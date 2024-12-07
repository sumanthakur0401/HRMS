package com.cg.hrms.controller;

import com.cg.hrms.entities.DeptEmp;
import com.cg.hrms.exceptions.EmployeeNotFoundException;
import com.cg.hrms.exceptions.InvalidInputException;
import com.cg.hrms.repos.DeptEmpRepo;
import com.cg.hrms.services.serviceimplementation.DeptEmpServiceImpl;
import com.cg.hrms.utility.DeptEmpCompositeKey;
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

import jakarta.transaction.Transactional;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeptEmpControllerTest {

    @InjectMocks
    private DeptEmpServiceImpl deptEmpService;

    @Mock
    private DeptEmpRepo deptEmpRepo;

    private DeptEmp deptEmp;
    private DeptEmpCompositeKey compositeKey;
    private Pageable pageable;
    private Date fromDate;
    private Date toDate;

    @BeforeEach
    public void setup() {
        fromDate = new Date();
        toDate = new Date(fromDate.getTime() + 86400000L); 
        compositeKey = new DeptEmpCompositeKey(1, "D01");
        deptEmp = new DeptEmp();
        deptEmp.setDeptEmpId(compositeKey);
        deptEmp.setFromDate(fromDate);
        deptEmp.setToDate(toDate);
        pageable = PageRequest.of(0, 10);
    }

    @Test
    public void testAddNewDeptEmp_ValidInput() {
        when(deptEmpRepo.save(any(DeptEmp.class))).thenReturn(deptEmp);
        DeptEmp result = deptEmpService.addNewDeptEmp(deptEmp);
        assertNotNull(result);
        assertEquals(deptEmp, result);
    }

    @Test
    public void testAddNewDeptEmp_NullInput() {
        assertThrows(InvalidInputException.class, () -> deptEmpService.addNewDeptEmp(null));
    }

    @Test
    public void testGetAllDeptEmp_ValidInput() {
        Page<DeptEmp> deptEmpPage = new PageImpl<>(List.of(deptEmp), pageable, 1);
        when(deptEmpRepo.findAll(pageable)).thenReturn(deptEmpPage);
        Page<DeptEmp> result = deptEmpService.getAllDeptEmp(pageable);
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(deptEmp, result.getContent().get(0));
    }

    @Test
    public void testGetAllDeptEmp_Empty() {
        Page<DeptEmp> emptyPage = new PageImpl<>(new ArrayList<>(), pageable, 0);
        when(deptEmpRepo.findAll(pageable)).thenReturn(emptyPage);
        assertThrows(EmployeeNotFoundException.class, () -> deptEmpService.getAllDeptEmp(pageable));
    }

    @Test
    public void testGetAllDeptEmpByEmpNoAndDeptNo_ValidInput() {
        Page<DeptEmp> deptEmpPage = new PageImpl<>(List.of(deptEmp), pageable, 1);
        when(deptEmpRepo.findAllBydeptEmpId(compositeKey, pageable)).thenReturn(deptEmpPage);
        Page<DeptEmp> result = deptEmpService.getAllDeptEmpByEmpNoAndDeptNo(1, "D01", pageable);
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(deptEmp, result.getContent().get(0));
    }

    @Test
    public void testGetAllDeptEmpByEmpNoAndDeptNo_InvalidEmpNo() {
        assertThrows(InvalidInputException.class, () -> deptEmpService.getAllDeptEmpByEmpNoAndDeptNo(0, "D01", pageable));
    }

    @Test
    public void testGetAllDeptEmpByEmpNoAndDeptNo_InvalidDeptNo() {
        assertThrows(InvalidInputException.class, () -> deptEmpService.getAllDeptEmpByEmpNoAndDeptNo(1, "", pageable));
    }

    @Test
    public void testUpdateDeptEmp_ValidInput() {
        DeptEmp updatedDeptEmp = new DeptEmp();
        updatedDeptEmp.setDeptEmpId(compositeKey);
        updatedDeptEmp.setFromDate(fromDate);
        updatedDeptEmp.setToDate(toDate);
        when(deptEmpRepo.findByDeptEmpId(compositeKey)).thenReturn(deptEmp);
        when(deptEmpRepo.save(deptEmp)).thenReturn(updatedDeptEmp);
        DeptEmp result = deptEmpService.updateDeptEmp(1, "D01", updatedDeptEmp);
        assertNotNull(result);
        assertEquals(updatedDeptEmp, result);
    }

    @Test
    public void testUpdateDeptEmp_EmployeeNotFound() {
        DeptEmp updatedDeptEmp = new DeptEmp();
        when(deptEmpRepo.findByDeptEmpId(compositeKey)).thenReturn(null);
        assertThrows(EmployeeNotFoundException.class, () -> deptEmpService.updateDeptEmp(1, "D01", updatedDeptEmp));
    }

    @Test
    public void testDeleteDeptEmpByEmpNoAndDeptNo_ValidInput() {
        when(deptEmpRepo.findByDeptEmpId(compositeKey)).thenReturn(deptEmp);
        deptEmpService.deleteDeptEmpByEmpNoAndDeptNo(1, "D01");
        verify(deptEmpRepo, times(1)).delete(deptEmp);
    }

    @Test
    public void testDeleteDeptEmpByEmpNoAndDeptNo_EmployeeNotFound() {
        when(deptEmpRepo.findByDeptEmpId(compositeKey)).thenReturn(null);
        assertThrows(EmployeeNotFoundException.class, () -> deptEmpService.deleteDeptEmpByEmpNoAndDeptNo(1, "D01"));
    }

    @Test
    @Transactional
    public void testDeleteDeptEmpByDeptNoAndFromDate_ValidInput() {
        List<DeptEmp> deptEmps = List.of(deptEmp);
        when(deptEmpRepo.findByDeptEmpIdDeptNoAndFromDate("D01", fromDate)).thenReturn(deptEmps);
        deptEmpService.deleteDeptEmpByDeptNoAndFromDate("D01", fromDate);
        verify(deptEmpRepo, times(1)).deleteByDeptEmpId(deptEmp.getDeptEmpId());
    }

    @Test
    public void testDeleteDeptEmpByDeptNoAndFromDate_NoRecords() {
        when(deptEmpRepo.findByDeptEmpIdDeptNoAndFromDate("D01", fromDate)).thenReturn(new ArrayList<>());
        assertThrows(EmployeeNotFoundException.class, () -> deptEmpService.deleteDeptEmpByDeptNoAndFromDate("D01", fromDate));
    }

    @Test
    @Transactional
    public void testDeleteDeptEmpByEmpNoAndFromDate_ValidInput() {
        List<DeptEmp> deptEmps = List.of(deptEmp);
        when(deptEmpRepo.findByDeptEmpIdEmpNoAndFromDate(1, fromDate)).thenReturn(deptEmps);
        deptEmpService.deleteDeptEmpByEmpNoAndFromDate(1, fromDate);
        verify(deptEmpRepo, times(1)).deleteByDeptEmpId(deptEmp.getDeptEmpId());
    }

    @Test
    public void testDeleteDeptEmpByEmpNoAndFromDate_NoRecords() {
        when(deptEmpRepo.findByDeptEmpIdEmpNoAndFromDate(1, fromDate)).thenReturn(new ArrayList<>());
        assertThrows(EmployeeNotFoundException.class, () -> deptEmpService.deleteDeptEmpByEmpNoAndFromDate(1, fromDate));
    }
}
