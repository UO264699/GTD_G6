package com.capgemini.persistence;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		
			Date d = new Date();
			
			
			
			pst.setString(1, "");
			pst.setDate(2, new java.sql.Date(d.getTime()));
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
	

	public void deleteByCategoryId(int categoryid) throws SQLException {
		
		Connection c = null;
		PreparedStatement pst = null;
    
	
		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement("DELETE FROM \"PUBLIC\".\"TTASKS\" WHERE category_id = ?");
			

			pst.setInt(1,categoryid);
			
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
		List<Object> listTasks = new ArrayList<Object>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement("SELECT * FROM \"PUBLIC\".\"TTASKS\"");
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				
				TaskDto t = new TaskDto();
				
				t.id = rs.getInt("id");
				t.categoryId = rs.getInt("category_id");
				t.userId = rs.getInt("user_id");
				t.comments = rs.getString("comments");
				t.created = rs.getDate("created");
				t.finished = rs.getDate("finished");
				t.planned = rs.getDate("planned");
				t.title = rs.getString("title");
				
				

				
				listTasks.add(t);
				
			}
			
			return listTasks;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
		
			c.close();
			

		}
		
	}
	

	public List<Object> findByUserId(int id) throws SQLException {
		List<Object> listTasks = new ArrayList<Object>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement("SELECT * FROM \"PUBLIC\".\"TTASKS\" where user_id=?");
			
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				
				TaskDto t = new TaskDto();
				
				t.id = rs.getInt("id");
				t.categoryId = rs.getInt("category_id");
				t.userId = rs.getInt("user_id");
				t.comments = rs.getString("comments");
				t.created = rs.getDate("created");
				t.finished = rs.getDate("finished");
				t.planned = rs.getDate("planned");
				t.title = rs.getString("title");
				
				

				
				listTasks.add(t);
				
			}
			
			return listTasks;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
		
			c.close();
			

		}
		
	}
	
	public List<Object> findByCategoryId(int category_id) throws SQLException {
		List<Object> listTasks = new ArrayList<Object>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement("SELECT * FROM \"PUBLIC\".\"TTASKS\" where category_id=?");
			
			
			pst.setInt(1, category_id);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				
				TaskDto t = new TaskDto();
				
				t.id = rs.getInt("id");
				t.categoryId = rs.getInt("category_id");
				t.userId = rs.getInt("user_id");
				t.comments = rs.getString("comments");
				t.created = rs.getDate("created");
				t.finished = rs.getDate("finished");
				t.planned = rs.getDate("planned");
				t.title = rs.getString("title");
				
				

				
				listTasks.add(t);
				
			}
			
			return listTasks;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
		
			c.close();
			

		}
		
	}
	
	
public void updateFinished(int id) throws SQLException {
		
		
		Connection c = null;
		PreparedStatement pst = null;
	

		try {
			c = Jdbc.getConnection();
		
			Date d = new Date();
	
			
			pst = c.prepareStatement("UPDATE \"PUBLIC\".\"TTASKS\" SET finished =? where id=?");
			
			pst.setDate(1,new java.sql.Date(d.getTime()));
			pst.setInt(2,id);
			
			pst.executeUpdate();
			
			pst.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
		
			c.close();
		}

		
	}

}
