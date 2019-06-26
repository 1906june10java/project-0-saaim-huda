package com.revature.test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.repository.AccountRepository;
import com.revature.repository.AccountRepositoryJdbc;

public class EvaluationServiceTest {
	private static final AccountRepositoryJdbc evaluationService = new AccountRepositoryJdbc();
	AccountRepository accountRepo = new AccountRepositoryJdbc();

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	/*******************************************************************
	 * JUNIT TEST 1
	 ******************************************************************/

	@Test
	public void depositTest() throws SQLException {
		String username = "saaim";
		Float balance = 100F;
		boolean expected = true;
		assertEquals(expected, AccountRepositoryJdbc.newDeposit(username, balance));

	}

	/**********************************************************************
	 *
	 **********************************************************************/

	@Test
	public void FailedDepositTest() throws SQLException {
		String username = "saaim";
		Float balance = -100F;
		boolean expected = false;
		assertEquals(expected, AccountRepositoryJdbc.newDeposit(username, balance));
	}

	/*******************************************************************
	 * JUNIT TEST 2
	 ******************************************************************/
	@Test
	public void withdrawTest() throws SQLException {
		String username = "saaim";
		Float balance = 100F;
		boolean expected = true;
		assertEquals(expected, AccountRepositoryJdbc.withdraw(username, balance));

	}

	/**********************************************************************
	 * Test 2
	 **********************************************************************/

	@Test
	public void FailedWithdrawTest() throws SQLException {
		String username = "saaim";
		Float balance = -100F;
		boolean expected = true;
		assertEquals(expected, AccountRepositoryJdbc.withdraw(username, balance));
	}

}
