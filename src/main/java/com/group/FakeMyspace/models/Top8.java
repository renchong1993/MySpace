package com.group.FakeMyspace.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity
@Table(name="top8s")
public class Top8 {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Date updatedAt;
	
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	

	//================ Relationship =================//
	
	//===== 121 User&Top8 list =====//
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User owner;
	
	//===== M2M Top8&User =====//
//	@OneToMany(mappedBy="fList", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
//	private List<User> eightFriends;
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "users_top8", 
        joinColumns = @JoinColumn(name = "top8_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> top8Frnd;


	
	
	//================ Constructor =================//
	public Top8() {
	
	}


	

	//================ Getter&Setter =================//
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public List<User> getTop8Frnd() {
		return top8Frnd;
	}


	public void setTop8Frnd(List<User> top8Frnd) {
		this.top8Frnd = top8Frnd;
	}
	
	
	
	
	
	
	

}
