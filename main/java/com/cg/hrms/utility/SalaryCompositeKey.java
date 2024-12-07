package com.cg.hrms.utility;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Embeddable
public class SalaryCompositeKey implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "emp_no")
    private int empNo; 
	
	@Column(name = "from_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fromDate; 

    public SalaryCompositeKey() {}

    public SalaryCompositeKey(int empNo, Date fromDate) {
        this.empNo = empNo;
        this.fromDate = fromDate;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryCompositeKey that = (SalaryCompositeKey) o;
        return empNo == that.empNo &&
               fromDate.equals(that.fromDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empNo, fromDate);
    }

	@Override
	public String toString() {
		return "SalaryCompositeKey [empNo=" + empNo + ", fromDate=" + fromDate + "]";
	}
}
