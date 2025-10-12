package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT e FROM Employee e JOIN FETCH e.account")
    List<Employee> findAllWithAccount();

    @Query("SELECT e FROM Employee e JOIN FETCH e.account WHERE e.id = :id")
    Employee findByIdWithAccount(@Param("id") Long id);
}
