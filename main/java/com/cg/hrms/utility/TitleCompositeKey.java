package com.cg.hrms.utility;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Embeddable
public class TitleCompositeKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "emp_no")
	private int empNo;

	@Column(name = "title", length = 50)
	private String title;

	@Column(name = "from_date")
	@Temporal(TemporalType.DATE)
	private Date fromDate;

	public TitleCompositeKey() {
	}

	public TitleCompositeKey(int empNo, String title, Date fromDate) {
		this.empNo = empNo;
		this.title = title;
		this.fromDate = fromDate;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		TitleCompositeKey that = (TitleCompositeKey) o;
		return empNo == that.empNo && title.equals(that.title) && fromDate.equals(that.fromDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(empNo, title, fromDate);
	}

	@Override
	public String toString() {
		return "TitleCompositeKey [empNo=" + empNo + ", title=" + title + ", fromDate=" + fromDate + "]";
	}
}
