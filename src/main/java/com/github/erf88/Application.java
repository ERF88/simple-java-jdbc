package com.github.erf88;

import java.sql.SQLException;
import java.util.Scanner;

public class Application {

	private static Scanner reader;
	private static CustomerDAO dao;
	
	public static void main(String[] args) throws SQLException {

		dao = new CustomerDAO();
		dao.createTable();
		Integer op = 0;

		do {
			System.out.println("------------ MENU ------------");
			System.out.println("1 - FindAll customer");
			System.out.println("2 - FindOne Customer");
			System.out.println("3 - Create Customer");
			System.out.println("4 - Update Customer");
			System.out.println("5 - Delete Customer");
			System.out.println("0 - Exit");
			System.out.println("------------------------------");

			op = reader.nextInt();
			reader.nextLine();			

			switch (op) {
			case 1:
				
				findAll();
				break;
			case 2:
				findOne();
				break;
			case 3:
				create();
				break;
			case 4:
				update();	
				break;
			case 5:
				delete();							
				break;
			default:
				System.out.println("Invalid Option");
				break;
			}
			
		} while (op != 0);
		
	}

	private static void findAll() {

		System.out.println("Customers:");

		dao.findAll().forEach(customer -> {
			System.out.println("Id: " + customer.getId());
			System.out.println("Name: " + customer.getName());
		});
	}

	private static void findOne() {

		System.out.println("Enter customer Id:");
		
		Integer id = reader.nextInt();
		Customer customer = dao.findById(id);

		if (customer != null) {
			System.out.println("Id: " + customer.getId());
			System.out.println("Name: " + customer.getName());
		} else {
			System.out.println("Does not exists");
		}
	}

	private static void create() {

		System.out.println("Enter name:");

		String name = reader.next();
		dao.insert(new Customer(name));
	}

	private static void update() {

		System.out.println("Enter customer Id:");
		Integer id = reader.nextInt();

		if (dao.exists(id)) {
			System.out.println("Enter new customer name:");

			String name = reader.next();
			dao.update(id, new Customer(name));
		} else {
			System.out.println("Does not exists");
		}
	}

	private static void delete() {

		System.out.println("Enter customer Id:");
		Integer id = reader.nextInt();

		if (dao.exists(id)) {
			dao.remove(id);
		} else {
			System.out.println("Does not exists");
		}
	}

}
