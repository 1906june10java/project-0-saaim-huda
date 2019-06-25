package com.revature.repository;

import com.revature.model.Login;

/**
 *
 * This is the Account DAO (Data Access Object).
 *
 * Defines CRUD operations for this particular pojo. NO BUSINESS LOGIC SHOULD BE
 * PRESENT on these kind of Objects.
 */

public interface LoginRepository {

	public Login findByUsername(String login_username);
}
