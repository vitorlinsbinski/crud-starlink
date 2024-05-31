package services;

import java.sql.Connection;
import java.sql.SQLException;

import database.ConnectionFactory;
import models.User.UserDAO;
import models.User.UserEntity;

public class GetUserByEmailService {
    private ConnectionFactory connection;
    
    public GetUserByEmailService() {
        this.connection = new ConnectionFactory();
    }
    
    public UserEntity execute(String email) {
        Connection dbConnection = this.connection.getConnection();
        
        try {
            return new UserDAO(dbConnection).getUserByEmail(email);
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
