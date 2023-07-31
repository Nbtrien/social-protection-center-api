package com.socialprotection.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bank_accounts")
public class BankAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bank_account_id")
	private long bankAccountId;

	@Column(name = "account_number")
	private String accountNumber;

	@Column(name = "account_name")
	private String accountName;
	
	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "balance")
	private float balance;

	@OneToMany(mappedBy = "bankAccount")
	private List<Income> incomes;

	@OneToMany(mappedBy = "bankAccount")
	private List<Expense> expenses;

	public BankAccount() {
		super();
	}

	public BankAccount(String accountNumber, String accountName, String bankName, float balance) {
		super();
		this.accountNumber = accountNumber;
		this.accountName = accountName;
		this.bankName = bankName;
		this.balance = balance;
	}

	public long getBankAccountId() {
		return bankAccountId;
	}

	public void setBankAccountId(long bankAccountId) {
		this.bankAccountId = bankAccountId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

}
