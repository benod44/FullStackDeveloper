package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PracticeJDBC {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@Localhost:1521:xe", "system", "1234");
		
		//table creation 
		String q1 = "create table emp (eid number, ename varchar2(10), esal number)";
		
		Statement statement = connection.createStatement();
		int a =statement.executeUpdate(q1);
		System.out.println("table created "+a);
		
		//values inserted into the emp table
		String q2 = "INSERT INTO emp values (444, 'ben', 7000)";
		String q3 = "INSERT INTO emp values(888, 'hen', 4000)";
		int x =statement.executeUpdate(q2);
		statement.executeUpdate(q3);
		System.out.println("record inserted "+x);
		
		//updating emp table
		String q4 = "update  emp SET esal= esal+2000 where eid=888";
		String q5 = "update  emp SET ename='den' where eid=888";
		statement.executeUpdate(q4);
		int n=	statement.executeUpdate(q5);
		System.out.println("row updated "+n);
		
		//retriving data from emp table
		String q6 = "select *from emp";
		
		 ResultSet resultSet = statement.executeQuery(q6);
		 
		 while(resultSet.next()){
			 
			 System.out.println(resultSet.getInt(1)+"----"+resultSet.getString(2)+"--"+resultSet.getInt(3));
			 
			 
		 }
		 
		 Thread.sleep(10000);
		 
		 String q7 = "drop table emp";
		 int d =statement.executeUpdate(q7);
		 System.out.println("table dropped "+d);
		
		
		
		
				
	}

}
