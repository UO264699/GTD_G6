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
	public int add(Object o)  {
		
		TaskDto task = (TaskDto) o;
		  
		
		
		Connection c = null;
		PreparedStatement pst = null;
  
		
	
		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement("INSERT INTO \"PUBLIC\".\"TTASKS\"(comments,created,finished,planned,title,category_id,user_id) VALUES(?,?,?,?,?,?,?)");
		
			Date d = new Date();
			
			
			
			pst.setString(1, null);
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
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	@Override
	public void delete(int id) {
		
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
			try {
				pst.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
	

	public void deleteByCategoryId(int categoryid)  {
		
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
			
			try {
				pst.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


	@Override
	public List<Object> findAll()  {
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
		
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		
	}
	

	public List<Object> findByUserId(int id)  {
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
		
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		
	}
	
	
	public TaskDto findById(int id)  {
		

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement("SELECT * FROM \"PUBLIC\".\"TTASKS\" where id=?");
			
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			TaskDto t = new TaskDto();
			
			while(rs.next()) {
				
				
			
				
				t.id = rs.getInt("id");
				t.categoryId = rs.getInt("category_id");
				t.userId = rs.getInt("user_id");
				t.comments = rs.getString("comments");
				t.created = rs.getDate("created");
				t.finished = rs.getDate("finished");
				t.planned = rs.getDate("planned");
				t.title = rs.getString("title");
				
				

		
			}
			
			return t;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
		
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		
	}
	
	public List<Object> findByCategoryId(int category_id) {
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
		
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			

		}
		
	}
	
	
	public void updateFinished(int id){
			
			
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
			
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
			
		}

	public void updateTask(TaskDto task) {		
		
		Connection c = null;
		PreparedStatement pst = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement("UPDATE \"PUBLIC\".\"TTASKS\" SET title =?, comments=?, planned=?  where id=?");
			
			
			pst.setString(1, task.title);
			pst.setString(2, task.comments);
			if(task.planned != null) {
				
				pst.setDate(3, new java.sql.Date(task.planned.getTime()));
			}else
				
				pst.setDate(3, null);
			
			pst.setInt(4, task.id);


			pst.executeUpdate();
			
			pst.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
		
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
