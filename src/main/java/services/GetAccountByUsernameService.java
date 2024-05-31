package services;

import java.sql.Connection;
import java.sql.SQLException;

import database.ConnectionFactory;
import models.Account.AccountDAO;
import models.Account.AccountEntity;
import models.User.UserDAO;
import models.User.UserEntity;

public class GetAccountByUsernameService {
    private ConnectionFactory connection;
    
    public GetAccountByUsernameService() {
        this.connection = new ConnectionFactory();
    }
    
    public AccountEntity execute(String username) {
        Connection dbConnection = this.connection.getConnection();
        
        try {
            return new AccountDAO(dbConnection).getAccountByUsername(username);
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
