package repositories;

import models.User.UserEntity;

public interface UserRepository {
	UserEntity getUserById(int id);
	
	UserEntity getUserByEmail(String email);
	
	UserEntity createUser(UserEntity user);
	
	void deleteUser(int userId);
}
