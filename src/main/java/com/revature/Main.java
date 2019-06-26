package com.revature;

import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.repository.AccountRepository;
import com.revature.repository.AccountRepositoryJdbc;

/**
 * Create an instance of your controller and launch your application.
 * 
 * Try not to have any logic at all on this class.
 */
public class Main {
	private static final Logger LOGGER = Logger.getLogger(AccountRepositoryJdbc.class);

	public static void main(String[] args) {
		showMainMenu();
	}

	public static void showMainMenu() {
		char selection = '0';
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("Welcome to Self Bank");
			System.out.println("1. Create Account ");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.println("Select an option");
			selection = scanner.next().charAt(0);

			switch (selection) {
			case '1':
				try {
					AccountRepository accountRepo = new AccountRepositoryJdbc();
					accountRepo.create();
				} catch (SQLException e) {
					LOGGER.error("Account cannot be created", e);
				}
				break;

			case '2':

				try {
					AccountRepository accountRepo = new AccountRepositoryJdbc();
					accountRepo.login();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					LOGGER.error("login cannot be created", e);
				}
				break;

			}
		} while (selection != '3');
		System.out.println("Thank you for choosing us. See you again");
		scanner.close();

	}

}
