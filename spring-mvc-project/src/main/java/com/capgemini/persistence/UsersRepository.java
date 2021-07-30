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

	public UsersRepository() {
		// TODO Auto-generated constructor stub
	}

	
	public int add(Object o) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

	
	public List<Object> findAll() throws SQLException {
		
		
		List<Object> listUsers = new ArrayList<Object>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement("SELECT * FROM \"PUBLIC\".\"TUSERS\"");
			
			rs = pst.executeQuery();
			
			while(rs.next()) {
				
			
				int id = rs.getInt("id");
				String email = rs.getString("email");
				boolean isadmin = rs.getBoolean("isadmin");
				String login = rs.getString("login");
				String status = rs.getString("status");

				
				User u = new User(id, login, email, null, isadmin, null, null, null);

				
				listUsers.add(u);
				
			}
			
			return listUsers;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
		

			c.close();
		}
		
		
		
	}

}
