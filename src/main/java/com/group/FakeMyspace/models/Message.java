package com.group.FakeMyspace.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name="messages")
public class Message {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String content;
	@Column(updatable=false)
	private Date createAt;
	
	
	@PrePersist
	protected void onCreate() {
		this.createAt = new Date();
	}
	
	
	
	
	//================ Relationship =================//
	
	//===== M21 User&Messages =====//
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User sender;
	
	//===== 121 Message&User =====//
	@OneToOne(mappedBy="messageReceived", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private User recipient;

	
	
	
	//================ Constructor =================//
	public Message() {

	}



	//================ Getter&Setter =================//
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}
	
	
	
	
	

}

