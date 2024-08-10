package com.example.jwt_auth2.repository;

import com.example.jwt_auth2.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
