package models.Account;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import models.User.UserDAO;
import models.User.UserEntity;
import repositories.AccountRepository;

public class AccountDAO implements AccountRepository {
	private Connection connection;
	
	public AccountDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
    public AccountEntity getAccountByUsername(String username) {
        String sql = "SELECT * FROM conta WHERE nome_usuario = ?";
        
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setString(1, username);
            
            ResultSet rs = ps.executeQuery();
            
            AccountEntity account = null;
            
            if (rs.next()) {
                int id = rs.getInt("id");
                String usernameResult = rs.getString("nome_usuario");
                String password = rs.getString("senha");
                Timestamp createdAtTimestamp = rs.getTimestamp("criado_em");
                Timestamp updatedAtTimestamp = rs.getTimestamp("atualizado_em");
                int userId = rs.getInt("usuario_id");
                
                LocalDateTime createdAt = createdAtTimestamp != null ? createdAtTimestamp.toLocalDateTime() : null;
                LocalDateTime updatedAt = updatedAtTimestamp != null ? updatedAtTimestamp.toLocalDateTime() : null;
          
                account = new AccountEntity(id, usernameResult, password, createdAt, updatedAt, null, userId);
            }
            
            rs.close();
            ps.close();
            
            return account;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
	

	@Override
	public AccountEntity getAccountById(int id) {
		String sql = "SELECT * FROM conta WHERE id = ?";
        
        try {
            PreparedStatement ps = this.connection.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            AccountEntity account = null;
            
            if (rs.next()) {
                int idResult = rs.getInt("id");
                String usernameResult = rs.getString("nome_usuario");
                String password = rs.getString("senha");
                Timestamp createdAtTimestamp = rs.getTimestamp("criado_em");
                Timestamp updatedAtTimestamp = rs.getTimestamp("atualizado_em");
                int userId = rs.getInt("usuario_id");
                
                LocalDateTime createdAt = createdAtTimestamp != null ? createdAtTimestamp.toLocalDateTime() : null;
                LocalDateTime updatedAt = updatedAtTimestamp != null ? updatedAtTimestamp.toLocalDateTime() : null;
          
                account = new AccountEntity(idResult, usernameResult, password, createdAt, updatedAt, null, userId);
            }
            
            rs.close();
            ps.close();
            
            return account;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public void createAccount(AccountEntity account) {
		String sql = "INSERT INTO conta (nome_usuario, senha, usuario_id)" 
				+ "VALUES (?, ?, ?)";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			
			ps.setString(1, account.getUsername());
			ps.setString(2, account.getPassword());
			ps.setInt(3, account.getUserId());
			
			ps.execute();
			
			ps.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void deleteAccount(int id) {
		String sql = "DELETE FROM conta WHERE id = ?";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ps.execute();
			ps.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public void updateAccount(AccountEntity account) {
		String sql = "UPDATE conta SET nome_usuario = ?, senha = ?, usuario_id = ?, atualizado_em = ? WHERE id = ?";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			
			ps.setString(1, account.getUsername());
			ps.setString(2, account.getPassword());
			ps.setInt(3, account.getUserId());
			
			Timestamp updatedAtTimestamp = Timestamp.valueOf(account.getUpdatedAt());
			
	        ps.setTimestamp(4, updatedAtTimestamp);
	        ps.setInt(5, account.getId());
			
			int rowsAffected = ps.executeUpdate();
			
			ps.close();
			
			if (rowsAffected == 0) {
	            throw new SQLException("Nenhuma linha afetada, atualização falhou.");
	        }
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	
}
