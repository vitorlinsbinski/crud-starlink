package models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

public class UserDAOImpl implements UserDAO {
	private Connection connection;
	
	public UserDAOImpl(Connection connection) {
		this.connection = connection;
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		String sql = "SELECT * from usuario WHERE email = ?";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			
			UserEntity user = null;
			
			while(rs.next()) {
				int id = rs.getInt(1);
				String fullName = rs.getString(2);
				String userEmail = rs.getString(3);
				String phone = rs.getString(4);
				String residentialAddress = rs.getString(5);
				Date birthdate = rs.getDate(6);
				String gender = rs.getString(7);
				
				user = new UserEntity(id, fullName, userEmail, phone, residentialAddress, birthdate.toLocalDate(), gender);
			}
			
			rs.close();
			ps.close();
			this.connection.close();
			
			System.out.println("Usuário no UserDAOImpl: " + user);
			
			return user;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void createUser(UserEntity user) {
		String sql = "INSERT INTO usuario (nome_completo, email, telefone, endereco_residencial, data_nascimento, genero)" 
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = this.connection.prepareStatement(sql);
			
			ps.setString(1, user.getFullName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPhone());
			ps.setString(4, user.getResidentialAddress());
			ps.setDate(5, Date.valueOf(user.getBirthdate()));
			ps.setString(6, user.getGender());
			
			ps.execute();
			
			ps.close();
			this.connection.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		
	}

}
