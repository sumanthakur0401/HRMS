package com.cg.hrms.dtos;

import java.util.Date;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import com.cg.hrms.entities.Title;
import com.cg.hrms.utility.TitleCompositeKey;

public class TitleDTO {
	private int empNo;
	private String title;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fromDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date toDate;

	public TitleDTO(int empNo, String title, Date fromDate, Date toDate) {
		super();
		this.empNo = empNo;
		this.title = title;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}
	
	public TitleDTO() {
		super();
	}
	
	//setters and getters
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
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	
	@Override
	public String toString() {
		return "TitleDTO [empNo=" + empNo + ", title=" + title + ", fromDate=" + fromDate + ", toDate=" + toDate + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(empNo, fromDate, title, toDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TitleDTO other = (TitleDTO) obj;
		return empNo == other.empNo && Objects.equals(fromDate, other.fromDate) && Objects.equals(title, other.title)
				&& Objects.equals(toDate, other.toDate);
	}
	
	public Title convertToTitle() {
		Title t = new Title();
		
		TitleCompositeKey tck = new TitleCompositeKey();
		tck.setEmpNo(empNo);
		tck.setTitle(title);
		tck.setFromDate(fromDate);
		
		t.setId(tck);
		t.setToDate(toDate);
		
		return t;
	}
	
}