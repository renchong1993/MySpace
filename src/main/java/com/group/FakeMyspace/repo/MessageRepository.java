package com.group.FakeMyspace.repo;


import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.group.FakeMyspace.models.Message;



@Repository
public interface MessageRepository extends CrudRepository<Message, Long>{
	List<Message> findAll();


}
