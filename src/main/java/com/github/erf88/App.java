package com.github.erf88;

import java.sql.SQLException;
import java.util.Scanner;

public class App {

	public static void main(String[] args) throws SQLException {

		CustomerDAO dao = new CustomerDAO();
		dao.createTable();
		Integer choice = 0;

		while (choice != 6) {

			System.out.println("------------ MENU ------------");
			System.out.println("1 - List customers");
			System.out.println("2 - Find customer");
			System.out.println("3 - Create customer");
			System.out.println("4 - Update customer");
			System.out.println("5 - Delete customer");
			System.out.println("6 - Exit");
			System.out.println("------------------------------");

			Scanner scanner = new Scanner(System.in);
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				findAll(dao);
				break;
			case 2:
				findOne(scanner, dao);
				break;
			case 3:
				create(scanner, dao);
				break;
			case 4:
				update(scanner, dao);
				break;
			case 5:
				delete(scanner, dao);
				break;
			case 6:
				choice = 6;
				break;
			default:
				System.out.println("Invalid Option");
			}

		}
	}

	private static void findAll(CustomerDAO dao) {

		System.out.println("Customers:");

		dao.findAll().forEach(customer -> {
			System.out.println("Id: " + customer.getId());
			System.out.println("Name: " + customer.getName());
		});
	}

	private static void findOne(Scanner scanner, CustomerDAO dao) {

		System.out.println("Enter customer Id:");
		
		Integer id = scanner.nextInt();
		Customer customer = dao.findById(id);

		if (customer != null) {
			System.out.println("Id: " + customer.getId());
			System.out.println("Name: " + customer.getName());
		} else {
			System.out.println("Does not exists");
		}
	}

	private static void create(Scanner scanner, CustomerDAO dao) {

		System.out.println("Enter name:");

		String name = scanner.next();
		dao.insert(new Customer(name));
	}

	private static void update(Scanner scanner, CustomerDAO dao) {

		System.out.println("Enter customer Id:");
		Integer id = scanner.nextInt();

		if (dao.exists(id)) {
			System.out.println("Enter new customer name:");

			String name = scanner.next();
			dao.update(id, new Customer(name));
		} else {
			System.out.println("Does not exists");
		}
	}

	private static void delete(Scanner scanner, CustomerDAO dao) {

		System.out.println("Enter customer Id:");
		Integer id = scanner.nextInt();

		if (dao.exists(id)) {
			dao.remove(id);
		} else {
			System.out.println("Does not exists");
		}
	}

}
