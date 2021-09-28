package com.group.FakeMyspace.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.FakeMyspace.models.Blurb;
import com.group.FakeMyspace.models.Comment;
import com.group.FakeMyspace.models.Top8;
import com.group.FakeMyspace.models.User;
import com.group.FakeMyspace.repo.BlurbRepository;
import com.group.FakeMyspace.repo.CommentRepository;
import com.group.FakeMyspace.repo.FriendRepository;
import com.group.FakeMyspace.repo.Top8Repository;
import com.group.FakeMyspace.repo.UserInfoRepository;
import com.group.FakeMyspace.repo.UserRepository;

@Service
public class MySpaceService {
	
	@Autowired
	public FriendRepository fRepo;
	@Autowired
	public CommentRepository cRepo;
	@Autowired
	private Top8Repository tRepo;
	@Autowired
	private UserRepository uRepo;
	@Autowired
	private BlurbRepository bRepo;
	@Autowired
	private UserInfoRepository iRepo;
	
	//========== User ========//
	//Top8 List
	public Top8 findTopEightOfOwner(User owner){
		return this.tRepo.findByOwner(owner);
	}
	//Add Top8 List
	public void addFriendToTop8(User owner, User frnd){
		Top8 ownerTop8 = owner.getMyTop8();
		List<User> frndList = ownerTop8.getTop8Frnd();
		frndList.add(frnd);
		this.uRepo.save(owner);
	}
	
	//Remove Top8 List
	public void removeFriendFromTop8(User owner, User frnd){
		Top8 ownerTop8 = owner.getMyTop8();
		List<User> frndList = ownerTop8.getTop8Frnd();
		frndList.remove(frnd);
		this.uRepo.save(owner);
	}
	
	
		
	//========== Blurb ========//
	//Create Blurb
	public Blurb createSingleBlurb(Blurb newBlurb) {
		return this.bRepo.save(newBlurb);
	}
	
	public Blurb createDefaultBlurb(String about, String meet, String quote, User owner) {
		Blurb defBlurb = new Blurb(about, meet, quote, owner);
		return this.bRepo.save(defBlurb);
	}
	
	
	//Find a Blurb
	public Blurb findSingleBlurb(Long uid) {
		User user = this.uRepo.findById(uid).orElse(null);
		return this.bRepo.findByOwner(user);
	}
	

//	*********************************************************
	
	//Update Blurb
//	public void createOrEditBlurb(Long uid, String about, String meet, String quote, String location, String image_url, User owner) {
//		Blurb blurb = findSingleBlurb(uid);
//		blurb.setAbout(about);
//		blurb.setMeet(meet);
//		blurb.setQuote(quote);
//		blurb.setLocation(location);
//		blurb.setImage_url(image_url);
//		blurb.setOwner(owner);
//		
//		this.bRepo.save(blurb);
//	}
	
	public Blurb updateBlurb(Blurb blurb) {
		return this.bRepo.save(blurb);
	}
//	*********************************************************
	
	
	
	//========== Comment ========//
	//Create
	public Comment createComm(Comment newComment) {
		return this.cRepo.save(newComment);
	}
//	*********************************************************
//	//Read Single Comment
//	*********************************************************
//	
//	*********************************************************
//	//Delete single comment
//	*********************************************************
	
	//Show All
	public List<Comment> showAllComments(User receiver){
		return this.cRepo.findAllByReceiver(receiver);
	}

}
