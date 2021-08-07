package com.capgemini.persistence.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Jdbc {


	/**
	 * Método que realiza la conexión con la base de datos
	 * 
	 * @return conexión
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
	 * Método que cierra la conexión
	 * 
	 * @param c conexión 
	 * @throws SQLException
	 */
	public void close(Connection c) throws SQLException {
		
		c.close();
	}

	





}

