package services;

import java.sql.Connection;
import java.sql.SQLException;

import database.ConnectionFactory;
import exceptions.ResourceNotFoundException;
import exceptions.UserAlreadyExistsExepction;
import models.Account.AccountDAO;
import models.Account.AccountEntity;
import models.User.UserDAO;
import models.User.UserEntity;

public class UpdateProfileService {
	private ConnectionFactory connection;

	public UpdateProfileService() {
		this.connection = new ConnectionFactory();
	}

	public void execute(UserEntity user, AccountEntity account) {
	    Connection dbConnection = this.connection.getConnection();
	    
	    try {
	        UserDAO userDAO = new UserDAO(dbConnection);
	        AccountDAO accountDAO = new AccountDAO(dbConnection);
	        
	        String oldUsername = accountDAO.getAccountById(account.getId()).getUsername();
	        String oldEmail = userDAO.getUserById(user.getId()).getEmail();
	        
	        if (!oldUsername.equals(account.getUsername())) {
	            AccountEntity accountWithSameUsername = accountDAO.getAccountByUsername(account.getUsername());
	            
	            if (accountWithSameUsername != null) {
	                throw new UserAlreadyExistsExepction("Nome de usuário já existe.");
	            }
	        }

	        if (!oldEmail.equals(user.getEmail())) {
	            UserEntity userWithSameEmail = userDAO.getUserByEmail(user.getEmail());
	            
	            if (userWithSameEmail != null) {
	                throw new UserAlreadyExistsExepction("E-mail já existe.");
	            }
	        }
	                    
	        userDAO.updateUser(user);
	        accountDAO.updateAccount(account);
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
