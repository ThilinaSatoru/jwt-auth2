package com.example.jwt_auth2.config;

import com.example.jwt_auth2.model.Role;
import com.example.jwt_auth2.model.User;
import com.example.jwt_auth2.repository.RoleRepository;
import com.example.jwt_auth2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (roleRepository.count() == 0) {
            Role userRole = new Role();
            userRole.setName("user");
            roleRepository.save(userRole);

            Role adminRole = new Role();
            adminRole.setName("admin");
            roleRepository.save(adminRole);
        }

        if (userRepository.count() == 0) {
            Set<Role> roles = new HashSet<>(roleRepository.findAll());

            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("password"));
            user.setRoles(roles);
            userRepository.save(user);

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(roles);
            userRepository.save(admin);
        }
    }
}
