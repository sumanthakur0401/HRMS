package com.cg.hrms.dtos;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.cg.hrms.entities.DeptManager;
import com.cg.hrms.utility.DeptManagerCompositeKey;

public class DeptManagerDTO {

    private int empNo;
    private String deptNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fromDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toDate;

    // Default constructor
    public DeptManagerDTO() {
        super();
    }

    // Parameterized constructor
    public DeptManagerDTO(int empNo, String deptNo, Date fromDate, Date toDate) {
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

    // Convert DeptManagerDTO to DeptManager entity using setters
    public DeptManager convertToDeptManagerDTO() {
        DeptManager deptManager = new DeptManager();
        
        deptManager.setId(new DeptManagerCompositeKey(this.empNo, this.deptNo));
        deptManager.setFromDate(this.fromDate);
        deptManager.setToDate(this.toDate);
        
        return deptManager;
    }

    @Override
    public String toString() {
        return "DeptManagerDTO [empNo=" + empNo + ", deptNo=" + deptNo + ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
    }
}
