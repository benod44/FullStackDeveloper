package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class practice_JDBC {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, InterruptedException {

		// load the driver
		Class.forName("oracle.jdbc.driver.OracleDriver");

		// register the driver
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@Localhost:1521:xe", "system", "1234");

		// write the query
		Statement statement = connection.createStatement();
		int a = statement.executeUpdate("create table emp(eid number, ename varchar2(10), esal float)");
		System.out.println("table created sucessfully " + a);

		// insert into table at runtime
		PreparedStatement preparedStatement = connection.prepareStatement("insert into emp values(?,?,?)");

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("insert employee id: ");
			int eid = sc.nextInt();

			System.out.println("insert employee name: ");
			String ename = sc.next();

			System.out.println("insert employee salary ");
			int esal = sc.nextInt();

			preparedStatement.setInt(1, eid);
			preparedStatement.setString(2, ename);
			preparedStatement.setInt(3, esal);

			int b = preparedStatement.executeUpdate();

			System.out.println("records insersted sucessfully " + b);

			System.out.println("do you want to enter more records yes/no? ");
			String answer = sc.next();

			if (answer.equals("no")) {
				break;

			}
		}

		Thread.sleep(10000);
		// update the table

		PreparedStatement preparedStatement1 = connection.prepareStatement("update emp set ename=? where ename=?");
		preparedStatement1.setString(1, "pig");
		preparedStatement1.setString(2, "ben");
		int c = preparedStatement1.executeUpdate();

		PreparedStatement preparedStatement2 = connection.prepareStatement("update emp set esal=esal+? where esal>?");
		preparedStatement2.setInt(1, 44);
		preparedStatement2.setInt(2, 4000);
		preparedStatement2.executeUpdate();

		System.out.println("record updated sucessully " + c);

		Thread.sleep(2000);
		// delete the record
		PreparedStatement preparedStatement3 = connection.prepareStatement("delete from emp where eid=?");
		preparedStatement3.setInt(1, 400);
		int d = preparedStatement3.executeUpdate();

		System.out.println("record deleted sucessfully " + d);

		// retriving the record from db
		PreparedStatement preparedStatement4 = connection.prepareStatement("select *from emp");
		ResultSet resultSet = preparedStatement4.executeQuery();

		while (resultSet.next()) {
			System.out.println(resultSet.getInt(1) + "--" + resultSet.getString(2) + "--" + resultSet.getInt(3));
		}
		
		connection.close();

	}

}
