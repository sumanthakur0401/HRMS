package com.cg.hrms.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import com.cg.hrms.dtos.EmployeeDTO;
import com.cg.hrms.utility.Gender;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "employees")
public class Employee  implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "emp_no")
	private int empNo;

	@Column(name = "birth_date")
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Column(name = "first_name",length = 14,nullable = false)
	private String firstName;

	@Column(name = "last_name", length = 16,nullable = false)
	private String lastName;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "hire_date")
	@Temporal(TemporalType.DATE)
	private Date hireDate;
	
	@OneToMany(mappedBy = "employee",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private Set<DeptEmp> deptEmps;
	
	@OneToMany(mappedBy = "employee",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private Set<DeptManager> deptManagers;
	
	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private Set<Salary> salary;
	
	@OneToMany(mappedBy = "employee",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonBackReference
	private Set<Title> titles;

	public Employee() {

	}

	public Employee(int empNo, Date birthDate, String firstName, String lastName, Gender gender, Date hireDate,
			Set<DeptEmp> deptEmps) {
		super();
		this.empNo = empNo;
		this.birthDate = birthDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.hireDate = hireDate;
		this.deptEmps = deptEmps;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Set<DeptEmp> getDeptEmps() {
		return deptEmps;
	}

	public void setDeptEmps(Set<DeptEmp> deptEmps) {
		this.deptEmps = deptEmps;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", birthDate=" + birthDate + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", hireDate=" + hireDate + ", deptEmps=" + deptEmps + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, deptEmps, empNo, firstName, gender, hireDate, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(birthDate, other.birthDate) && Objects.equals(deptEmps, other.deptEmps)
				&& empNo == other.empNo && Objects.equals(firstName, other.firstName) && gender == other.gender
				&& Objects.equals(hireDate, other.hireDate) && Objects.equals(lastName, other.lastName);
	}
	
	public EmployeeDTO convertToDTO() {
		EmployeeDTO empDto = new EmployeeDTO();
		
		empDto.setEmpNo(empNo);
		empDto.setBirthDate(birthDate);
		empDto.setFirstName(firstName);
		empDto.setLastName(lastName);
		empDto.setGender(gender);
		empDto.setHireDate(hireDate);
		
		return empDto;
	}
}
