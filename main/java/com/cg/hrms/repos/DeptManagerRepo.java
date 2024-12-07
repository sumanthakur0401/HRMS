package com.cg.hrms.repos;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.cg.hrms.entities.DeptManager;
import com.cg.hrms.utility.DeptManagerCompositeKey;

import jakarta.validation.constraints.NotNull;

@Repository
public interface DeptManagerRepo extends JpaRepository<DeptManager, DeptManagerCompositeKey> {

   
    // Method to find all by deptNo and fromDate
    List<DeptManager> findAllByIdDeptNoAndFromDate(String deptNo, Date fromDate);

    // Method to find all by empNo and fromDate
    List<DeptManager> findAllByIdEmpNoAndFromDate(int empNo, Date fromDate);

    // Method to find by composite key and fromDate
    DeptManager findByIdAndFromDate(DeptManagerCompositeKey id, Date fromDate);
}
