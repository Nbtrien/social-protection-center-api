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
@Table(name = "type_of_orphans")
public class TypeOfOrphan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orphan_type_id")
	private long orphanTypeId;
	@Column(name = "orphan_type_name")
	private String orphanTypeName;

	@JsonIgnore
	@OneToMany(mappedBy = "typeOfOrphans")
	private List<Children> children;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	public TypeOfOrphan(String orphanTypeName, String description) {
		super();
		this.orphanTypeName = orphanTypeName;
		this.description = description;
	}

	public TypeOfOrphan() {
		super();
	}

	public long getOrphanTypeId() {
		return orphanTypeId;
	}

	public void setOrphanTypeId(long orphanTypeId) {
		this.orphanTypeId = orphanTypeId;
	}

	public String getOrphanTypeName() {
		return orphanTypeName;
	}

	public void setOrphanTypeName(String orphanTypeName) {
		this.orphanTypeName = orphanTypeName;
	}

	public List<Children> getChildren() {
		return children;
	}

	public void setChildren(List<Children> children) {
		this.children = children;
	}

}