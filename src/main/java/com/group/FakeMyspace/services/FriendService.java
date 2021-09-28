package com.group.FakeMyspace.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.FakeMyspace.models.Friend;
import com.group.FakeMyspace.models.Top8;
import com.group.FakeMyspace.models.User;
import com.group.FakeMyspace.repo.FriendRepository;
import com.group.FakeMyspace.repo.Top8Repository;

@Service
public class FriendService {
	@Autowired
	private FriendRepository fRepo;
	
	@Autowired
	private Top8Repository tRepo;
	
	public List<Friend> getAllFriends(){
		return this.fRepo.findAll();
	}
	
	public Top8 findTopEight(User owner){
		return this.tRepo.findByOwner(owner);
	}
	
	public Friend findbyId(Long id) {
		return this.fRepo.findById(id).orElse(null);
	}
	
	public Friend create(Friend friend) {
		return this.fRepo.save(friend);
	}
	
	public Friend update(Friend friend) {
		return this.fRepo.save(friend);
	}
	
	public void delete(Long id) {
		this.fRepo.deleteById(id);
	}
	
	//Connect Friends by create a instance and wait for approve
	public Friend requestFriend(User owner, User oneUser) {
		Friend newFrd = new Friend(owner, oneUser);
		newFrd.setOwner(owner);
		newFrd.setOneUser(oneUser);
		
		return this.fRepo.save(newFrd);
	}
	
	//find single friend request
	public Friend findSingleRequest(User sender, User receiver) {
		return this.fRepo.findByOwnerAndOneUser(sender, receiver);
	}
	
	
	//find all friend request
	public List<Friend> findAllRequest(User receiver){
		return this.fRepo.findAllByOneUser(receiver);
	}
	
	
	
	//approve sender request, create a reverse request from receiver and set approve
	public void acceptFriendRequest(User sender, User receiver) {
		Friend fRequest = findSingleRequest(sender, receiver);
		fRequest.setApprove(true);
		this.fRepo.save(fRequest);
		
		Friend revFrnd = requestFriend(receiver, sender);
		revFrnd.setApprove(true);
		this.fRepo.save(revFrnd);
		
	}
}
