package com.group.FakeMyspace.models;

import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
@Table(name="users")
public class User {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String name;

	@NotBlank
	private String email;

	
	@Size(min=8)
	private String password;
	@Transient
	private String passwordConfirm;
	@Column(updatable=false)
	private Date createAt;
	private Date updatedAt;
	
	
	@PrePersist
	protected void onCreate() {
		this.createAt = new Date();
	}
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	
	
	
	//================ Relationship =================//
	
	//===== 12M User&Frienfds =====//
	@OneToMany(mappedBy="owner", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Friend> friend;
	
	//===== 121 Friend&User =====//
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="friend_id")
	private Friend oneFriend;
	
	
	//===== 121 User&Top8 list =====//
	@OneToOne(mappedBy="owner", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Top8 myTop8;
	
	//===== M2M Top8&User =====//
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "users_top8", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "top8_id")
    )
    private List<Top8> top8List;
	
	
	//===== 121 User&Blurb =====//
	@OneToOne(mappedBy="owner", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Blurb blurb;
	
	
	//===== 121 User&UserInfo =====//
	@OneToOne(mappedBy="owner", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private UserInfo userInfo;
	
	
	//===== 12M User&Messages =====//
	@OneToMany(mappedBy="sender", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Message> messageSent;
	
	//===== 121 Message&User =====//
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="message_id")
	private Message messageReceived;
	
	
	//===== 12M Users&Comments =====//
	@OneToMany(mappedBy="creator", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Comment> commentSent;
	
	@OneToMany(mappedBy="receiver", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Comment> commentReceived;
	
	
	
	
	//================ Constructor =================//
	public User() {
	
	}
	
	
	

	//================ Getter&Setter =================//
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	public Date getCreateAt() {
		return createAt;
	}
	
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public Date getUpdatedAt() {
		return updatedAt;
	}
	
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	public List<Friend> getFriend() {
		return friend;
	}
	
	public void setFriend(List<Friend> friend) {
		this.friend = friend;
	}
	
	public Friend getOneFriend() {
		return oneFriend;
	}
	
	public void setOneFriend(Friend oneFriend) {
		this.oneFriend = oneFriend;
	}
	
	public Top8 getMyTop8() {
		return myTop8;
	}
	
	public void setMyTop8(Top8 myTop8) {
		this.myTop8 = myTop8;
	}
	
	public List<Top8> getTop8List() {
		return top8List;
	}
	
	public void setTop8List(List<Top8> top8List) {
		this.top8List = top8List;
	}
	
	public Blurb getBlurb() {
		return blurb;
	}
	
	public void setBlurb(Blurb blurb) {
		this.blurb = blurb;
	}
	
	public List<Message> getMessageSent() {
		return messageSent;
	}
	
	public void setMessageSent(List<Message> messageSent) {
		this.messageSent = messageSent;
	}
	
	public Message getMessageReceived() {
		return messageReceived;
	}
	
	public void setMessageReceived(Message messageReceived) {
		this.messageReceived = messageReceived;
	}
	
	public List<Comment> getCommentSent() {
		return commentSent;
	}
	
	public void setCommentSent(List<Comment> commentSent) {
		this.commentSent = commentSent;
	}
	public List<Comment> getCommentReceived() {
		return commentReceived;
	}
	
	public void setCommentReceived(List<Comment> commentReceived) {
		this.commentReceived = commentReceived;
	}
	
	public UserInfo getUserInfo() {
		return userInfo;
	}
	
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	
	
	

}