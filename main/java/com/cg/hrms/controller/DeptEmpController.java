package com.cg.hrms.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.hrms.dtos.DeptEmpDTO;
import com.cg.hrms.entities.DeptEmp;
import com.cg.hrms.services.serviceinterface.DeptEmpService;

/**
 * REST controller for managing department-employee (DeptEmp) associations.
 */
@RestController
@RequestMapping("/api/v1/deptemp")
public class DeptEmpController {

    @Autowired
    private DeptEmpService deptEmpService;

    /**
     * Add a new DeptEmp record.
     */
    @PostMapping
    public ResponseEntity<DeptEmpDTO> addNewDeptEmp(@RequestBody DeptEmpDTO deptEmpDTO) {
        DeptEmp deptEmp = deptEmpService.addNewDeptEmp(deptEmpDTO.convertToDeptEmp());
        return ResponseEntity.status(HttpStatus.CREATED).body(deptEmp.convertToDTO());
    }

    /**
     * Get all DeptEmp records with pagination.
     */
    @GetMapping
    public ResponseEntity<Page<DeptEmpDTO>> getAllDeptEmp(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<DeptEmp> deptEmpPage = deptEmpService.getAllDeptEmp(pageable);
        return ResponseEntity.ok(deptEmpPage.map(DeptEmp::convertToDTO));
    }

    /**
     * Get DeptEmp by empNo and deptNo with pagination.
     */
    @GetMapping("/empno/{empno}/deptno/{deptno}")
    public ResponseEntity<Page<DeptEmpDTO>> getAllDeptEmpByEmpNoAndDeptNo(
            @PathVariable int empno,
            @PathVariable String deptno,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<DeptEmp> deptEmpPage = deptEmpService.getAllDeptEmpByEmpNoAndDeptNo(empno, deptno, pageable);
        return ResponseEntity.ok(deptEmpPage.map(DeptEmp::convertToDTO));
    }

    /**
     * Get DeptEmp by deptNo and fromDate with pagination.
     */
    @GetMapping("/deptno/{deptno}/fromdate/{fromdate}")
    public ResponseEntity<Page<DeptEmpDTO>> getAllDeptEmpByDeptNoAndFromDate(
            @PathVariable String deptno,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromdate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<DeptEmp> deptEmpPage = deptEmpService.getAllDeptEmpByDeptNoAndFromDate(deptno, fromdate, pageable);
        return ResponseEntity.ok(deptEmpPage.map(DeptEmp::convertToDTO));
    }

    /**
     * Get DeptEmp by empNo and fromDate with pagination.
     */
    @GetMapping("/empno/{empno}/fromdate/{fromdate}")
    public ResponseEntity<Page<DeptEmpDTO>> getAllDeptEmpByEmpNoAndFromDate(
            @PathVariable int empno,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromdate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<DeptEmp> deptEmpPage = deptEmpService.getAllDeptEmpByEmpNoAndFromDate(empno, fromdate, pageable);
        return ResponseEntity.ok(deptEmpPage.map(DeptEmp::convertToDTO));
    }

    /**
     * Get DeptEmp by empNo, deptNo, and fromDate with pagination.
     */
    @GetMapping("/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}")
    public ResponseEntity<Page<DeptEmpDTO>> getAllDeptEmpByEmpNoAndDeptNoAndFromDate(
            @PathVariable int empno,
            @PathVariable String deptno,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromdate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<DeptEmp> deptEmpPage = deptEmpService.getAllDeptEmpByEmpNoAndDeptNoAndFromDate(empno, deptno, fromdate, pageable);
        return ResponseEntity.ok(deptEmpPage.map(DeptEmp::convertToDTO));
    }

    /**
     * Update an existing DeptEmp record.
     */
    @PutMapping("/{empno}/{deptno}")
    public ResponseEntity<DeptEmpDTO> updateDeptEmp(
            @PathVariable int empno,
            @PathVariable String deptno,
            @RequestBody DeptEmpDTO updatedDeptEmpDTO) {

        try {
            DeptEmp updatedDeptEmp = deptEmpService.updateDeptEmp(empno, deptno, updatedDeptEmpDTO.convertToDeptEmp());
            return ResponseEntity.ok(updatedDeptEmp.convertToDTO());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Update DeptEmp by empNo and fromDate.
     */
    @PutMapping("/empno/{empno}/fromdate/{fromdate}")
    public ResponseEntity<String> updateDeptEmpByEmpNoAndFromDate(
            @PathVariable int empno,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestBody DeptEmpDTO updatedDeptEmpDTO) {

        try {
            deptEmpService.updateDeptEmpByEmpNoAndFromDate(empno, fromDate, updatedDeptEmpDTO.convertToDeptEmp());
            return ResponseEntity.ok("DeptEmp updated");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Update failed: " + e.getMessage());
        }
    }

    /**
     * Update DeptEmp by deptNo and fromDate.
     */
    @PutMapping("/deptno/{deptno}/fromdate/{fromdate}")
    public ResponseEntity<List<DeptEmpDTO>> updateDeptEmpByDeptNoAndFromDate(
            @PathVariable String deptno,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fromDate,
            @RequestBody DeptEmpDTO updatedDeptEmpDTO) {

        List<DeptEmp> updatedDeptEmpPage = deptEmpService.updateDeptEmpByDeptNoAndFromDate(deptno, fromDate, updatedDeptEmpDTO.convertToDeptEmp());
        return ResponseEntity.ok(updatedDeptEmpPage.stream().map(DeptEmp::convertToDTO).toList());
    }

    /**
     * Update DeptEmp by empNo, deptNo, and fromDate.
     */
    @PutMapping("/empno/{empno}/deptno/{deptno}/fromdate/{fromdate}")
    public ResponseEntity<String> updateDeptEmpByEmpNoDeptNoAndFromDate(
            @PathVariable int empno,
            @PathVariable String deptno,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestBody DeptEmpDTO updatedDeptEmpDTO) {

        deptEmpService.updateDeptEmpByEmpNoDeptNoAndFromDate(empno, deptno, fromDate, updatedDeptEmpDTO.convertToDeptEmp());
        return ResponseEntity.ok("DeptEmp updated");
    }

    /**
     * Delete DeptEmp by empNo and deptNo.
     */
    @DeleteMapping("/empno/{empno}/deptno/{deptno}")
    public ResponseEntity<String> deleteDeptEmpByEmpNoAndDeptNo(
            @PathVariable int empno,
            @PathVariable String deptno) {

        deptEmpService.deleteDeptEmpByEmpNoAndDeptNo(empno, deptno);
        return ResponseEntity.ok("DeptEmp deleted");
    }

    /**
     * Delete DeptEmp by empNo and fromDate.
     */
    @DeleteMapping("/empno/{empno}/fromdate/{fromdate}")
    public ResponseEntity<String> deleteDeptEmpByEmpNoAndFromDate(
            @PathVariable int empno,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromdate) {

        deptEmpService.deleteDeptEmpByEmpNoAndFromDate(empno, fromdate);
        return ResponseEntity.ok("DeptEmp deleted");
    }

    /**
     * Delete DeptEmp by deptNo and fromDate.
     */
    @DeleteMapping("/deptno/{deptno}/fromdate/{fromdate}")
    public ResponseEntity<String> deleteDeptEmpByDeptNoAndFromDate(
            @PathVariable String deptno,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate) {

        deptEmpService.deleteDeptEmpByDeptNoAndFromDate(deptno, fromDate);
        return ResponseEntity.ok("DeptEmp deleted");
    }
}
