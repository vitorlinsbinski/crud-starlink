package services;

import java.sql.Connection;
import java.sql.SQLException;

import database.ConnectionFactory;
import exceptions.ResourceNotFoundException;
import exceptions.UserCredentialsDoesNotMatch;
import models.Account.AccountDAO;
import models.Account.AccountEntity;
import models.User.UserDAO;
import repositories.AccountRepository;
import utils.Hashing;

public class AuthenticateService {
    private ConnectionFactory connection;

    public AuthenticateService() {
        this.connection = new ConnectionFactory();
    }
    
    public AccountEntity execute(String username, String password) {
        Connection dbConnection = this.connection.getConnection();
        
        try {
        	AccountEntity account = new AccountDAO(dbConnection).getAccountByUsername(username);
        	
        	if(account == null) {
        		throw new ResourceNotFoundException("Usuário não encontrado.");
        	}
        	
        	String hashedPassword = Hashing.hashPassword(password);
        	
        	boolean doesPasswordMatch = account.getPassword().equals(hashedPassword);
       
            if(!doesPasswordMatch) {
                throw new UserCredentialsDoesNotMatch("Credenciais inválidas.");
            }
            
            return account;
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

