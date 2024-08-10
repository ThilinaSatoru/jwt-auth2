package com.example.jwt_auth2.service;

import com.example.jwt_auth2.model.Employee;
import com.example.jwt_auth2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    private final String storageDirectory = "C:\\Users\\samurai\\Pictures\\APP\\profile_pictures";

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public void saveProfilePicture(MultipartFile file, Long employeeId) throws IOException {
        if (!Files.exists(Paths.get(storageDirectory))) {
            Files.createDirectories(Paths.get(storageDirectory));
        }
        String fileName = employeeId + "_" + file.getOriginalFilename();
        File destinationFile = new File(storageDirectory + File.separator + fileName);
        file.transferTo(destinationFile);

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setProfilePicturePath(destinationFile.getPath());
        employeeRepository.save(employee);
    }

    public byte[] getProfilePicture(Long employeeId) throws IOException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new RuntimeException("Employee not found"));
        String picturePath = employee.getProfilePicturePath();
        return Files.readAllBytes(Paths.get(picturePath));
    }
}
