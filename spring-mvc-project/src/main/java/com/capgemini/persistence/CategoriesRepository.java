package com.capgemini.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.capgemini.model.Category;
import org.springframework.stereotype.Repository;

import com.capgemini.persistence.dto.CategoryDto;
import com.capgemini.persistence.dto.TaskDto;
import com.capgemini.persistence.jdbc.Jdbc;

@Repository
public class CategoriesRepository implements com.capgemini.persistence.Repository {

	public CategoriesRepository() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int add(Object o) throws SQLException {
		
		CategoryDto category = (CategoryDto) o;
		  
		
		
		Connection c = null;
		PreparedStatement pst = null;
  
		
	
		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement("INSERT INTO \"PUBLIC\".\"TCATEGORIES\"(name,user_id) VALUES(?,?)");
	
			
			pst.setString(1, category.name);
			pst.setString(2, "2");
			
			

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
			
			pst = c.prepareStatement("DELETE FROM \"PUBLIC\".\"TCATEGORIES\" WHERE id = ?");
			

			pst.setInt(1,id);
			
			pst.executeUpdate();
			
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			pst.close();
			c.close();
		}

	}
	

	public void deleteByUserId(int id) throws SQLException {
		Connection c = null;
		PreparedStatement pst = null;
    
	
		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement("DELETE FROM \"PUBLIC\".\"TCATEGORIES\" WHERE user_id = ?");
			

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
		List<Object> listCategories = new ArrayList<Object>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;



		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement("SELECT * FROM \"PUBLIC\".\"TCATEGORIES\"");

			rs = pst.executeQuery();

			while(rs.next()) {


				int id = rs.getInt("id");
				String name = rs.getString("name");
				int user_id = rs.getInt("user_id");

				Category u = new Category(id,name,user_id);

				listCategories.add(u);

			}

			return listCategories;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {


			c.close();
		}
	}

}
