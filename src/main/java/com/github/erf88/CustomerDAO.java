package com.github.erf88;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
	
	private final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS customer (id INTEGER NOT NULL AUTO_INCREMENT, name VARCHAR(255) NOT NULL, PRIMARY KEY (id))";
	private final String FIND_ALL = "SELECT * FROM customer";
	private final String FIND_BY_ID = "SELECT * FROM customer WHERE id = ?";
	private final String INSERT = "INSERT INTO customer (name) VALUES (?)";
	private final String UPDATE = "UPDATE customer SET name = ? WHERE id = ?";
	private final String DELETE = "DELETE FROM customer WHERE id = ?";
	private Connection connection = null;
	private PreparedStatement statement = null;
	
	public CustomerDAO() {
		this.connection = new ConnectionFactory().getConnection();				
	}
	
	public void createTable() {
		
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate(CREATE_TABLE);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
	}
	
	public List<Customer> findAll() {		
		
		List<Customer> customers = new ArrayList<Customer>();
		
		try {
			
			this.statement = connection.prepareStatement(FIND_ALL);
			ResultSet res = statement.executeQuery();
			
			while(res.next()) {
				customers.add(new Customer(res.getInt("id"), res.getString("name")));
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customers;
		
	}
	
	public Customer findById(Integer id) {
		
		Customer customer = null;
		
		try {
			this.statement = connection.prepareStatement(FIND_BY_ID);
			this.statement.setInt(1, id);
			
			ResultSet res = this.statement.executeQuery();
			
			while(res.next()) {
				customer = new Customer(res.getInt("id"), res.getString("name"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customer;
	}
	
	public void insert(Customer customer) {
				
		try {
			
			this.statement = connection.prepareStatement(INSERT);
			this.statement.setString(1, customer.getName());
			this.statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void update(Integer id, Customer customer) {
		
		try {
			
			this.statement = connection.prepareStatement(UPDATE);
			this.statement.setString(1, customer.getName());
			this.statement.setInt(2, id);
			this.statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void remove(Integer id) {
		
		try {
			
			this.statement = connection.prepareStatement(DELETE);
			this.statement.setInt(1, id);
			this.statement.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public Boolean exists(Integer id) {
		return findById(id) != null ? Boolean.TRUE : Boolean.FALSE;
	}

}
