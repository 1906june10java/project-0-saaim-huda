package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.model.Account;
import com.revature.util.ConnectionUtil;

public class AccountRepositoryJdbc implements AccountRepository {

	// logger for 4j
	private static final Logger LOGGER = Logger.getLogger(AccountRepositoryJdbc.class);

	Account account = new Account();

	@Override
	public void create() throws SQLException {
		String userPass = "";
		String userPass2 = "0";
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a username: ");
		String userNameInput = input.nextLine();
		do {
			System.out.println("Please enter a password");
			userPass = input.nextLine();
			System.out.println("Please reenter the password");
			userPass2 = input.nextLine();
		} while (!(userPass.equals(userPass2)));
		Float initBalance = new Float(0.0);
		String sql = "INSERT INTO ACCOUNT (A_USERNAME, A_PASSWORD,A_BALANCE) VALUES ('" + userNameInput + " ', '"
				+ userPass + "','" + initBalance + "')";
		Connection connection = ConnectionUtil.getConnection();
		Statement s = connection.createStatement();
		s.execute(sql);

	}

	@Override
	public void login() throws SQLException {
		// LOGGER.trace("Entering Logging functionality");
		ResultSet rs = null;
		Scanner input = new Scanner(System.in);
		System.out.print("Username:");
		String userNameInput = input.nextLine();
		System.out.print("Password:");
		String userPassInput = input.nextLine();
		String sql = "SELECT * FROM ACCOUNT WHERE A_USERNAME = ? AND A_PASSWORD = ?";
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, userNameInput);
		statement.setString(2, userPassInput);
		rs = statement.executeQuery();
		while (rs.next()) {
			String user_name = rs.getString("A_USERNAME");
			String user_pass = rs.getString("A_PASSWORD");
			Float user_Bal = rs.getFloat("A_BALANCE");
			account.username = user_name;
			account.password = user_pass;
			account.balance = user_Bal;
			showMenu(account.username, account.balance);
			// rs.close();

			if (account.password != null || account.password.equals(userPassInput)) {
				showMenu(account.username, account.balance);
			} else {
				System.out.println("Invalid username or password");
			}

		}

	}

	public void showMenu(String chkUsername, Float userBalance) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Login Sucessful");
		System.out.println("Welcome to Self Bank");
		char selection = '0';

		do {
			System.out.println("1. Withdraw");
			System.out.println("2. Balance");
			System.out.println("3. Deposit");
			System.out.println("4. Log out");

			System.out.print("type an option to continue: ");

			selection = scanner.next().charAt(0);

			switch (selection) {
			case '1':
				withdraw(chkUsername, userBalance);
				break;
			case '2':
				balance(chkUsername);
				break;
			case '3':
				newDeposit(chkUsername, userBalance);
				break;
			case '4':
				break;
			default:
				System.out.println("Try Again! Option Not Valid");

			}
		} while (selection != '4');
		System.out.println("Please Log In to Continue");
	}

	@Override
	public void deposit() throws SQLException {
	}

	public static boolean newDeposit(String chkUsername, Float userBalance) throws SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			// System.out.println("printing newDeposit " + userBalance);
			Scanner input = new Scanner(System.in);
			System.out.print("Confirm your username to continue: ");
			chkUsername = input.nextLine();
			System.out.println("Enter amount to deposit");
			Scanner amountInput = new Scanner(System.in);
			userBalance = Float.valueOf(amountInput.nextLine());
			int parameterIndex = 0;
			String sql = "";
			sql = "UPDATE ACCOUNT SET A_BALANCE = (A_BALANCE + ?) WHERE A_USERNAME = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setFloat(++parameterIndex, userBalance);
			statement.setString(++parameterIndex, chkUsername);
			// statement.executeUpdate();
			// System.out.println("Amount Deposited " + userBalance);

			if (statement.executeUpdate() > 0) {
				System.out.println("Amount Deposited " + userBalance);
				return true;
			}

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("catch statement from deposit");
			return false;
		}
		return false;
	}

	public static boolean withdraw(String chkUsername, Float userBalance) throws SQLException {
		try (Connection connection = ConnectionUtil.getConnection()) {
			Scanner input = new Scanner(System.in);
			System.out.print("Confirm your username to continue: ");
			chkUsername = input.nextLine();
			System.out.println("Enter amount to withdraw");
			Scanner amountInput = new Scanner(System.in);
			userBalance = Float.valueOf(amountInput.nextLine());
			int parameterIndex = 0;
			String sql = "";
			sql = "UPDATE ACCOUNT SET A_BALANCE = (A_BALANCE - ?) WHERE A_USERNAME = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setFloat(++parameterIndex, userBalance);
			statement.setString(++parameterIndex, chkUsername);
			// statement.executeUpdate();
			// System.out.println("Amount Deposited " + userBalance);

			if (statement.executeUpdate() > 0) {
				System.out.println("Amount Withdrawn " + userBalance);
				return true;
			}

		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("Positive Number should be inputted");
			return false;
		}
		return false;
	}

	public void balance(String chkUsername) throws SQLException {
		// PreparedStatement statement = connection.prepareStatement(sql);
		PreparedStatement statment = null;
		ResultSet rs = null;
		String sql = "SELECT A_BALANCE FROM ACCOUNT WHERE A_USERNAME = ?";
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, chkUsername);
		rs = statement.executeQuery();
		while (rs.next()) {

			Float balance = rs.getFloat("A_BALANCE");
			System.out.println("Your balance is: " + balance);
			account.balance = balance;
		}
	}

}