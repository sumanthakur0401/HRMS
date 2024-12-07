package com.cg.hrms.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.cg.hrms.dtos.TitleDTO;
import com.cg.hrms.utility.TitleCompositeKey;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@Table(name = "titles")
public class Title implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private TitleCompositeKey id;

    @Column(name = "to_date", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date toDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("empNo")
    @JsonManagedReference
    @JoinColumn(name = "emp_no", insertable = false, updatable = false)
    private Employee employee;

    public Title(TitleCompositeKey id, Date toDate, Employee employee) {
		super();
		this.id = id;
		this.toDate = toDate;
		this.employee = employee;
	}

	public Title() {
		super();
	}
	
	//getter and setter method of composite key
	public int getEmpNo() {
		return id.getEmpNo();
	}
	public String getTitle() {
		return id.getTitle();
	}
	public Date getFromDate() {
		return id.getFromDate();
	}
	
	public void setTitle(String title) {
		this.id.setTitle(title);
	}

	public void setFromDate(Date fromDate) {
		this.id.setFromDate(fromDate);
	}
	
	//setters and getters
	public TitleCompositeKey getId() {
		return id;
	}

	public void setId(TitleCompositeKey id) {
		this.id = id;
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

	//hashCode toString and equals
	@Override
	public String toString() {
		return "Title [id=" + id + ", toDate=" + toDate + ", employee=" + employee + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(employee, id, toDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Title other = (Title) obj;
		return Objects.equals(employee, other.employee) && Objects.equals(id, other.id)
				&& Objects.equals(toDate, other.toDate);
	}
	
	public TitleDTO convertToDTO() {
		TitleDTO td = new TitleDTO();
		
		td.setEmpNo(getEmpNo());
		td.setTitle(getTitle());
		td.setFromDate(getFromDate());
		td.setToDate(toDate);
		
		return td;
	}
}
