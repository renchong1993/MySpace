package com.group.FakeMyspace.services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.FakeMyspace.models.Blurb;
import com.group.FakeMyspace.models.User;
import com.group.FakeMyspace.models.UserInfo;
import com.group.FakeMyspace.repo.BlurbRepository;
import com.group.FakeMyspace.repo.UserInfoRepository;
import com.group.FakeMyspace.repo.UserRepository;


@Service
public class UServ {

	
	@Autowired
	private BlurbRepository bRepo;
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private UserInfoRepository iRepo;
	
	//================ Register =================//
	public User registerUser(User user) {
	    String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
	    user.setPassword(hashed);
	    
	    return uRepo.save(user);
	}


	public User findByEmail(String email) {
	    return uRepo.findByEmail(email);
	}


	public User findUserById(Long id) {
		Optional<User> u = uRepo.findById(id);
		
		if(u.isPresent()) {
	        return u.get();
		} else {
		    return null;
		}
	}

	//================ Login =================//
	public boolean authenticateUser(String email, String password) {

	    User user = uRepo.findByEmail(email);
	    if(user == null) {
	        return false;
	    } else {
	        if(BCrypt.checkpw(password, user.getPassword())) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	}

	
	//================ Miscellaneous =================//
	public List<User> allUser(){
		return this.uRepo.findAll();
	}


	public Blurb findBlurd(User owner) {
		return this.bRepo.findByOwner(owner);
	}

//	*********************************************************
	//Create UserInfo
	public UserInfo createUserInfo(String gender, int age, String location, String image_url, User owner) {
		UserInfo uInfo = new UserInfo(gender, age, location, image_url, owner);
		return this.iRepo.save(uInfo);
	}
//	*********************************************************
	//Read UserInfo
	public UserInfo findSingleUserInfo(User owner) {
		return this.iRepo.findByOwner(owner);
	}
//	*********************************************************
	//Update UserInfo
	public void updateUserInfo(String gender, int age, String location, String image_url, User owner) {
		UserInfo uInfo = findSingleUserInfo(owner);
		uInfo.setGender(gender);
		uInfo.setAge(age);
		uInfo.setLocation(location);
		uInfo.setImage_url(image_url);
		
		this.iRepo.save(uInfo);
	}

//	*********************************************************

		
		
	
}
