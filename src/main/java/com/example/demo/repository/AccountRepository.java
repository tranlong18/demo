package com.example.demo.repository;

import com.example.demo.dto.AccountDTO;
import com.example.demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);
}
