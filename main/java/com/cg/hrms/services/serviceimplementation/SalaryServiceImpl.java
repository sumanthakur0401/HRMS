package com.cg.hrms.services.serviceimplementation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.hrms.entities.Salary;
import com.cg.hrms.repos.SalaryRepo;
import com.cg.hrms.services.serviceinterface.SalaryService;

@Service
public class SalaryServiceImpl implements SalaryService {

    @Autowired
    private SalaryRepo salaryRepo;

    @Override
    public Salary addSalary(Salary salary) {
        return salaryRepo.save(salary);
    }

    @Override
    public List<Salary> getAllSalaries() {
        return salaryRepo.findAll();
    }

    @Override
    public Salary findSalaryByEmpNoAndFromDate(int empNo, Date fromDate) {
        return salaryRepo.findByIdEmpNoAndIdFromDate(empNo, fromDate);
    }

    @Override
    public List<Salary> findAllSalariesByFromDate(Date fromDate) {
        return salaryRepo.findByIdFromDate(fromDate);
    }

    @Override
    public List<Salary> findAllSalariesByEmpNo(int empNo) {
        return salaryRepo.findByIdEmpNo(empNo);
    }

    @Override
    public List<Salary> findAllSalariesBySalaryRange(int minSalary, int maxSalary) {
        return salaryRepo.findBySalaryBetween(minSalary, maxSalary);
    }

    @Override
    public List<Salary> updateSalariesByFromDate(Date fromDate, Salary salary) {
        List<Salary> existingSalaries = salaryRepo.findByIdFromDate(fromDate);
        for (Salary existingSalary : existingSalaries) {
            existingSalary.setSalary(salary.getSalary());
            salaryRepo.save(existingSalary);
        }
        return existingSalaries;
    }

    @Override
    public List<Salary> updateSalariesByEmpNo(int empNo, Salary salary) {
        List<Salary> existingSalaries = salaryRepo.findByIdEmpNo(empNo);
        for (Salary existingSalary : existingSalaries) {
            existingSalary.setSalary(salary.getSalary());
            salaryRepo.save(existingSalary);
        }
        return existingSalaries;
    }

    @Override
    public Salary updateSalaryByEmpNoAndFromDate(int empNo, Date fromDate, Salary salary) {
        Salary existingSalary = salaryRepo.findByIdEmpNoAndIdFromDate(empNo, fromDate);
        if (existingSalary != null) {
            existingSalary.setSalary(salary.getSalary());
            return salaryRepo.save(existingSalary);
        }
        return null;
    }

    @Override
    public String deleteSalaryByEmpNoAndFromDate(int empNo, Date fromDate) {
        Salary salary = salaryRepo.findByIdEmpNoAndIdFromDate(empNo, fromDate);
        if (salary != null) {
            salaryRepo.delete(salary);
            return "Salary deleted successfully.";
        }
        return "Salary not found.";
    }

    @Override
    public String deleteSalariesByEmpNo(int empNo) {
        List<Salary> salaries = salaryRepo.findByIdEmpNo(empNo);
        if (!salaries.isEmpty()) {
            salaryRepo.deleteAll(salaries);
            return "Salaries deleted successfully.";
        }
        return "Salaries not found.";
    }

    @Override
    public String deleteSalariesByFromDate(Date fromDate) {
        List<Salary> salaries = salaryRepo.findByIdFromDate(fromDate);
        if (!salaries.isEmpty()) {
            salaryRepo.deleteAll(salaries);
            return "Salaries deleted successfully.";
        }
        return "Salaries not found.";
    }

    @Override
    public List<Salary> findSalariesGreaterThan(double salary) {
        return salaryRepo.findBySalaryGreaterThan(salary);
    }
    
    
}
