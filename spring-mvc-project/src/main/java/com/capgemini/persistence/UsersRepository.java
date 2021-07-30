package com.capgemini.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.persistence.dto.PizzaDto;
import com.capgemini.persistence.dto.UserDto;
import com.capgemini.persistence.jdbc.Jdbc;
import com.sun.org.apache.xml.internal.security.keys.content.keyvalues.RSAKeyValue;

public class UsersRepository implements Repository {

	public UsersRepository() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int add(Object o) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(int id) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
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
				
				UserDto u = new UserDto();
				
				u.email = rs.getString("email");
				u.isadmin = rs.getBoolean("isadmin");
				u.login = rs.getString("login");
				u.status = rs.getString("status");


				
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
