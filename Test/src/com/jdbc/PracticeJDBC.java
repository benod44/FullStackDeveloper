package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class PracticeJDBC {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException   {
		
	//loading driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//register the driver
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@Localhost:1521:xe", "system", "1234");
		
		//to execute the query
		Statement statement = connection.createStatement();
		
		//scanner to read input
		Scanner sc= new Scanner(System.in);
		
		System.out.println("create a table name: ");
		String tname = sc.next();
		
		
		int a = statement.executeUpdate("create table "+tname+"(eid number, ename varchar2(10), esal number)");
		
		System.out.println("table created sucessfully "+a);
		
		//inserting values into the table
		System.out.println();
		
		System.out.println("inserting values in the table");
		
		while(true){
			System.out.println("input the employee id:");
			int eid = sc.nextInt();
			
			System.out.println("input employee name:");
			String ename = sc.next();
			
			System.out.println("input the employee salary");
			int esal = sc.nextInt();
			
			int b = statement.executeUpdate("INSERT INTO emp values ("+eid+", '"+ename+"' ,"+esal+")");
			
			System.out.println("records inserted sucessfully "+ b);
			
			System.out.println("do you want to ennter one more records yes/no? ");
			String answer = sc.next();
			
			if(answer.equals("no")){
				break;
			}
		}
		
		System.out.println("select table to retrive info");
		String db = sc.next();
		
		
		ResultSet resultSet = statement.executeQuery("select *from "+db);
		while (resultSet.next()){
			System.out.println(resultSet.getInt(1)+"----"+resultSet.getString(2)+"----"+resultSet.getInt(3));
		}
		
		Thread.sleep(10000);
		
		System.out.println("enter the table name to drop");
		String drop = sc.next();
		
		int d = statement.executeUpdate("drop table "+drop);
		System.out.println("table dropped sucessfully "+d);
		
		
	}

}
