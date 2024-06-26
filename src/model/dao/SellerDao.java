package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface SellerDao {
	
	void insert(Seller obj );
	  
	void update(Seller obj );
	
	void deletebyId(Integer id );
	
	Seller  findyById(Integer id);
	
	List<Seller>findyAll();
	
	List<Seller>findyByIdDepartment(Department department);

}
