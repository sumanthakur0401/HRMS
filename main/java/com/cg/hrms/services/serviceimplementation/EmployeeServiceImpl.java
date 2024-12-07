package com.cg.hrms.services.serviceimplementation;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cg.hrms.dtos.EmployeeDTO;
import com.cg.hrms.entities.Employee;
import com.cg.hrms.exceptions.EmployeeNotFoundException;
import com.cg.hrms.exceptions.InvalidInputException;
import com.cg.hrms.repos.DepartmentRepo;
import com.cg.hrms.repos.DeptEmpRepo;
import com.cg.hrms.repos.EmployeeRepo;
import com.cg.hrms.repos.TitleRepo;
import com.cg.hrms.services.serviceinterface.EmployeeService;
import com.cg.hrms.utility.Gender;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepo empRepo;

	@Override
    public Employee addNewEmployee(Employee employee) {
        if (employee == null) {
            throw new InvalidInputException("Employee or Employee ID cannot be null");
        }
        return empRepo.save(employee);
    }

    @Override
    public Page<Employee> getAllEmployees(Pageable pageable) {
        Page<Employee> empPage = empRepo.findAll(pageable);
        if (empPage.isEmpty()) {
            throw new EmployeeNotFoundException("Could not find any employees");
        }
        return empPage;
    }

    @Override
    public Page<Employee> getAllEmployeeByLastName(String lastName, Pageable pageable) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new InvalidInputException("Last name cannot be null or empty");
        }
        Page<Employee> empPage = empRepo.findByLastName(lastName, pageable);
        if (empPage.isEmpty()) {
            throw new EmployeeNotFoundException("No employees found with last name: " + lastName);
        }
        return empPage;
    }

    @Override
    public Page<Employee> getAllEmployeeByFirstName(String firstName, Pageable pageable) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new InvalidInputException("First name cannot be null or empty");
        }
        Page<Employee> empPage = empRepo.findByFirstName(firstName, pageable);
        return empPage;
    }

    @Override
    public Employee getEmployeeById(int empNo) {
        return empRepo.findById(empNo)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with ID " + empNo + " not found"));
    }

    @Override
    public void updateEmployee(Employee employee) {
        if (employee == null) {
            throw new InvalidInputException("Employee or Employee ID cannot be null");
        }
        if (!empRepo.existsById(employee.getEmpNo())) {
            throw new EmployeeNotFoundException("Employee with ID " + employee.getEmpNo() + " not found");
        }
        empRepo.save(employee);
    }

    @Override
    public Page<Employee> getAllEmployeeByGender(Gender gender, Pageable pageable) {
        if (gender == null) {
            throw new InvalidInputException("Gender cannot be null or empty");
        }
        Page<Employee> empPage = empRepo.findByGender(gender, pageable);
        if (empPage.isEmpty()) {
            throw new EmployeeNotFoundException("No employees found with gender " + gender);
        }
        return empPage;
    }

    @Override
    public Page<Employee> getEmployeeByHireDate(Date hireDate, Pageable pageable) {
        if (hireDate == null) {
            throw new InvalidInputException("Hire date cannot be null");
        }
        Page<Employee> empPage = empRepo.findByHireDate(hireDate, pageable);
        if (empPage.isEmpty()) {
            throw new EmployeeNotFoundException("No employees found with hire date " + hireDate);
        }
        return empPage;
    }

    @Override
    public Page<Employee> getEmployeeByBirthDate(Date birthDate, Pageable pageable) {
        if (birthDate == null) {
            throw new InvalidInputException("Birth date cannot be null");
        }
        Page<Employee> empPage = empRepo.findByBirthDate(birthDate, pageable);
        if (empPage.isEmpty()) {
            throw new EmployeeNotFoundException("No employees found with birth date " + birthDate);
        }
        return empPage;
    }
}
