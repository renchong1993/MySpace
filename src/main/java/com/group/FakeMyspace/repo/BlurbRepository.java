package com.group.FakeMyspace.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.group.FakeMyspace.models.Blurb;
import com.group.FakeMyspace.models.User;

@Repository
public interface BlurbRepository extends CrudRepository<Blurb, Long>{
	
	Blurb findByOwner(User owner);

}
