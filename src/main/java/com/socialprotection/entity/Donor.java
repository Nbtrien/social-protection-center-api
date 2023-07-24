package com.socialprotection.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "donors")
public class Donor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "donor_id")
	private long donorId;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id")
	private Person personDonor;
	
	@Column(name = "account_name")
	private String accountName;
	
	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "bank_name")
	private String bankName;
	
	@Column(name = "bank_cvv")
	private String bankCvv;
	
	@Column(name = "expiration_date")
	private Date expirationDate;
	
	@OneToOne(mappedBy = "donor")
	private Donation donation;
	
	public Donor() {
		super();
	}

	public Donor(Person personDonor, String accountName, String accountNumber, String bankName, String bankCvv,
			Date expirationDate, Donation donation) {
		super();
		this.personDonor = personDonor;
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		this.bankCvv = bankCvv;
		this.expirationDate = expirationDate;
		this.donation = donation;
	}

	public Person getPersonDonor() {
		return personDonor;
	}

	public void setPersonDonor(Person personDonor) {
		this.personDonor = personDonor;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCvv() {
		return bankCvv;
	}

	public void setBankCvv(String bankCvv) {
		this.bankCvv = bankCvv;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Donation getDonation() {
		return donation;
	}

	public void setDonation(Donation donation) {
		this.donation = donation;
	}
	
}
