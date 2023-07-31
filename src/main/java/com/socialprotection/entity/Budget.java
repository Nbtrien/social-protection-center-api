package com.socialprotection.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "budgets")
public class Budget {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "budget_id")
	private long budgetId;

	@Column(name = "budget_name")
	private String budgetName;

	@Column(name = "budget_description")
	private String budgetDescription;

	@Column(name = "amount")
	private float amout;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

	@JsonIgnore
	@OneToMany(mappedBy = "budget", fetch = FetchType.LAZY)
	private List<Donation> donations;

	@OneToMany(mappedBy = "budget", fetch = FetchType.LAZY)
	private List<Income> incomes;

	public Budget() {
		super();
	}

	public Budget(String budgetName, String budgetDescription, float amout, Date startDate, Date endDate) {
		super();
		this.budgetName = budgetName;
		this.budgetDescription = budgetDescription;
		this.amout = amout;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public long getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(long budgetId) {
		this.budgetId = budgetId;
	}

	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}

	public void setAmout(float amout) {
		this.amout = amout;
	}

	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}

	public String getBudgetDescription() {
		return budgetDescription;
	}

	public void setBudgetDescription(String budgetDescription) {
		this.budgetDescription = budgetDescription;
	}

	public Float getAmout() {
		return amout;
	}

	public void setAmout(Float amout) {
		this.amout = amout;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
