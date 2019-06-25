package com.revature.model;

public class Login implements Comparable<Login> {
	private String login_username;
	private String login_password;

	// generate constructor
	public Login(String login_username, String login_password) {
		super();
		this.login_username = login_username;
		this.login_password = login_password;
	}

	public Login() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login_password == null) ? 0 : login_password.hashCode());
		result = prime * result + ((login_username == null) ? 0 : login_username.hashCode());
		return result;
	}

	public String getLogin_username() {
		return login_username;
	}

	public void setLogin_username(String login_username) {
		this.login_username = login_username;
	}

	public String getLogin_password() {
		return login_password;
	}

	public void setLogin_password(String login_password) {
		this.login_password = login_password;
	}

	// generate hashcode and equal
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		if (login_password == null) {
			if (other.login_password != null)
				return false;
		} else if (!login_password.equals(other.login_password))
			return false;
		if (login_username == null) {
			if (other.login_username != null)
				return false;
		} else if (!login_username.equals(other.login_username))
			return false;
		return true;
	}
	// generate string

	@Override
	public String toString() {
		return "Login [login_username=" + login_username + ", login_password=" + login_password + "]";
	}

	@Override
	public int compareTo(Login o) {
		return new String(this.login_username).compareTo(o.login_username);
	}
}
