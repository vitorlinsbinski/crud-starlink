package services;

import java.sql.Connection;
import java.sql.SQLException;

import database.ConnectionFactory;
import models.User.UserDAO;
import models.User.UserEntity;

public class GetUserByIdService {
    private ConnectionFactory connection;
    
    public GetUserByIdService() {
        this.connection = new ConnectionFactory();
    }
    
    public UserEntity execute(int id) {
        Connection dbConnection = this.connection.getConnection();
        
        try {
            return new UserDAO(dbConnection).getUserById(id);
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
