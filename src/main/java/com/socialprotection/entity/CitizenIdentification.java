package com.socialprotection.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "citizen_identification")
public class CitizenIdentification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "citizen_id")
	private long citizenId;
	
	@Column(name = "citizen_ident_number", columnDefinition = "Char(12)")
	private String citizenIdentNumber;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "issue_date")
	private Date issueDate;
	
	@Column(name = "issue_place")
	private String issuePlace;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "expire_date")
	private Date expireDate;
	
	@JsonIgnore
	@OneToOne(mappedBy = "citizenId")
    private Children children;
	
	@JsonIgnore
	@OneToOne(mappedBy = "citizenIdentification")
    private Adopter adopter;
	
	

	public Adopter getAdopter() {
		return adopter;
	}

	public void setAdopter(Adopter adopter) {
		this.adopter = adopter;
	}

	public long getCitizenId() {
		return citizenId;
	}

//	public void setCitizenId(long citizenId) {
//		this.citizenId = citizenId;
//	}

	public String getCitizenIdentNumber() {
		return citizenIdentNumber;
	}

	public void setCitizenIdentNumber(String citizenIdentNumber) {
		this.citizenIdentNumber = citizenIdentNumber;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public String getIssuePlace() {
		return issuePlace;
	}

	public void setIssuePlace(String issuePlace) {
		this.issuePlace = issuePlace;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public Children getChildren() {
		return children;
	}

	public void setChildren(Children children) {
		this.children = children;
	}

	public CitizenIdentification(String citizenIdentNumber, Date issueDate, String issuePlace, Date expireDate,
			Children children, Adopter adopter) {
		super();
		this.citizenIdentNumber = citizenIdentNumber;
		this.issueDate = issueDate;
		this.issuePlace = issuePlace;
		this.expireDate = expireDate;
		this.children = children;
		this.adopter = adopter;
	}

	public CitizenIdentification() {
		super();
	}
	
	

}
