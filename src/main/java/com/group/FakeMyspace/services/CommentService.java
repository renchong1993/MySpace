package com.group.FakeMyspace.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.FakeMyspace.models.Comment;
import com.group.FakeMyspace.repo.CommentRepository;


@Service
public class CommentService {
	@Autowired
	private CommentRepository cRepo;
	
	public Comment findById(Long id) {
		return this.cRepo.findById(id).orElse(null);
	}
	
	public Comment create(Comment comment) {
		return this.cRepo.save(comment);
	}
	
	public Comment update(Comment comment) {
		return this.cRepo.save(comment);
	}
	
	public void delete(Long id) {
		this.cRepo.deleteById(id);
	}
}