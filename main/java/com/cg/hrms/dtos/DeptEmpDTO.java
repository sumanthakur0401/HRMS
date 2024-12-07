package com.cg.hrms.dtos;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.cg.hrms.entities.DeptEmp;
import com.cg.hrms.utility.DeptEmpCompositeKey;

public class DeptEmpDTO {

    private int empNo;
    private String deptNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fromDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toDate;

    public DeptEmpDTO() {
        super();
    }

    public DeptEmpDTO(int empNo, String deptNo, Date fromDate, Date toDate) {
        this.empNo = empNo;
        this.deptNo = deptNo;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    // Getters and Setters
    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

  
    // Method to convert from DeptEmpDTO to DeptEmp entity
    public DeptEmp convertToDeptEmp() {
    	  DeptEmp deptEmp = new DeptEmp();
    	  
          deptEmp.setDeptEmpId(new DeptEmpCompositeKey(this.empNo, this.deptNo)); // Set composite key using setter
          deptEmp.setFromDate(this.fromDate);
          deptEmp.setToDate(this.toDate);
          
          return deptEmp;
    }

    @Override
    public String toString() {
        return "DeptEmpDTO [empNo=" + empNo + ", deptNo=" + deptNo + ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
    }
}
