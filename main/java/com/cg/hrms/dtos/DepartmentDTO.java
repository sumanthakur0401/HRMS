package com.cg.hrms.dtos;

import com.cg.hrms.entities.Department;

public class DepartmentDTO {

    private String deptNo;
    private String deptName;

    // Default constructor
    public DepartmentDTO() {
        super();
    }

    // Parameterized constructor
    public DepartmentDTO(String deptNo, String deptName) {
        this.deptNo = deptNo;
        this.deptName = deptName;
    }

    // Getters and Setters
    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    // Convert DepartmentDTO to Department entity
    public Department convertToDepartment() {
        Department department = new Department();
        department.setDeptNo(this.deptNo);
        department.setDeptName(this.deptName);
        return department;
    }

    @Override
    public String toString() {
        return "DepartmentDTO [deptNo=" + deptNo + ", deptName=" + deptName + "]";
    }
}