package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
	 public static void main(String[] args) throws SQLException  {
    
		 Connection conn=null;
		 Statement sts= null;
		 //transaçoes
		 try {
		    conn=Conexao.getConnection();
		    conn.setAutoCommit(false);
		    sts=conn.createStatement();
		   
		   
		   int linhas = sts.executeUpdate("UPDATE seller SET BaseSalary= 2090 WHERE DepartmentId = 1");
		   
		 //  int x =1;
		//   if(x<2) {
		///	   throw new SQLException("FAKE ERRO");
		//	   
		//   }
		   
		   int linhas2 = sts.executeUpdate("UPDATE seller SET BaseSalary= 3090 WHERE DepartmentId = 2");
		   conn.commit();//termina a transação 
		   System.out.print("linha:"+linhas);
		   System.out.print("linha2:"+linhas2);
		   
		    
		 }catch(SQLException e) {
			 try {
				conn.rollback();//cancela a transação 
				  throw new SQLException("Transação cancelada,por esse erro :"+e.getMessage());
			} catch (SQLException e1) {
				throw new SQLException("Erro no rollback "+e1.getMessage());
				
			}
		 }
		 finally {
			 Conexao.closeConnection();
			
		 }
       
	}

}