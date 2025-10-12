package com.example.demo.service;

import com.example.demo.dto.AccountDTO;

public interface AccountService {
    AccountDTO createAccount(AccountDTO accountDTO);

    AccountDTO updateAccount(Long accountId, AccountDTO accountDTO);

    void deleteAccount(Long accountId);
}
