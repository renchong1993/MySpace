package com.group.FakeMyspace.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="blurbs")
public class Blurb {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String about;
	@NotBlank
	private String meet;
	@NotBlank
	private String quote;
//	@NotBlank
//	private String location;
//	
//	
//	private String image_url;
	
	
	private Date updatedAt;
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	

	
	//================ Relationship =================//
	//===== 121 User&Blurb =====//
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User owner;

	
	
	
	//================ Constructor =================//

	public Blurb() {
	
	}





	public Blurb(String about, String meet, String quote, User owner) {
		this.about = about;
		this.meet = meet;
		this.quote = quote;
		this.owner = owner;
	}





	//================ Getter&Setter =================//
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getMeet() {
		return meet;
	}

	public void setMeet(String meet) {
		this.meet = meet;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	
	
	
	
	
}
