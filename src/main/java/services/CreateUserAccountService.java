package services;

import java.sql.Connection;
import java.sql.SQLException;

import database.ConnectionFactory;
import exceptions.UserAlreadyExistsExepction;
import models.Account.AccountDAO;
import models.Account.AccountEntity;
import models.User.UserDAO;
import models.User.UserEntity;

public class CreateUserAccountService {
	private ConnectionFactory connection;
	private GetUserByEmailService getUserByEmailService;
	private GetAccountByUsernameService getAccountByUsername;
	
	public CreateUserAccountService() {
		this.connection = new ConnectionFactory();
		this.getUserByEmailService = new GetUserByEmailService();
		this.getAccountByUsername = new GetAccountByUsernameService();
	}
	
	public void execute(UserEntity user, AccountEntity account) {
	    Connection dbConnection = this.connection.getConnection();
	    AccountDAO accountDAO = new AccountDAO(dbConnection);
	    UserDAO userDAO = new UserDAO(dbConnection);
	    
	    try {
	        UserEntity userOnDatabase = getUserByEmailService.execute(user.getEmail());
	        AccountEntity accountOnDatabase = getAccountByUsername.execute(account.getUsername());
	        
	        if (userOnDatabase == null && accountOnDatabase == null) {
                UserEntity newUser = userDAO.createUser(user);
                
                account.setUserId(newUser.getId());
                
                accountDAO.createAccount(account);
            } else if (userOnDatabase != null && accountOnDatabase == null) {
            	user.setId(userOnDatabase.getId());
            	
            	userDAO.updateUser(user);
            	
                account.setUserId(userOnDatabase.getId());
                
                accountDAO.createAccount(account);
            } else {
                throw new UserAlreadyExistsExepction("Email ou nome de usuário já registrado.");
            }
	        
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
