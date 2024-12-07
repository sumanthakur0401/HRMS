package com.cg.hrms.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hrms.dtos.EmployeeDTO;
import com.cg.hrms.entities.Employee;
import com.cg.hrms.entities.Salary;
import com.cg.hrms.entities.Title;
import com.cg.hrms.services.serviceinterface.EmployeeService;
import com.cg.hrms.services.serviceinterface.SalaryService;
import com.cg.hrms.services.serviceinterface.TitleService;
import com.cg.hrms.utility.Gender;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private SalaryService salaryService;
    
    @Autowired
    private TitleService titleService;
    
    @PostMapping
    public ResponseEntity<EmployeeDTO> addNewEmployee(@RequestBody EmployeeDTO employeeDto) {
        Employee employee = employeeService.addNewEmployee(employeeDto.convertToEmployee());
        return ResponseEntity.ok(employee.convertToDTO());
    }

    @GetMapping
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployees(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EmployeeDTO> employeeDtos = employeeService.getAllEmployees(pageable).map(Employee::convertToDTO);
        return ResponseEntity.ok(employeeDtos);
    }

    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployeeByLastName(
            @PathVariable("lastName") String lastName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EmployeeDTO> employeeDtos = employeeService.getAllEmployeeByLastName(lastName, pageable).map(Employee::convertToDTO);
        return ResponseEntity.ok(employeeDtos);
    }

    @GetMapping("/firstname/{firstName}")
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployeeByFirstName(
            @PathVariable("firstName") String firstName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EmployeeDTO> employeeDtos = employeeService.getAllEmployeeByFirstName(firstName, pageable).map(Employee::convertToDTO);
        return ResponseEntity.ok(employeeDtos);
    }

    @PutMapping("/lastname/{empno}")
    public ResponseEntity<EmployeeDTO> updateLastNameOfEmployeeUsingEmpNo(
            @PathVariable("empno") int empno,
            @RequestParam("lastName") String lastName) {
        Employee employee = employeeService.getEmployeeById(empno);
        employee.setLastName(lastName);
        employeeService.updateEmployee(employee);
        return ResponseEntity.ok(employee.convertToDTO());
    }

    @PutMapping("/firstname/{empno}")
    public ResponseEntity<EmployeeDTO> updateFirstNameOfEmployeeUsingEmpNo(
            @PathVariable("empno") int empno,
            @RequestParam("firstName") String firstName) {
        Employee employee = employeeService.getEmployeeById(empno);
        employee.setFirstName(firstName);
        employeeService.updateEmployee(employee);
        return ResponseEntity.ok(employee.convertToDTO());
    }

    @PutMapping("/hiredate/{empno}")
    public ResponseEntity<EmployeeDTO> updateHireDateOfEmployeeUsingEmpNo(
            @PathVariable("empno") int empno,
            @RequestParam("hiredate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date hiredate) {
        Employee employee = employeeService.getEmployeeById(empno);
        employee.setHireDate(hiredate);
        employeeService.updateEmployee(employee);
        return ResponseEntity.ok(employee.convertToDTO());
    }

    @PutMapping("/birthdate/{empno}")
    public ResponseEntity<EmployeeDTO> updateBirthDateOfEmployeeUsingEmpNo(
            @PathVariable("empno") int empno,
            @RequestParam("birthdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthdate) {
        Employee employee = employeeService.getEmployeeById(empno);
        employee.setBirthDate(birthdate);
        employeeService.updateEmployee(employee);
        return ResponseEntity.ok(employee.convertToDTO());
    }

    @GetMapping("/gender/{gender}")
    public ResponseEntity<Page<EmployeeDTO>> getAllEmployeeByGender(
            @PathVariable("gender") Gender gender,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EmployeeDTO> employeeDtos = employeeService.getAllEmployeeByGender(gender, pageable).map(Employee::convertToDTO);
        return ResponseEntity.ok(employeeDtos);
    }

    @GetMapping("/empid/{empno}")
    public ResponseEntity<EmployeeDTO> getEmployeeByEmpNo(@PathVariable("empno") int empno) {
        Employee employee = employeeService.getEmployeeById(empno);
        return ResponseEntity.ok(employee.convertToDTO());
    }

    @GetMapping("/hiredate/{hiredate}")
    public ResponseEntity<Page<EmployeeDTO>> getEmployeeByHireDate(
            @PathVariable("hiredate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date hiredate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EmployeeDTO> employeeDtos = employeeService.getEmployeeByHireDate(hiredate, pageable).map(Employee::convertToDTO);
        return ResponseEntity.ok(employeeDtos);
    }

    @GetMapping("/birthdate/{birthdate}")
    public ResponseEntity<Page<EmployeeDTO>> getEmployeeByBirthDate(
            @PathVariable("birthdate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date birthdate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EmployeeDTO> employeeDtos = employeeService.getEmployeeByBirthDate(birthdate, pageable).map(Employee::convertToDTO);
        return ResponseEntity.ok(employeeDtos);
    }
    
    @GetMapping("/countSalariesWithTitle")
    public long countSalariesWithTitle(@RequestParam double minSalary, @RequestParam String title) {
   
        List<Salary> salaries = salaryService.findSalariesGreaterThan(minSalary);
        Set<Long> salaryList = new HashSet<>();

        for (Salary s : salaries) {
        	salaryList.add((long) s.getEmployee().getEmpNo());
        }

        List<Title> titles = titleService.findTitlesByTitle(title);
        Set<Long> titleList = new HashSet<>();

        for (Title t : titles) {
        	titleList.add((long) t.getEmployee().getEmpNo()); 
        }

        salaryList.retainAll(titleList);

  
        return salaryList.size();
    }
}
