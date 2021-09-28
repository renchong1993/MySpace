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

import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name="userInfos")
public class UserInfo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String gender;
	
//	@Value("${some.key:1}")
	private int age;
	
	private String location;
	
	@Value("${some.key:src/main/resources/static/img/default_img}")  //set default value
	private String image_url;
	
	
	private Date updatedAt;
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	
	
	//================ Relationship =================//
	//===== 121 User&Blurb =====//
//	@OneToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="user_id")
//	private User owner;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User owner;
	
	

	//================ Constructor =================//
	public UserInfo() {
	
	}
	
	public UserInfo(String gender, int age, String location, String image_url, User owner) {
		super();
		this.gender = gender;
		this.age = age;
		this.location = location;
		this.image_url = image_url;
		this.owner = owner;
	}





	//================ Getter&Setter =================//
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
	
	
	

}
