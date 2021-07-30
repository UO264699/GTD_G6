package com.capgemini.persistence;

import java.sql.SQLException;
import java.util.List;

public interface Repository {
	
	
	public int add(Object o) throws SQLException;
	public void delete(int id) throws SQLException;

	public List<Object> findAll() throws SQLException;

}
