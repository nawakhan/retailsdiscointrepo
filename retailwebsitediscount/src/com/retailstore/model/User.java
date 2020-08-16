package com.retailstore.model;

import java.time.LocalDateTime;

/**
 * User class with required fields
 * 
 * @author nawazish
 *
 */
public class User {

	private String userid;
	private String userType;
	private LocalDateTime joiningDateTime;

	public User(String userid, String userType) {
		this.userid = userid;
		this.userType = userType;
	}

	public User(String userid, String userType, LocalDateTime joiningDateTime) {
		this.userid = userid;
		this.userType = userType;
		this.joiningDateTime = joiningDateTime;
	}

	public String getUserid() {
		return userid;
	}

	public String getUserType() {
		return userType;
	}

	public LocalDateTime getJoiningDateTime() {
		return joiningDateTime;
	}

	public void setJoiningDateTime(LocalDateTime joiningDateTime) {
		this.joiningDateTime = joiningDateTime;
	}

}
