package com.p4u.core.beans;

import com.p4u.core.model.User;

public class LoginResult {

	private boolean logged;
	
	private User user;

	public LoginResult(boolean logged, User user) {
		super();
		this.logged = logged;
		this.user = user;
	}

	public boolean isLogged() {
		return logged;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
