package com.mobilecityguide.controllers;

import com.mobilecityguide.models.User;

public class UserController {

	private User activeUser;

	public User getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(User activeUser) {
		this.activeUser = activeUser;
	}
}
