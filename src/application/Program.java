package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import model.entities.Department;

public class Program {
	 public static void main(String[] args) throws SQLException  {
    
		Department obj = new Department(1,"books");
       System.out.print(obj);
	}

}