package com.jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class jdbc_properties {

	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		
		//read the file
		FileInputStream fileInputStream = new FileInputStream("connection.properties");
		
		//load the properties file
		Properties properties = new Properties();
		properties.load(fileInputStream);
		
		//get the poperties value
		String driver = properties.getProperty("driver");
		String url = properties.getProperty("url");
		String userName = properties.getProperty("userName");
		String password = properties.getProperty("password");
		
		//load the driver
		Class.forName(driver);
		
		//register the driver
		Connection connection = DriverManager.getConnection(url, userName, password);
		
		//create the query
		Statement statement = connection.createStatement();
		int a = statement.executeUpdate("create table emp(eid number, ename varchar2(10), esal float)");
		
		System.out.println("table created sucessfully "+a);
		
		//insert values into the table
		PreparedStatement preparedStatement = connection.prepareStatement("insert into emp values(?,?,?)");
		
		Scanner sc = new Scanner(System.in);
		
		while(true){
			System.out.println("enter employee id: ");
			int eid = sc.nextInt();
			
			System.out.println("enter employee name: ");
			String ename = sc.next();
			
			System.out.println("enter employee salary");
			int esal = sc.nextInt();
			
			preparedStatement.setInt(1, eid);
			preparedStatement.setString(2, ename);
			preparedStatement.setInt(3, esal);
			
			preparedStatement.executeUpdate();
			
			System.out.println("do you want to enter more records yes/no?");
			String answer = sc.next();
			
			if(answer.equals("no")){
				break;
			}
		}
		
		
		PreparedStatement preparedStatement2= connection.prepareStatement("select *from emp");
		ResultSet resultSet =preparedStatement2.executeQuery();
		
		while(resultSet.next()){
			System.out.println(resultSet.getInt(1)+"----"+resultSet.getString(2)+"----"+resultSet.getInt(3));
		}
		
		connection.close();
		
		
		
		
		
	}

}
