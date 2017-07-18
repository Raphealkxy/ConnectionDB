package com.timmy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	 private static final String url="jdbc:mysql://127.0.0.1:3306/user?characterEncoding=utf8&useSSL=false";
	 private static final String name="root";
	 private static final String password="kxy1277609958";

	// private Connection connection=null;
	 static
	 {
		 
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//connection=DriverManager.getConnection(url, name, password);

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			
	 }
	 public static Connection connect() throws SQLException
	 {
		 
		 return DriverManager.getConnection(url, name, password);
	 }
	   

	   
}
