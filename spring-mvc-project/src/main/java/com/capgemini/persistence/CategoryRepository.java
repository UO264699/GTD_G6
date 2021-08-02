package com.capgemini.persistence;

import com.capgemini.model.Category;
import com.capgemini.model.User;
import com.capgemini.persistence.jdbc.Jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
public class CategoryRepository implements com.capgemini.persistence.Repository {
    @Override
    public int add(Object o) throws SQLException {
        return 0;
    }

    @Override
    public void delete(int id) throws SQLException {

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
