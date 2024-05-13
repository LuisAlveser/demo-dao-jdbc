package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import application.DbIntegrityException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	
       private Connection conn;
	
	public  DepartmentDaoJDBC (Connection conn) {
		this.conn=conn;
		
		
	}
	

	@Override
	public void insert(Department obj) {
		PreparedStatement st = null;
		try {
			
			st=conn.prepareStatement(
					"INSERT INTO department"
			+"(Name) "
			+"VALUES "
			+"(?) ",Statement.RETURN_GENERATED_KEYS
			);
			st.setString(1,obj.getName());
			 int rowsAffeccted =st.executeUpdate();
			 
			 if(rowsAffeccted >0) {
				 ResultSet rs = st.getGeneratedKeys();
			      if(rs.next()){
			    	  int id =rs.getInt(1);
			    	  obj.setId(id);
			    	  
			      }
			 
			}
			 else {
				throw new DbIntegrityException("Unexpected error! NO rows affected");  
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}

			 
		
	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletebyId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findyById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findyAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
