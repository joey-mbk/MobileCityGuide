package com.mobilecityguide.controllers;

import com.mobilecityguide.models.User;

public class UserController {

	private static User activeUser;

	public static User getActiveUser() {
		return activeUser;
	}

	public void setActiveUser(User activeUser) {
		UserController.activeUser = activeUser;
	}
}
