package com.cg.hrms.dtos;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.cg.hrms.entities.Salary;
import com.cg.hrms.utility.SalaryCompositeKey;

public class SalaryDTO {

    private int empNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fromDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toDate;
    private int salary;

    // Default constructor
    public SalaryDTO() {
        super();
    }

    // Parameterized constructor
    public SalaryDTO(int empNo, Date fromDate, Date toDate, int salary) {
        this.empNo = empNo;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.salary = salary;
    }

    // Getters and Setters
    public int getEmpNo() {
        return empNo;
    }

    public void setEmpNo(int empNo) {
        this.empNo = empNo;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    // Convert SalaryDTO to Salary entity
    public Salary convertToSalary() {
        Salary salaryEntity = new Salary();
        salaryEntity.setId(new SalaryCompositeKey(this.empNo, this.fromDate));
        salaryEntity.setSalary(this.salary);
        salaryEntity.setToDate(this.toDate);
        return salaryEntity;
    }

   

    @Override
    public String toString() {
        return "SalaryDTO [empNo=" + empNo + ", fromDate=" + fromDate + ", toDate=" + toDate + ", salary=" + salary + "]";
    }
}