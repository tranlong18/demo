package com.example.demo.dto;

import com.example.demo.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)// nếu null response trả về json k hiển thị
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private Integer age;
    private AccountDTO account;
}
