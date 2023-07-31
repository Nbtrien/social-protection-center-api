package com.socialprotection.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person {

	@Column(name = "full_name")
	private String fullName;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "gender")
	private String gender;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "address_permanent", columnDefinition = "TEXT")
	private String addressPermanent;

	@Column(name = "address_temporary", columnDefinition = "TEXT")
	private String addressTemporary;

	public Person(String fullName, String firstName, String lastName, String gender, String nationality,
			String addressPermanent, String addressTemporary) {
		super();
		this.fullName = fullName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.nationality = nationality;
		this.addressPermanent = addressPermanent;
		this.addressTemporary = addressTemporary;
	}

	public Person() {
		super();
	}

	public String getAddressPermanent() {
		return addressPermanent;
	}

	public void setAddressPermanent(String addressPermanent) {
		this.addressPermanent = addressPermanent;
	}

	public String getAddressTemporary() {
		return addressTemporary;
	}

	public void setAddressTemporary(String addressTemporary) {
		this.addressTemporary = addressTemporary;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
}