package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;

import model.dao.impl.DepartmentDaoJDBC;
import model.entities.Department;

public class Program_2 {

	public static void main(String[] args) {
		DepartmentDao departmentDao = DaoFactory.createDepatmentDao();
		System.out.println("=== Test 1: department  insert====");
		Department dep = new Department(null,"Music");
	   departmentDao.insert(dep);
	   

	}

	

}
