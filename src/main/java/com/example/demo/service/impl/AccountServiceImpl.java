package com.example.demo.service.impl;

import com.example.demo.dto.AccountDTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.AccountMapper;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Employee employee = employeeRepository.findById(accountDTO.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Employee not found with ID: " + accountDTO.getEmployeeId()));

        Account account = AccountMapper.mapToAccount(accountDTO);
        account.setEmployee(employee);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO updateAccount(Long accountId, AccountDTO accountDTO) {
        Account account = accountRepository.findById(accountId).orElseThrow(
                () -> new ResourceNotFoundException("Account is not exist with given id: " + accountId));
        account.setEmail(accountDTO.getEmail());
        account.setPassword(accountDTO.getPassword());
        Account updatedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(updatedAccount);
    }

    @Override
    public void deleteAccount(Long accountId) {
        accountRepository.findById(accountId).orElseThrow(
                () -> new ResourceNotFoundException("Account is not exist with given id: " + accountId));
        accountRepository.deleteById(accountId);
    }

}
