package com.cg.hrms.services.serviceinterface;

import java.util.List;

import com.cg.hrms.dtos.DepartmentDTO;
import com.cg.hrms.entities.Department;

public interface DepartmentService {

	/**
     * Adds a new department.
     * @param department The department to add.
     * @return The added department.
     */
    Department addDepartment(Department department);

    /**
     * Retrieves all departments.
     * @return A list of all departments.
     */
    List<Department> getAllDepartment();

    /**
     * Finds a department by its department number.
     * @param deptno The department number to search for.
     * @return The department with the specified department number.
     */
    Department findDepartmentByDeptNo(String deptno);

    /**
     * Finds a department by its name.
     * @param name The name of the department to search for.
     * @return The department with the specified name.
     */
    Department findDepartmentByName(String name);

    /**
     * Updates a department by its department number.
     * @param deptno The department number of the department to update.
     * @param department The updated department information.
     */
    String updateDepartmentByDeptNo(String deptno, Department department);

    /**
     * Updates a department by its name.
     * @param name The name of the department to update.
     * @param department The updated department information.
     */
    String updateDepartmentByName(String name, Department department);

    /**
     * Deletes a department by its department number.
     * @param deptno The department number of the department to delete.
     */
    String deleteDepartmentByDeptNo(String deptno);

    /**
     * Deletes a department by its name.
     * @param name The name of the department to delete.
     */
    String deleteDepartmentByName(String name);
}
