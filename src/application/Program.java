package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	 public static void main(String[] args) throws SQLException  {
    
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println("=== Test 1: seller finbyById====");
		
		Seller seller =sellerDao.findyById(3);
       System.out.print(seller);
	}

}