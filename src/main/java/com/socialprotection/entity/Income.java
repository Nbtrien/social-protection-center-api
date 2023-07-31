package com.socialprotection.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "incomes")
public class Income {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "income_id")
	private long incomeId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "budget_id")
	private Budget budget;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "bank_account_id")
	private BankAccount bankAccount;
	
	@Column(name = "income_name")
	private String incomeName;
	
	@Column(name = "income_description")
	private String incomeDescription;
	
	@Column(name = "amount")
	private float amount;
	
	@Column(name = "date_time")
	private Date dateTime;
	
	public Income() {
		super();
	}

	public Income(Budget budget, BankAccount bankAccount, String incomeName, String incomeDescription, float amount,
			Date dateTime) {
		super();
		this.budget = budget;
		this.bankAccount = bankAccount;
		this.incomeName = incomeName;
		this.incomeDescription = incomeDescription;
		this.amount = amount;
		this.dateTime = dateTime;
	}
	
	public long getIncomeId() {
		return incomeId;
	}

	public void setIncomeId(long incomeId) {
		this.incomeId = incomeId;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getIncomeName() {
		return incomeName;
	}

	public void setIncomeName(String incomeName) {
		this.incomeName = incomeName;
	}

	public String getIncomeDescription() {
		return incomeDescription;
	}

	public void setIncomeDescription(String incomeDescription) {
		this.incomeDescription = incomeDescription;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	
}
