package com.example.jwt_auth2.repository;

import com.example.jwt_auth2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
