package com.socialprotection.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "token")
@Table(name = "tokens")
public class Token {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long id;

	@Column(unique = true)
	public String token;

	public boolean revoked;

	public boolean expired;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	public User user;

	@Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Timestamp createdAt;

	@Column(name = "modified_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp modifiedAt;

	public Token() {
		super();
	}

	public Token(long id, String token, boolean revoked, boolean expired, User user) {
		super();
		this.id = id;
		this.token = token;
		this.revoked = revoked;
		this.expired = expired;
		this.user = user;
	}

	public Token(String token, boolean revoked, boolean expired, User user) {
		super();
		this.token = token;
		this.revoked = revoked;
		this.expired = expired;
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isRevoked() {
		return revoked;
	}

	public void setRevoked(boolean revoked) {
		this.revoked = revoked;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@PreUpdate
	public void setModifiedAt() {
		this.modifiedAt = new Timestamp(System.currentTimeMillis());
	}
}
