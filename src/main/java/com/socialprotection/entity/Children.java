package com.socialprotection.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "children")
public class Children extends Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "child_id")
	private long childId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "birthday")
	private Date birthDay;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "image_id")
	private Image image;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orphan_type_id")
	private TypeOfOrphan typeOfOrphans;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "child_status_id")
	private ChildrenStatus childrenStatus;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "citizen_id")
	private CitizenIdentification citizenId;

	@OneToMany(mappedBy = "child")
	@JsonIgnore
	private List<MedicalRecord> medicalRecords;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "date_in")
	private Date dateIn;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "date_out")
	private Date dateOut;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "child_activities", joinColumns = {
			@JoinColumn(name = "child_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "activity_id", nullable = false) })
	@JsonIgnore
	private Set<Activity> activities = new HashSet<>();

	@OneToMany(mappedBy = "children")
	@JsonIgnore
	private List<Adoption> adoptions;

	@OneToOne(mappedBy = "children")
	@JsonIgnore
	private AdoptionHistory adoptionHistory;

	@OneToMany(mappedBy = "children")
	@JsonIgnore
	private List<Guardian> guardians;

	

	public List<Guardian> getGuardians() {
		return guardians;
	}

	public void setGuardians(List<Guardian> guardians) {
		this.guardians = guardians;
	}

	public AdoptionHistory getAdoptionHistory() {
		return adoptionHistory;
	}

	public void setAdoptionHistory(AdoptionHistory adoptionHistory) {
		this.adoptionHistory = adoptionHistory;
	}

	public List<Adoption> getAdoptions() {
		return adoptions;
	}

	public void setAdoptions(List<Adoption> adoptions) {
		this.adoptions = adoptions;
	}

	public Set<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Set<Activity> activities) {
		this.activities = activities;
	}

	public long getChildId() {
		return childId;
	}

	public void setChildId(long childId) {
		this.childId = childId;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public TypeOfOrphan getTypeOfOrphans() {
		return typeOfOrphans;
	}

	public void setTypeOfOrphans(TypeOfOrphan typeOfOrphans) {
		this.typeOfOrphans = typeOfOrphans;
	}

	public ChildrenStatus getChildrenStatus() {
		return childrenStatus;
	}

	public void setChildrenStatus(ChildrenStatus childrenStatus) {
		this.childrenStatus = childrenStatus;
	}

	public CitizenIdentification getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(CitizenIdentification citizenId) {
		this.citizenId = citizenId;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecords;
	}

	public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
		this.medicalRecords = medicalRecords;
	}

	public Children(Date birthDay, Image image, Employee employeeChild, TypeOfOrphan typeOfOrphans,
			ChildrenStatus childrenStatus, CitizenIdentification citizenId, Date dateIn, Date dateOut,
			Set<Activity> activities, List<Adoption> adoptions, AdoptionHistory adoptionHistory) {
		super();
		this.birthDay = birthDay;
		this.image = image;
		this.employee = employeeChild;
		this.typeOfOrphans = typeOfOrphans;
		this.childrenStatus = childrenStatus;
		this.citizenId = citizenId;
		this.dateIn = dateIn;
		this.dateOut = dateOut;
		this.activities = activities;
		this.adoptions = adoptions;
		this.adoptionHistory = adoptionHistory;
	}

	public Children() {
		super();
	}

}