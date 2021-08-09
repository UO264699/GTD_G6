package com.capgemini.persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {


	/**
	 * M�todo que realiza la conexi�n con la base de datos
	 * 
	 * @return conexi�n
	 */
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
	
	/**
	 * M�todo que cierra la conexi�n
	 * 
	 * @param c conexi�n 
	 * @throws SQLException
	 */
	public void close(Connection c) throws SQLException {
		
		c.close();
	}

	





}

