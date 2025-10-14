package com.example.demo.controller;

import com.example.demo.dto.AccountDTO;
import com.example.demo.dto.EmployeeDTO;
import com.example.demo.service.AccountService;
import com.example.demo.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<ApiResponse<AccountDTO>> createAccount(@RequestBody AccountDTO accountDTO) {
        AccountDTO savedAccount = accountService.createAccount(accountDTO);
        ApiResponse<AccountDTO> response = ApiResponse.<AccountDTO>builder()
                .status(HttpStatus.CREATED.value())
                .message("Account created successfully")
                .data(savedAccount)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse<AccountDTO>> updateAccount(@PathVariable("id") Long accountId,
                                                                 @RequestBody AccountDTO accountDTO) {
        AccountDTO savedAccount = accountService.updateAccount(accountId, accountDTO);
        ApiResponse<AccountDTO> response = ApiResponse.<AccountDTO>builder()
                .status(HttpStatus.OK.value())
                .message("Account update successfully")
                .data(savedAccount)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAccount(@PathVariable("id") Long accountId) {
        accountService.deleteAccount(accountId);
        ApiResponse<Void> response = ApiResponse.<Void>builder()
                .status(HttpStatus.OK.value())
                .message("Account delete successfully")
                .build();
        return ResponseEntity.ok(response);
    }

}
