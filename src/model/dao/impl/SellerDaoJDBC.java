package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import application.Conexao;
import application.DbIntegrityException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC  implements SellerDao{
	
	
	private Connection conn;
	
	public SellerDaoJDBC (Connection conn) {
		this.conn=conn;
		
		
	}

	@Override
	public void insert(Seller obj) {
		PreparedStatement st = null;
		try {
		
		st=conn.prepareStatement(
				"INSERT INTO seller"
		+"(Name, Email, BirthDate, BaseSalary, DepartmentId) "
		+"VALUES "
		+"(?, ?, ?, ?, ?) ",Statement.RETURN_GENERATED_KEYS
		);
		st.setString(1, obj.getName());
		st.setString(2,obj.getEmail());
		st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
		st.setDouble(4, obj.getBaseSalary());
		st.setInt(5, obj.getDepartment().getId());
		
		
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
	}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

	@Override
	public void update(Seller obj) {
		PreparedStatement st = null;
		try {
		
		st=conn.prepareStatement(
				"UPDATE seller "
				+"SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
				+"WHERE Id = ? "
		);
		st.setString(1, obj.getName());
		st.setString(2,obj.getEmail());
		st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
		st.setDouble(4, obj.getBaseSalary());
		st.setInt(5, obj.getDepartment().getId());
		st.setInt(6, obj.getId());
		
		st.executeUpdate();
	
		 
	}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void deletebyId(Integer id) {
		PreparedStatement st = null;
		try {
			st= conn.prepareStatement("DELETE FROM seller\r\n"
					+ "WHERE Id = ?");
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Seller findyById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
				"SELECT seller.*, department.Name as DepName " +
				"FROM seller INNER JOIN department " +
				"ON seller.DepartmentId = department.Id " +
				"WHERE seller.Id = ?"
			);
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Department dep = instantianteDepartment(rs);
				//Seller 
				Seller obj = instantianteSeller(rs,dep);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}



	private Seller instantianteSeller(ResultSet rs, Department dep) throws SQLException {
		  Seller obj   =new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);
		return obj;
	}

	private Department instantianteDepartment(ResultSet rs) throws SQLException {
	   Department	dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("DepName"));
		return dep;
		
		
	}

	@Override
	public List<Seller> findyAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					
					+"ORDER BY Name"
			);
			
			
			rs = st.executeQuery();
			
			List<Seller> list= new ArrayList<>();
			
			Map<Integer,Department>map = new HashMap<>();
			
			
			while (rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep==null) {
				
				dep = instantianteDepartment(rs);
				
				map.put(rs.getInt("DepartmentId"), dep);
				}
				//Seller 
				Seller obj = instantianteSeller(rs,dep);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
		
		
	}
	@Override
    public 	List<Seller>findyByIdDepartment(Department department){
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+"FROM seller INNER JOIN department "
					+"ON seller.DepartmentId = department.Id "
					+"WHERE DepartmentId = ? "
					+"ORDER BY Name"
			);
			st.setInt(1, department.getId());
			
			rs = st.executeQuery();
			
			List<Seller> list= new ArrayList<>();
			
			Map<Integer,Department>map = new HashMap<>();
			
			
			while (rs.next()) {
				
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep==null) {
				
				dep = instantianteDepartment(rs);
				
				map.put(rs.getInt("DepartmentId"), dep);
				}
				//Seller 
				Seller obj = instantianteSeller(rs,dep);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		
    
    }


}
