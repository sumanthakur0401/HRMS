package com.cg.hrms.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.hrms.entities.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, String> {

	Department findByDeptName(String deptName);

	boolean existsByDeptNo(String deptNo);

	void deleteByDeptNo(String deptNo);

	Optional<Department> findByDeptNo(String deptNo);
}
