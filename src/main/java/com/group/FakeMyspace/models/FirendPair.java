package com.group.FakeMyspace.models;

import javax.persistence.Entity;
import javax.persistence.Table;

//@Entity
//@Table(name="friendpairs")
public class FirendPair {

	private Long userId1;
	
	private Long userId2;

	public Long getUserId1() {
		return userId1;
	}

	public void setUserId1(Long userId1) {
		this.userId1 = userId1;
	}

	public Long getUserId2() {
		return userId2;
	}

	public void setUserId2(Long userId2) {
		this.userId2 = userId2;
	}
	
	public int validatefriend() {
		try {
			this.getUserId1();
			this.getUserId2();
			return 1;
		}
		
		catch(Exception e) {
			return -1;
		}
	}
}
