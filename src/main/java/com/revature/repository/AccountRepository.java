package com.revature.repository;

import java.sql.SQLException;

/**
 *
 * This is the Account DAO (Data Access Object).
 *
 * Defines CRUD operations for this particular pojo. NO BUSINESS LOGIC SHOULD BE
 * PRESENT on these kind of Objects.
 */

public interface AccountRepository {
	public void create() throws SQLException;

	public void login() throws SQLException;

	public void deposit() throws SQLException;

	// public Account findByName(String username);
	// public List<Account> findAll();

	/*
	 * public boolean create(Account account);
	 * 
	 * public boolean createSecure(Account account);
	 * 
	 * public Account findByName(String username);
	 * 
	 * public List<Account> findAll();
	 */
}
