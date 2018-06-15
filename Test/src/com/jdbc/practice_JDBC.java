package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class practice_JDBC {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		//load the driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//register with driver
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@Localhost:1521:xe", "system", "1234");
		
		//to execute query
		Statement statement = connection.createStatement();
		int a = statement.executeUpdate("create table emp (eid number, ename varchar2(10), esal float)");
		
		System.out.println("table created sucessfully "+a);
		
		PreparedStatement preparedStatement = connection.prepareStatement("insert into emp values(?,?,?)");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("inserting values into the table");
		
		while(true){
			System.out.println("insert employee id: ");
			int eid = sc.nextInt();
			
			System.out.println("insert employee name: ");
			String ename = sc.next();
			
			System.out.println("insert employee salary");
			int esal = sc.nextInt();
			
			preparedStatement.setInt(1, eid);
			preparedStatement.setString(2, ename);
			preparedStatement.setInt(3, esal);
			
		int b =	preparedStatement.executeUpdate();
			
			System.out.println("insert sucessfull "+b);
			
			System.out.println("do you want to enter another record yes/no?");
			String answer = sc.next();
			
			if(answer.equals("no")){
				break;
			}
			
			
			
			
		}
				
		connection.close();
		
		
	}
	
	}
