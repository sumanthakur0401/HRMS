package com.cg.hrms.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.cg.hrms.dtos.SalaryDTO;
import com.cg.hrms.utility.SalaryCompositeKey;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "salaries")
public class Salary implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private SalaryCompositeKey id;

    @Column(name = "salary", nullable = false)
    private int salary;

    @Column(name = "to_date", nullable = false)
	@Temporal(TemporalType.DATE)
    private Date toDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("empNo")
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    private Employee employee;

	public Salary() {
		super();
	}

	public Salary(SalaryCompositeKey id, int salary, Date toDate, Employee employee) {
		super();
		this.id = id;
		this.salary = salary;
		this.toDate = toDate;
		this.employee = employee;
	}
	
	public SalaryCompositeKey getId() {
		return id;
	}

	public void setId(SalaryCompositeKey id) {
		this.id = id;
	}

	public int getSalary() {
		return salary;
	}
	
	public int getEmpNO() {
		return this.id.getEmpNo();
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@Override
	public String toString() {
		return "Salary [id=" + id + ", salary=" + this + ", toDate=" + toDate + ", employee=" + employee + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(employee, id, salary, toDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Salary other = (Salary) obj;
		return Objects.equals(employee, other.employee) && Objects.equals(id, other.id) && salary == other.salary
				&& Objects.equals(toDate, other.toDate);
	}
	
	 // Convert Salary entity to SalaryDTO
    public SalaryDTO convertToDTO() {
     
        SalaryDTO dto = new SalaryDTO();
        dto.setEmpNo(this.getId().getEmpNo());
        dto.setFromDate(this.getId().getFromDate());
        dto.setToDate(this.getToDate());
        dto.setSalary(this.getSalary());
        return dto;
    }
}
