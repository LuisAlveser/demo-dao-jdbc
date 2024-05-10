package model.dao;

import application.Conexao;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
	public static SellerDao createSellerDao() {
		return new SellerDaoJDBC(Conexao.getConnection());
		
		
	}

}
