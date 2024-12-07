package com.cg.hrms.dtos;

import java.util.Date;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import com.cg.hrms.entities.Employee;
import com.cg.hrms.utility.Gender;

public class EmployeeDTO {
	private int empNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	private String firstName;
	private String lastName;
	private Gender gender;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date hireDate;

	public EmployeeDTO() {
		super();
	}

	public EmployeeDTO(int empNo, Date birthDate, String firstName, String lastName, Gender gender, Date hireDate) {
		super();
		this.empNo = empNo;
		this.birthDate = birthDate;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.hireDate = hireDate;
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

	@Override
	public String toString() {
		return "EmployeeDTO [empNo=" + empNo + ", birthDate=" + birthDate + ", firstName=" + firstName + ", lastName="
				+ lastName + ", gender=" + gender + ", hireDate=" + hireDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthDate, empNo, firstName, gender, hireDate, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDTO other = (EmployeeDTO) obj;
		return Objects.equals(birthDate, other.birthDate) && empNo == other.empNo
				&& Objects.equals(firstName, other.firstName) && gender == other.gender
				&& Objects.equals(hireDate, other.hireDate) && Objects.equals(lastName, other.lastName);
	}

	public Employee convertToEmployee() {
		Employee t = new Employee();

		t.setEmpNo(empNo);
		t.setBirthDate(birthDate);
		t.setFirstName(firstName);
		t.setLastName(lastName);
		t.setGender(gender);
		t.setHireDate(hireDate);

		return t;
	}

}
