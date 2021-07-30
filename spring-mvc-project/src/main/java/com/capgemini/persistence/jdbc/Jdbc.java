package com.capgemini.persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {


	public static Connection getConnection() {


		Connection con = null;

		try {
			
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/", "SA", "");

		}  catch (Exception e) {
			e.printStackTrace(System.out);
		}
		return con;
	}
	
	public void close(Connection c) throws SQLException {
		
		c.close();
	}

	





}

