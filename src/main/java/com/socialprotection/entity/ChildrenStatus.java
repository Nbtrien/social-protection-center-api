package com.socialprotection.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "children_status")
public class ChildrenStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "child_status_id")
	private long childStatusId;

	@Column(name = "status")
	private String status;

	@JsonIgnore
	@OneToMany(mappedBy = "childrenStatus")
	private List<Children> children;
	
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	

	public ChildrenStatus(String status, String description) {
		super();
		this.status = status;
		this.description = description;
	}

	public ChildrenStatus() {
		super();
	}

	public long getChildStatusId() {
		return childStatusId;
	}

	public void setChildStatusId(int childStatusId) {
		this.childStatusId = childStatusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Children> getChildren() {
		return children;
	}

	public void setChildren(List<Children> children) {
		this.children = children;
	}

}
