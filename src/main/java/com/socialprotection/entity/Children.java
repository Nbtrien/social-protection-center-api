package com.socialprotection.entity;

import java.sql.Date;
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

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
	@Column(name = "birthday")
	private Date birthDay;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "image_id")
	private Image image;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "guardian_id")
	private Guardian guardian;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "orphan_type_id")
	private TypeOfOrphan typeOfOrphan;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "child_status_id")
	private ChildrenStatus childrenStatus;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "citizen_id")
	private CitizenIdentification citizenId;

	@OneToMany(mappedBy = "child")
	@JsonIgnore
	private List<MedicalRecord> medicalRecords;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Asia/Ho_Chi_Minh")
	@Column(name = "date_in")
	private Date dateIn;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@Column(name = "date_out")
	private Date dateOut;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
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

//	@OneToMany(mappedBy = "children")
//	@JsonIgnore
//	private List<Guardian> guardians;

	@Column(name = "circumstance", columnDefinition = "TEXT")
	private String circumstance;

	public Children(Date birthDay, Image image, Employee employeeChild, TypeOfOrphan typeOfOrphan,
			ChildrenStatus childrenStatus, CitizenIdentification citizenId, Date dateIn, Date dateOut,
			Set<Activity> activities, List<Adoption> adoptions, AdoptionHistory adoptionHistory) {
		super();
		this.birthDay = birthDay;
		this.image = image;
		this.employee = employeeChild;
		this.typeOfOrphan = typeOfOrphan;
		this.childrenStatus = childrenStatus;
		this.citizenId = citizenId;
		this.dateIn = dateIn;
		this.dateOut = dateOut;
		this.activities = activities;
		this.adoptions = adoptions;
		this.adoptionHistory = adoptionHistory;
	}

	public Children(String fullName, String firstName, String lastName, String gender, String nationality,
			String addressPermanent, String addressTemporary, Date birthDay, Image image, Employee employee,
			TypeOfOrphan typeOfOrphan, ChildrenStatus childrenStatus, Date dateIn, Date dateOut, String circumstance) {
		super(fullName, firstName, lastName, gender, nationality, addressPermanent, addressTemporary);
		this.birthDay = birthDay;
		this.image = image;
		this.employee = employee;
		this.typeOfOrphan = typeOfOrphan;
		this.childrenStatus = childrenStatus;
		this.dateIn = dateIn;
		this.dateOut = dateOut;
		this.circumstance = circumstance;
	}

	public Children(String fullName, String firstName, String lastName, String gender, String nationality,
			String addressPermanent, String addressTemporary, Date birthDay, Image image, TypeOfOrphan typeOfOrphan,
			Date dateIn, String circumstance) {
		super(fullName, firstName, lastName, gender, nationality, addressPermanent, addressTemporary);
		this.birthDay = birthDay;
		this.image = image;
		this.typeOfOrphan = typeOfOrphan;
		this.dateIn = dateIn;
		this.circumstance = circumstance;
	}

	public Children() {
		super();
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

	public TypeOfOrphan getTypeOfOrphan() {
		return typeOfOrphan;
	}

	public void setTypeOfOrphan(TypeOfOrphan typeOfOrphan) {
		this.typeOfOrphan = typeOfOrphan;
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

	public String getCircumstance() {
		return circumstance;
	}

	public void setCircumstance(String circumstance) {
		this.circumstance = circumstance;
	}

	public Guardian getGuardian() {
		return guardian;
	}

	public void setGuardian(Guardian guardian) {
		this.guardian = guardian;
	}

	@Override
	public String toString() {
		return "Children [childId=" + childId + ", birthDay=" + birthDay + ", image=" + image + ", guardian=" + guardian
				+ ", employee=" + employee + ", typeOfOrphan=" + typeOfOrphan + ", childrenStatus=" + childrenStatus
				+ ", citizenId=" + citizenId + ", medicalRecords=" + medicalRecords + ", dateIn=" + dateIn
				+ ", dateOut=" + dateOut + ", activities=" + activities + ", adoptions=" + adoptions
				+ ", adoptionHistory=" + adoptionHistory + ", circumstance=" + circumstance + "]";
	}

}