package com.socialprotection.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "adoptions")
public class Adoption {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "adoption_id")
	private long adoptionId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "register_date")
	private Date registerDate;

	@Column(name = "status", columnDefinition = "Char(1)")
	private String status;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "child_id", nullable = false)
	private Children children;

	@OneToMany(mappedBy = "adoption", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Adopter> adopters;

	@PrePersist
	private void prePersist() {
		adopters.forEach(c -> c.setAdoption(this));
	}

	public long getAdoptionId() {
		return adoptionId;
	}

	public void setAdoptionId(long adoptionId) {
		this.adoptionId = adoptionId;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Children getChildren() {
		return children;
	}

	public void setChildren(Children children) {
		this.children = children;
	}

	public Set<Adopter> getAdopters() {
		return adopters;
	}

	public void setAdopters(Set<Adopter> adopters) {
		this.adopters = adopters;
	}

	public Adoption(Date registerDate, String status) {
		super();
		this.registerDate = registerDate;
		this.status = status;
	}

	public Adoption() {
		super();
	}

}
