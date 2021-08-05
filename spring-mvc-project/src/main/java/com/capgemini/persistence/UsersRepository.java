package com.capgemini.persistence;



import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.capgemini.model.User;
import com.capgemini.persistence.dto.UserDto;
import com.capgemini.persistence.jdbc.Jdbc;

@Repository
public class UsersRepository implements com.capgemini.persistence.Repository {

	
	public int add(Object o)  {
		UserDto u = (UserDto) o;

		Connection c = null;
		PreparedStatement pst = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement("INSERT INTO TUSERS (EMAIL,ISADMIN,LOGIN,PASSWORD,STATUS) VALUES (?,?,?,?,?)");

			pst.setString(1, u.email);
			pst.setBoolean(2, u.isAdmin);
			pst.setString(3, u.login);
			pst.setString(4, u.password);
			pst.setString(5, u.status);

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
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	public void delete(int id) {
		
		Connection c = null;
		PreparedStatement pst = null;
    
	
		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement("DELETE FROM \"PUBLIC\".\"TUSERS\" WHERE id = ?");
			

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

	
	public List<Object> findAll()  {
		
		
		List<Object> listUsers = new ArrayList<Object>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement("SELECT * FROM \"PUBLIC\".\"TUSERS\"");
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
			

				
				UserDto u = new UserDto();
				
				u.id = rs.getInt("id");
				u.email = rs.getString("email");
				u.isAdmin = rs.getBoolean("isadmin");
				u.login = rs.getString("login");
			    u.status = rs.getString("status");

				
				listUsers.add(u);
				
			}
			
			return listUsers;
			
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
	
	public UserDto findById(int id) {
		
		
		List<Object> listUsers = new ArrayList<Object>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement("SELECT * FROM \"PUBLIC\".\"TUSERS\" where id=?");
			
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
			

				
				UserDto u = new UserDto();
				
				u.id = rs.getInt("id");
				u.email = rs.getString("email");
				u.isAdmin = rs.getBoolean("isadmin");
				u.login = rs.getString("login");
			    u.status = rs.getString("status");
		
                
				
				listUsers.add(u);
				
			}
			
			return (UserDto) listUsers.get(0);
			
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
	
	
	
	public void updateStatus(int id)  {
		
		
		Connection c = null;
		PreparedStatement pst = null;
		
		UserDto u = findById(id);
	
		
	
		try {
			c = Jdbc.getConnection();
		
	
			
			pst = c.prepareStatement("UPDATE \"PUBLIC\".\"TUSERS\" SET status = "+ "'" + changeStatus(u) + "'" + " where id=?");
			

			pst.setInt(1,id);
			
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
	
	private String changeStatus(UserDto u) {
		

		if(u.status.equals("ENABLED"))
			return "DISABLED";
		else
			return "ENABLED";
	}

}
