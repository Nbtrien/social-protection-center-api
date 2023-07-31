package com.socialprotection.entity;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "shifts")
public class Shift {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shift_id")
	private long shiftId;
	
	@Column(name = "shift_title")
	private String shiftTitle;
	
	@Column(name = "time_start")
	private Time timeStart;
	
	@Column(name = "time_end")
	private Time timeEnd;
	
	@JsonIgnore
	@OneToMany(mappedBy = "shift")
	private List<Employee> employees;

	public long getShiftId() {
		return shiftId;
	}

//	public void setShiftId(long shiftId) {
//		this.shiftId = shiftId;
//	}

	public String getShiftTitle() {
		return shiftTitle;
	}

	public void setShiftTitle(String shiftTitle) {
		this.shiftTitle = shiftTitle;
	}

	public Time getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(Time timeStart) {
		this.timeStart = timeStart;
	}

	public Time getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Time timeEnd) {
		this.timeEnd = timeEnd;
	}

	

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	

	public Shift(String shiftTitle, Time timeStart, Time timeEnd) {
		super();
		this.shiftTitle = shiftTitle;
		this.timeStart = timeStart;
		this.timeEnd = timeEnd;
	}

	public Shift() {
		super();
	}

	
	
}
