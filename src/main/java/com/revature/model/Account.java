package com.revature.model;

//generate constructor -> getter/setter -> hashcode and equals ->string field

public class Account implements Comparable<Account> {

	// declare variables to match each column of the table in the db
	public String username;
	public String password;
	public Float balance;

	@Override
	public int compareTo(Account o) {
		// TODO Auto-generated method stub
		return new String(this.username).compareTo(o.username);
	}

	public Account(String username, String password, float balance) {
		super();
		this.username = username;
		this.password = password;
		this.balance = balance;
	}

	public Account() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(balance);
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (Float.floatToIntBits(balance) != Float.floatToIntBits(other.balance))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", balance=" + balance + ", getUsername()="
				+ getUsername() + ", getPassword()=" + getPassword() + ", getBalance()=" + getBalance()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
