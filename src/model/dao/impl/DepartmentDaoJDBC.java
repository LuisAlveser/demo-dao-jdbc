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
		PreparedStatement st = null;
		try {
			st =conn.prepareStatement("UPDATE department "+"SET Name= ? "+"WHERE Id = ? " 	);
			st.setString(1, obj.getName());
			st.setInt(2,obj.getId() );
			st.executeUpdate();
			
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deletebyId(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Department findyById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs =null;
		try {
			st= conn.prepareStatement("SELECT * FROM department "+"WHERE Id = ? ");
			st.setInt(1, id);
			rs =st.executeQuery();
			   if(rs.next()) {
				   Department dep = new  Department();
				   dep.setId(rs.getInt("Id"));
				   dep.setName(rs.getString("Name"));
				   return dep;
				   
			   }
			   
			return null;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
		
	}

	@Override
	public List<Department> findyAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
