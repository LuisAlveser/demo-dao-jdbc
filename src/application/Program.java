package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {
	 public static void main(String[] args) throws SQLException  {
    
		Department obj = new Department(1,"books");
		Seller d = new Seller(21,"bob","bob@gmail.com",new Date(),3000.0,obj);
       System.out.print(d);
	}

}