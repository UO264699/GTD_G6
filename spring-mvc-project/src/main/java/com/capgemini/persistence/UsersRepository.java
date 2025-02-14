package com.capgemini.persistence;


/**
 * 
 * Clase que se encarga de realizar las transacciones con la base de datos relacionadas
 * con los usuarios
 * 
 * @author andrefer
 *
 */
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.capgemini.persistence.dto.UserDto;
import com.capgemini.persistence.jdbc.Jdbc;

@Repository
public class UsersRepository implements com.capgemini.persistence.Repository {

	/**
	 *M�todo que inserta en la base de datos el usuario que se pasa por par�metro
	 */
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
    /**
     * M�todo que borra el usuario cuyo id se pasa por par�metro
     * 
     */
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

	/**
	 * M�todo que devuelve la lista de todos los usuarios del sistema
	 */
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
	
	/**
	 * 
	 * M�todo que devuelve un usuario cuyo id se pasa por par�metro
	 * 
	 * @param id id del usuario
	 * @return usuario
	 */
	public UserDto findById(int id) {
		
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement("SELECT * FROM \"PUBLIC\".\"TUSERS\" where id=?");
			
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			
			
				
			UserDto u = new UserDto();
				while(rs.next()) {
				
				u.id = rs.getInt("id");
				u.email = rs.getString("email");
				u.isAdmin = rs.getBoolean("isadmin");
				u.login = rs.getString("login");
				u.password = rs.getString("password");
			    u.status = rs.getString("status");
			    u.confirmPassword = "a";
				
			}
                
				
			
				
			
			
			
			return u;
			
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
	/**
	 * M�todo que devuelve el usuario cuyo nombre de usuario se pasa por par�metro
	 * 
	 * @param login nombre de usuario
	 * @return usuario
	 */
	public UserDto findByLogin(String login) {
		
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement("SELECT * FROM \"PUBLIC\".\"TUSERS\" where login=?");
			
			pst.setString(1, login);
			
			rs = pst.executeQuery();
			
			UserDto u = new UserDto();
			
			while(rs.next()) {
				
				u.id = rs.getInt("id");
				u.email = rs.getString("email");
				u.isAdmin = rs.getBoolean("isadmin");
				u.password = rs.getString("password");
				u.login = rs.getString("login");
			    u.status = rs.getString("status");
			    u.confirmPassword = "a";
				
			}
			
			return u;
			
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
	
	/**
	 * M�todo que devuelve el usuario cuyo email se pasa por par�metro
	 * 
	 * @param email email del usuario 
	 * @return usuario
	 */
	public UserDto findByEmail(String email) {
		
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement("SELECT * FROM \"PUBLIC\".\"TUSERS\" where email=?");
			
			pst.setString(1, email);
			
			rs = pst.executeQuery();
			
			UserDto u = new UserDto();
			
			while(rs.next()) {
				
				u.id = rs.getInt("id");
				u.email = rs.getString("email");
				u.isAdmin = rs.getBoolean("isadmin");
				u.password = rs.getString("password");
				u.login = rs.getString("login");
			    u.status = rs.getString("status");
			    u.confirmPassword = "a";
				
			}
			
			return u;
			
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
	
	
	/**
	 * M�todo que actualiza el estado del usuario cuyo id se pasa por
	 * par�metro
	 * 
	 * @param id
	 */
	public void updateStatus(int id)  {
		
		
		Connection c = null;
		PreparedStatement pst = null;
		
		UserDto u = findById(id);
	
		
	
		try {
			c = Jdbc.getConnection();
		
	
			
			pst = c.prepareStatement("UPDATE \"PUBLIC\".\"TUSERS\" SET status =? where id=?");
			

			pst.setString(1, changeStatus(u));
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
	
	private String changeStatus(UserDto u) {
		

		if(u.status.equals("ENABLED"))
			return "DISABLED";
		else
			return "ENABLED";
	}

}
