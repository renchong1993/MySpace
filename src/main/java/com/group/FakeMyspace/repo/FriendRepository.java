package com.group.FakeMyspace.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.group.FakeMyspace.models.Friend;
import com.group.FakeMyspace.models.User;

@Repository
public interface FriendRepository extends CrudRepository<Friend, Long>{
	
	List<Friend> findAllByOwner(User owner);
	
	
//	List<Friend> findAllByOwnerAndTopEightTrue(User owner);
	
	List<Friend> findAll();
	
	Friend findByOwnerAndOneUser(User owner, User oneUser);
	
	
	//return all friend request to this user
	List<Friend> findAllByOneUser(User oneUser);

	
	//return all pending friend request to this user? Need to confirm the default value is null, else False
	List<Friend> findAllByOneUserAndApproveNull(User oneUser);
}
