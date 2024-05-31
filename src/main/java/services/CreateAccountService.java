package services;

import java.sql.Connection;
import java.sql.SQLException;

import database.ConnectionFactory;
import exceptions.UserAlreadyExistsExepction;
import models.Account.AccountDAO;
import models.Account.AccountEntity;
import models.User.UserDAO;
import models.User.UserEntity;

public class CreateAccountService {
	private ConnectionFactory connection;
	private GetUserByEmailService getUserByEmailService;
	private GetAccountByUsername getAccountByUsername;
	
	public CreateAccountService() {
		this.connection = new ConnectionFactory();
		this.getUserByEmailService = new GetUserByEmailService();
		this.getAccountByUsername = new GetAccountByUsername();
	}
	
	public void execute(UserEntity user, AccountEntity account) {
	    Connection dbConnection = this.connection.getConnection();
	    
	    try {
	        UserEntity userOnDatabase = getUserByEmailService.execute(user.getEmail());
	        AccountEntity accountOnDatabase = getAccountByUsername.execute(account.getUsername());
	        
	        if (userOnDatabase != null || accountOnDatabase != null) {
	            throw new UserAlreadyExistsExepction("Email ou nome de usuário já registrado.");
	        }
	        
	        UserEntity newUser = new UserDAO(dbConnection).createUser(user);
	        
	        account.setUserId(newUser.getId());
	        
	        new AccountDAO(dbConnection).createAccount(account);
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
