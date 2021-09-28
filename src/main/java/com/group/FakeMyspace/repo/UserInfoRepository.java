package com.group.FakeMyspace.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.group.FakeMyspace.models.User;
import com.group.FakeMyspace.models.UserInfo;

@Repository
public interface UserInfoRepository extends CrudRepository<UserInfo, Long>{
	
	UserInfo findByOwner(User owner);

}
