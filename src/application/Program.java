package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	 public static void main(String[] args) throws SQLException  {
    
		Scanner sc = new Scanner(System.in);
		SellerDao sellerDao = DaoFactory.createSellerDao();
		//System.out.println("=== Test 1: seller finbyById====");
		
		Seller seller =sellerDao.findyById(3);
      // System.out.print(seller);
       
     //  System.out.println("=== Test 2: seller finbyByDepartment====");
       Department dep = new Department(2,null);
       List<Seller>list = sellerDao.findyByIdDepartment(dep);
       
       //for(Seller obj :list) {
    	  // System.out.println(obj);
      // }
      // System.out.println("=== Test 3: seller finbyAll====");
    
         list = sellerDao.findyAll();
       
    //   for(Seller obj :list) {
    //	   System.out.println(obj);
     //  }
     //    System.out.println("=== Test 4: seller insert====");
      //   Seller newSeller = new Seller(null,"luis","luis@gmail.com",new Date(),4000.0,dep);
      //   sellerDao.insert(newSeller);
       // System.out.print("Inseted New ID :"+newSeller.getId()); 
         
      //   System.out.println("=== Test 5: seller update====");
         seller=sellerDao.findyById(1);
         seller.setName("Maria Luiza");
         sellerDao.update(seller);
       //  System.out.print("Update completed");
         
         System.out.println("=== Test 6: seller delete====");
         System.out.print("Enter id for delete test: ");
         int id =sc.nextInt();
         sellerDao.deletebyId(id);
         System.out.print("Delete completed");
	}

}