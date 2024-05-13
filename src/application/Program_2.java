package application;

import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;

import model.dao.impl.DepartmentDaoJDBC;
import model.entities.Department;

public class Program_2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		DepartmentDao departmentDao = DaoFactory.createDepatmentDao();
	//	System.out.println("=== Test 1: department  insert====");
	//	Department dep = new Department(2,null);
	//   departmentDao.insert(dep);
		
		System.out.println("=== Test 2: department  update  and finbyById===");
		//finbybyId
	//	Department dep =departmentDao.findyById(2);
	//	System.out.println(dep);
		//update
	//	Department dep1 =departmentDao.findyById(5);
	//	dep1.setName("Class");
		//departmentDao.update(dep1);
	//	System.out.print("Update completed");
		
		System.out.println("=== Test 3: department  deleteById===");
		System.out.print("Enter id for delete test: ");
		int id = sc.nextInt();
		departmentDao.deletebyId(id);;
		System.out.println("Delete completed");
		
	
	}

	

}
