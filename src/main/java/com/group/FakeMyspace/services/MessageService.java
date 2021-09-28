package com.group.FakeMyspace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.FakeMyspace.models.Message;
import com.group.FakeMyspace.repo.MessageRepository;

@Service
public class MessageService {
	@Autowired
	private MessageRepository mRepo;
	
	public Message findById(Long id) {
		return this.mRepo.findById(id).orElse(null);
	}
	
	public Message create(Message message) {
		return this.mRepo.save(message);
	}
	
	public Message update(Message message) {
		return this.mRepo.save(message);
	}
	
	public void delete(Long id) {
		this.mRepo.deleteById(id);
	}
}