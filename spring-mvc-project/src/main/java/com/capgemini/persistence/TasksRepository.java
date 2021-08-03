package com.capgemini.persistence;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;


import com.capgemini.persistence.dto.TaskDto;
import com.capgemini.persistence.jdbc.Jdbc;

@Repository
public class TasksRepository implements com.capgemini.persistence.Repository {


	@Override
	public int add(Object o) throws SQLException {
		
		TaskDto task = (TaskDto) o;
		  
		
		
		Connection c = null;
		PreparedStatement pst = null;
  
		
	
		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement("INSERT INTO \"PUBLIC\".\"TTASKS\"(comments,created,finished,planned,title,category_id,user_id) VALUES(?,?,?,?,?,?,?)");
		
			pst.setString(1, null);
			pst.setDate(2, (java.sql.Date) new Date());
			pst.setDate(3, null);
			pst.setDate(4, null);
			pst.setString(5, task.title);
			pst.setInt(6,task.categoryId);
			pst.setInt(7,task.userId);
			

			pst.executeUpdate();
			
			
			
		

			

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
		}
		return 0;
	}
	
	@Override
	public void delete(int id) throws SQLException {
		
		Connection c = null;
		PreparedStatement pst = null;
    
	
		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement("DELETE FROM \"PUBLIC\".\"TTASKS\" WHERE user_id = ?");
			

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
