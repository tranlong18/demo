package com.example.demo.mapper;

import com.example.demo.dto.AccountDTO;
import com.example.demo.entity.Account;
import com.example.demo.entity.Employee;

public class AccountMapper {
    public static AccountDTO mapToAccountDTO(Account account) {
        if (account == null) return null;

        Long employeeId = account.getEmployee() != null
                ? account.getEmployee().getId()
                : null;
        return new AccountDTO(
                account.getId(),
                account.getEmail(),
                account.getPassword(),
                employeeId);
    }
    public static Account mapToAccount(AccountDTO accountDTO) {
        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setEmail(accountDTO.getEmail());
        account.setPassword(accountDTO.getPassword());
        return account;
    }
}
