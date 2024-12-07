//package com.cg.hrms.controller;
//
//import com.cg.hrms.dtos.DepartmentDTO;
//import com.cg.hrms.entities.Department;
//import com.cg.hrms.exceptions.DepartmentNotFoundException;
//import com.cg.hrms.services.serviceinterface.DepartmentService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class DepartmentControllerTest {
//
//    @InjectMocks
//    private DepartmentController departmentController;
//
//    @Mock
//    private DepartmentService departmentService;
//
//    private Department department;
//    private DepartmentDTO departmentDTO;
//    private List<Department> departments;
//    private List<DepartmentDTO> departmentDTOS;
//
//    @BeforeEach
//    public void setup() {
//        department = new Department("D001", "HR", null);
//        departmentDTO = new DepartmentDTO("D001", "HR");
//        departments = new ArrayList<>();
//        departments.add(department);
//        departmentDTOS = new ArrayList<>();
//        departmentDTOS.add(departmentDTO);
//    }
//
//    // Test for addDepartment method - Valid Input
//    @Test
//    public void testAddDepartment_ValidInput() {
//        when(departmentService.addDepartment(departmentDTO)).thenReturn(departmentDTO);
//        ResponseEntity<String> response = departmentController.addDepartment(departmentDTO);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("New department added successfully", response.getBody());
//    }
//
//    // Test for getAllDepartment method - Valid Input
//    @Test
//    public void testGetAllDepartment_ValidInput() {
//        when(departmentService.getAllDepartment()).thenReturn(departmentDTOS);
//        ResponseEntity<List<DepartmentDTO>> response = departmentController.getAllDepartment();
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(departmentDTOS, response.getBody());
//    }
//
//    // Test for findDepartmentByDeptNo method - Valid Input
//    @Test
//    public void testFindDepartmentByDeptNo_ValidInput() {
//        when(departmentService.findDepartmentByDeptNo("D001")).thenReturn(departmentDTO);
//        ResponseEntity<DepartmentDTO> response = departmentController.findDepartmentByDeptNo("D001");
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(departmentDTO, response.getBody());
//    }
//
//    // Test for findDepartmentByDeptNo method - Not Found
//    @Test
//    public void testFindDepartmentByDeptNo_NotFound() {
//        when(departmentService.findDepartmentByDeptNo("D001")).thenThrow(new DepartmentNotFoundException("Department with deptNo D001 not found"));
//        ResponseEntity<DepartmentDTO> response = null;
//        try {
//            response = departmentController.findDepartmentByDeptNo("D001");
//        } catch (DepartmentNotFoundException e) {
//            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    // Test for findDepartmentByName method - Valid Input
//    @Test
//    public void testFindDepartmentByName_ValidInput() {
//        when(departmentService.findDepartmentByName("HR")).thenReturn(departmentDTO);
//        ResponseEntity<DepartmentDTO> response = departmentController.findDepartmentByName("HR");
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(departmentDTO, response.getBody());
//    }
//
//    // Test for findDepartmentByName method - Not Found
//    @Test
//    public void testFindDepartmentByName_NotFound() {
//        when(departmentService.findDepartmentByName("HR")).thenThrow(new DepartmentNotFoundException("Department with name HR not found"));
//        ResponseEntity<DepartmentDTO> response = null;
//        try {
//            response = departmentController.findDepartmentByName("HR");
//        } catch (DepartmentNotFoundException e) {
//            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    // Test for updateDepartmentByDeptNo method - Valid Input
//    @Test
//    public void testUpdateDepartmentByDeptNo_ValidInput() {
//        when(departmentService.updateDepartmentByDeptNo("D001", departmentDTO)).thenReturn("Department updated successfully");
//        ResponseEntity<String> response = departmentController.updateDepartmentByDeptNo("D001", departmentDTO);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("DepartmentDTO updated successfully.", response.getBody());
//    }
//
//    // Test for updateDepartmentByName method - Valid Input
//    @Test
//    public void testUpdateDepartmentByName_ValidInput() {
//        when(departmentService.updateDepartmentByName("HR", departmentDTO)).thenReturn("Department updated successfully");
//        ResponseEntity<String> response = departmentController.updateDepartmentByName("HR", departmentDTO);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("DepartmentDTO updated successfully.", response.getBody());
//    }
//
//    // Test for updateDepartmentByName method - Not Found
//    @Test
//    public void testUpdateDepartmentByName_NotFound() {
//        when(departmentService.updateDepartmentByName("HR", departmentDTO)).thenThrow(new DepartmentNotFoundException("Department with name HR not found"));
//        ResponseEntity<String> response = null;
//        try {
//            response = departmentController.updateDepartmentByName("HR", departmentDTO);
//        } catch (DepartmentNotFoundException e) {
//            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    // Test for deleteDepartmentByDeptNo method - Valid Input
//    @Test
//    public void testDeleteDepartmentByDeptNo_ValidInput() {
//        when(departmentService.deleteDepartmentByDeptNo("D001")).thenReturn("Department deleted successfully");
//        ResponseEntity<String> response = departmentController.deleteDepartmentByDeptNo("D001");
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("DepartmentDTO Deleted Successfully", response.getBody());
//    }
//
//    // Test for deleteDepartmentByDeptNo method - Not Found
//    @Test
//    public void testDeleteDepartmentByDeptNo_NotFound() {
//        when(departmentService.deleteDepartmentByDeptNo("D001")).thenThrow(new DepartmentNotFoundException("Department with deptNo D001 not found"));
//        ResponseEntity<String> response = null;
//        try {
//            response = departmentController.deleteDepartmentByDeptNo("D001");
//        } catch (DepartmentNotFoundException e) {
//            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//
//    // Test for deleteDepartmentByName method - Valid Input
//    @Test
//    public void testDeleteDepartmentByName_ValidInput() {
//        when(departmentService.deleteDepartmentByName("HR")).thenReturn("Department deleted successfully");
//        ResponseEntity<String> response = departmentController.deleteDepartmentByName("HR");
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("DepartmentDTO with given name deleted Successfully", response.getBody());
//    }
//
//    // Test for deleteDepartmentByName method - Not Found
//    @Test
//    public void testDeleteDepartmentByName_NotFound() {
//        when(departmentService.deleteDepartmentByName("HR")).thenThrow(new DepartmentNotFoundException("Department with name HR not found"));
//        ResponseEntity<String> response = null;
//        try {
//            response = departmentController.deleteDepartmentByName("HR");
//        } catch (DepartmentNotFoundException e) {
//            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//    }
//}
