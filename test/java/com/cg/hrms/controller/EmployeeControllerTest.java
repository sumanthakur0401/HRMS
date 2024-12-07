package com.cg.hrms.controller;

import com.cg.hrms.dtos.EmployeeDTO;
import com.cg.hrms.entities.Employee;
import com.cg.hrms.exceptions.EmployeeNotFoundException;
import com.cg.hrms.services.serviceinterface.EmployeeService;
import com.cg.hrms.utility.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the EmployeeController class.
 */
@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;

    private Employee employee;
    private EmployeeDTO employeeDTO;
    private List<Employee> employees;
    private List<EmployeeDTO> employeeDTOS;
    private Pageable pageable;

    @BeforeEach
    public void setup() {
        // Initialize test data
        employee = new Employee(1, new Date(), "Suman", "Thakur", Gender.M, new Date(), null);
        employeeDTO = new EmployeeDTO(1, new Date(), "Suman", "Thakur", Gender.M, new Date());
        employees = new ArrayList<>();
        employees.add(employee);
        employeeDTOS = new ArrayList<>();
        employeeDTOS.add(employeeDTO);
        pageable = PageRequest.of(0, 10);
    }

    /**
     * Test the addNewEmployee method with valid input.
     */
    @Test
    public void testAddNewEmployee_ValidInput() {
        when(employeeService.addNewEmployee(any(Employee.class))).thenReturn(employee);
        ResponseEntity<EmployeeDTO> response = employeeController.addNewEmployee(employeeDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTO, response.getBody());
    }

    /**
     * Test the addNewEmployee method with invalid input.
     */
    @Test
    public void testAddNewEmployee_InvalidInput() {
        EmployeeDTO invalidEmployeeDTO = new EmployeeDTO(0, null, null, null, null, null);
        ResponseEntity<EmployeeDTO> response = null;
        try {
            employeeController.addNewEmployee(invalidEmployeeDTO);
        } catch (NullPointerException e) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Test the getAllEmployees method with valid input.
     */
    @Test
    public void testGetAllEmployees_ValidInput() {
        Page<Employee> employeePage = new PageImpl<>(employees, pageable, employees.size());
        when(employeeService.getAllEmployees(any(Pageable.class))).thenReturn(employeePage);
        ResponseEntity<Page<EmployeeDTO>> response = employeeController.getAllEmployees(0, 10);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTOS, response.getBody().getContent());
    }

    /**
     * Test the getAllEmployees method when there are no employees.
     */
    @Test
    public void testGetAllEmployees_NoEmployees() {
        Page<Employee> emptyEmployeePage = new PageImpl<>(new ArrayList<>(), pageable, 0);
        when(employeeService.getAllEmployees(any(Pageable.class))).thenReturn(emptyEmployeePage);
        ResponseEntity<Page<EmployeeDTO>> response = employeeController.getAllEmployees(0, 10);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new ArrayList<>(), response.getBody().getContent());
    }

    /**
     * Test the getAllEmployees method when an exception occurs.
     */
    @Test
    public void testGetAllEmployees_Exception() {
        when(employeeService.getAllEmployees(any(Pageable.class)))
                .thenThrow(new RuntimeException("Error getting all employees"));
        ResponseEntity<Page<EmployeeDTO>> response = null;
        try {
            employeeController.getAllEmployees(0, 10);
        } catch (RuntimeException e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /**
     * Test the getAllEmployeeByLastName method with valid input.
     */
    @Test
    public void testGetAllEmployeeByLastName_ValidInput() {
        when(employeeService.getAllEmployeeByLastName("Thakur", PageRequest.of(1, 1))).thenReturn(new PageImpl<>(employees));
        ResponseEntity<Page<EmployeeDTO>> response = employeeController.getAllEmployeeByLastName("Thakur", 1, 1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTOS, response.getBody().getContent());
    }

    /**
     * Test the getAllEmployeeByLastName method when there are no employees.
     */
    @Test
    public void testGetAllEmployeeByLastName_NoEmployees() {
        Page<Employee> emptyEmployeePage = new PageImpl<>(new ArrayList<>(), pageable, 0);
        when(employeeService.getAllEmployeeByLastName("Thakur", PageRequest.of(0, 10))).thenReturn(emptyEmployeePage);
        ResponseEntity<Page<EmployeeDTO>> response = employeeController.getAllEmployeeByLastName("Thakur", 0, 10);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new ArrayList<>(), response.getBody().getContent());
    }

    /**
     * Test the getAllEmployeeByLastName method when an exception occurs.
     */
    @Test
    public void testGetAllEmployeeByLastName_Exception() {
        when(employeeService.getAllEmployeeByLastName("Thakur", PageRequest.of(1, 1)))
                .thenThrow(new RuntimeException("Error getting employees by last name"));
        ResponseEntity<Page<EmployeeDTO>> response = null;
        try {
            employeeController.getAllEmployeeByLastName("Thakur", 1, 1);
        } catch (RuntimeException e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    /**
     * Test the updateLastNameOfEmployeeUsingEmpNo method with valid input.
     */
    @Test
    public void testUpdateLastNameOfEmployeeUsingEmpNo_ValidInput() {
        when(employeeService.getEmployeeById(1)).thenReturn(employee);
        ResponseEntity<EmployeeDTO> response = employeeController.updateLastNameOfEmployeeUsingEmpNo(1, "Sahu");
        employeeDTO.setLastName(response.getBody().getLastName());
        verify(employeeService, times(1)).updateEmployee(employee);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTO, response.getBody());
    }

    /**
     * Test the updateLastNameOfEmployeeUsingEmpNo method when the employee is not found.
     */
    @Test
    public void testUpdateLastNameOfEmployeeUsingEmpNo_EmployeeNotFound() {
        when(employeeService.getEmployeeById(1)).thenThrow(new EmployeeNotFoundException("Employee not found"));
        ResponseEntity<EmployeeDTO> response = null;
        try {
            response = employeeController.updateLastNameOfEmployeeUsingEmpNo(1, "Sahu");
        } catch (EmployeeNotFoundException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * Test the updateLastNameOfEmployeeUsingEmpNo method with invalid input.
     */
    @Test
    public void testUpdateLastNameOfEmployeeUsingEmpNo_InvalidInput() {
        when(employeeService.getEmployeeById(1)).thenReturn(null);
        ResponseEntity<EmployeeDTO> response = null;
        try {
            employeeController.updateLastNameOfEmployeeUsingEmpNo(1, "Sahu");
        } catch (NullPointerException e) {
            response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    /**
     * Test the updateFirstNameOfEmployeeUsingEmpNo method with valid input.
     */
    @Test
    public void testUpdateFirstNameOfEmployeeUsingEmpNo_ValidInput() {
        when(employeeService.getEmployeeById(1)).thenReturn(employee);
        ResponseEntity<EmployeeDTO> response = employeeController.updateFirstNameOfEmployeeUsingEmpNo(1, "Satya");
        employeeDTO.setFirstName(response.getBody().getFirstName());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTO, response.getBody());
    }

    /**
     * Test the updateFirstNameOfEmployeeUsingEmpNo method when the employee is not found.
     */
    @Test
    public void testUpdateFirstNameOfEmployeeUsingEmpNo_EmployeeNotFound() {
        when(employeeService.getEmployeeById(1)).thenThrow(new EmployeeNotFoundException("Employee not found"));
        ResponseEntity<EmployeeDTO> response = null;
        try {
            employeeController.updateFirstNameOfEmployeeUsingEmpNo(1, "Satya");
        } catch (EmployeeNotFoundException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * Test the updateHireDateOfEmployeeUsingEmpNo method with valid input.
     */
    @Test
    public void testUpdateHireDateOfEmployeeUsingEmpNo_ValidInput() {
        when(employeeService.getEmployeeById(1)).thenReturn(employee);
        ResponseEntity<EmployeeDTO> response = employeeController.updateHireDateOfEmployeeUsingEmpNo(1, new Date());
        verify(employeeService, times(1)).updateEmployee(employee);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    /**
     * Test the updateBirthDateOfEmployeeUsingEmpNo method with valid input.
     */
    @Test
    public void testUpdateBirthDateOfEmployeeUsingEmpNo_ValidInput() {
        when(employeeService.getEmployeeById(1)).thenReturn(employee);
        ResponseEntity<EmployeeDTO> response = employeeController.updateBirthDateOfEmployeeUsingEmpNo(1, new Date());
        employeeDTO.setBirthDate(response.getBody().getBirthDate());
        verify(employeeService, times(1)).updateEmployee(employee);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTO, response.getBody());
    }

    /**
     * Test the getEmployeeByEmpNo method with valid input.
     */
    @Test
    public void testGetEmployeeByEmpNo_ValidInput() {
        when(employeeService.getEmployeeById(1)).thenReturn(employee);
        ResponseEntity<EmployeeDTO> response = employeeController.getEmployeeByEmpNo(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTO, response.getBody());
    }

    /**
     * Test the getEmployeeByEmpNo method when the employee is not found.
     */
    @Test
    public void testGetEmployeeByEmpNo_EmployeeNotFound() throws EmployeeNotFoundException {
        when(employeeService.getEmployeeById(1)).thenThrow(new EmployeeNotFoundException("Employee not found"));
        ResponseEntity<EmployeeDTO> response = null;
        try {
            response = employeeController.getEmployeeByEmpNo(1);
        } catch (EmployeeNotFoundException e) {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    /**
     * Test the getEmployeeByHireDate method with valid input.
     */
    @Test
    public void testGetEmployeeByHireDate_ValidInput() {
        Page<Employee> employeePage = new PageImpl<>(employees, pageable, employees.size());
        when(employeeService.getEmployeeByHireDate(any(Date.class), any(Pageable.class))).thenReturn(employeePage);
        ResponseEntity<Page<EmployeeDTO>> response = employeeController.getEmployeeByHireDate(new Date(), 0, 10);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTOS, response.getBody().getContent());
    }

    /**
     * Test the getEmployeeByBirthDate method with valid input.
     */
    @Test
    public void testGetEmployeeByBirthDate_ValidInput() {
        Page<Employee> employeePage = new PageImpl<>(employees, pageable, employees.size());
        when(employeeService.getEmployeeByBirthDate(any(Date.class), any(Pageable.class))).thenReturn(employeePage);
        ResponseEntity<Page<EmployeeDTO>> response = employeeController.getEmployeeByBirthDate(new Date(), 0, 10);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(employeeDTOS, response.getBody().getContent());
    }
}
