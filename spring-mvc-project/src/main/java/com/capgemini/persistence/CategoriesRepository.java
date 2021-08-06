package com.capgemini.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.capgemini.model.Category;
import org.springframework.stereotype.Repository;

import com.capgemini.persistence.dto.CategoryDto;
import com.capgemini.persistence.jdbc.Jdbc;


@Repository
public class CategoriesRepository implements com.capgemini.persistence.Repository {

	

	@Override
	public int add(Object o) {
		
		CategoryDto category = (CategoryDto) o;
		  
		
		
		Connection c = null;
		PreparedStatement pst = null;
  
		
	
		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement("INSERT INTO \"PUBLIC\".\"TCATEGORIES\"(name,user_id) VALUES(?,?)");
	
			
			pst.setString(1, category.name);
			pst.setInt(2, category.user_id);
			
			

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
	public void delete(int id)  {
		
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
			try {
				pst.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
	
   

	public void deleteByUserId(int id)  {
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
		List<Object> listCategories = new ArrayList<Object>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;



		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement("SELECT * FROM \"PUBLIC\".\"TCATEGORIES\"");

			rs = pst.executeQuery();

			while(rs.next()) {


				CategoryDto categoryDto = new CategoryDto();
				
				categoryDto.name = rs.getString("name");
				categoryDto.user_id = rs.getInt("user_id");
				categoryDto.id = rs.getInt("id");
			

				listCategories.add(categoryDto);

			}

			return listCategories;

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
	
	public CategoryDto findbyid(int id)  {
		List<Object> listCategories = new ArrayList<Object>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;



		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement("SELECT * FROM \"PUBLIC\".\"TCATEGORIES\" where id=?");
			
			pst.setInt(1, id);

			rs = pst.executeQuery();

			while(rs.next()) {


				CategoryDto categoryDto = new CategoryDto();
				
				categoryDto.name = rs.getString("name");
				categoryDto.user_id = rs.getInt("user_id");

			

				listCategories.add(categoryDto);

			}

			return (CategoryDto) listCategories.get(0);

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
	
	public List<Object> findbyUserid(int id)  {
		List<Object> listCategories = new ArrayList<Object>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;



		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement("SELECT * FROM \"PUBLIC\".\"TCATEGORIES\" where user_id=?");
			
			pst.setInt(1, id);

			rs = pst.executeQuery();

			while(rs.next()) {


				CategoryDto categoryDto = new CategoryDto();
				
				categoryDto.name = rs.getString("name");
				categoryDto.user_id = rs.getInt("user_id");
				categoryDto.id = rs.getInt("id");
			

				listCategories.add(categoryDto);

			}

			return listCategories;

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
	
	public CategoryDto findbyname(String name)  {
		List<Object> listCategories = new ArrayList<Object>();

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;



		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement("SELECT * FROM \"PUBLIC\".\"TCATEGORIES\" where name=?");
			
			pst.setString(1, name);

			rs = pst.executeQuery();

			while(rs.next()) {


				CategoryDto categoryDto = new CategoryDto();
				
				categoryDto.name = rs.getString("name");
				categoryDto.user_id = rs.getInt("user_id");
				categoryDto.id = rs.getInt("id");

			

				listCategories.add(categoryDto);

			}

			return (CategoryDto) listCategories.get(0);

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

	public Object getCategoryById(int id) {
		List<Object> listCategories = new ArrayList<Object>();
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();

			pst = c.prepareStatement("SELECT * FROM \"PUBLIC\".\"TCATEGORIES\" WHERE ID = ?");

			rs = pst.executeQuery();

			pst.setInt(1,id);

			rs = pst.executeQuery();
			
			while(rs.next()) {
				
				
				CategoryDto t = new CategoryDto();
				
				t.id = rs.getInt("id");
				t.name = rs.getString("name");
				t.user_id = rs.getInt("user_id");

				listCategories.add(t);
				
			}
			
			return listCategories;

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
	
	
	public void updateCategory(int id,String name) {		
		
		Connection c = null;
		PreparedStatement pst = null;


		
		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement("UPDATE \"PUBLIC\".\"TCATEGORIES\" SET name =? where id=?");
			
			pst.setString(1, name);
			pst.setInt(2, id);
			
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
