package services;

import java.sql.Connection;
import java.sql.SQLException;

import database.ConnectionFactory;
import models.Account.AccountDAO;
import models.Account.AccountEntity;
import models.User.UserDAO;

public class DeleteUserAccountService {
	ConnectionFactory connection;
	
	public DeleteUserAccountService() {
		this.connection = new ConnectionFactory();
	}
	
	public void execute(int accountId) {	
		Connection dbConnection = this.connection.getConnection();
		AccountDAO accountDAO = new AccountDAO(dbConnection);
		
		try {
			AccountEntity accountToBeDeleted = accountDAO.getAccountById(accountId);
			
			accountDAO.deleteAccount(accountToBeDeleted.getId());
		} finally {
	        try {
	            if (dbConnection != null && !dbConnection.isClosed()) {
	                dbConnection.close();
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", e);
	        }
	    }
	}
}
