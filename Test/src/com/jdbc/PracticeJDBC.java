package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PracticeJDBC {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@Localhost:1521:xe", "System", "1234");
		
		String q1 = "create table emp (eid number, ename varchar2(40), esal number;)";
		
		Statement statement = connection.createStatement();
		statement.executeUpdate(q1);
		
				
	}

}
