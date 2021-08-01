package com.capgemini.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.capgemini.persistence.jdbc.Jdbc;

public class TasksRepository implements Repository {

	public TasksRepository() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int add(Object o) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(int id) throws SQLException {
		
		Connection c = null;
		PreparedStatement pst = null;
    
	
		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement("DELETE FROM \"PUBLIC\".\"TUSERS\" WHERE user_id = ?");
			

			pst.setInt(1,id);
			
			pst.executeUpdate();
			
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}


	}

	@Override
	public List<Object> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
