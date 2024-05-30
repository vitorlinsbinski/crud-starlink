package models.User;

import java.sql.Connection;

import database.ConnectionFactory;
import exceptions.UserAlreadyExistsExepction;

public class UserService implements UserDAO {
	private ConnectionFactory connection;
	
	public UserService() {
		this.connection = new ConnectionFactory();
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		Connection dbConnection = this.connection.getConnection();
		
		return new UserDAOImpl(dbConnection).getUserByEmail(email);
	}

	@Override
	public void createUser(UserEntity user) {
		Connection dbConnection = this.connection.getConnection();
		
		UserEntity userOnDatabase = getUserByEmail(user.getEmail());
		
		System.out.println("userOnDatabase" + userOnDatabase);
		
		if(userOnDatabase != null) {
			throw new UserAlreadyExistsExepction("Usuário com este email já existe.");
		}
		
		new UserDAOImpl(dbConnection).createUser(user);
	}

	@Override
	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		
	}

	
}
