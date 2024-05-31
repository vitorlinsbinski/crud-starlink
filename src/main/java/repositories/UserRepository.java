package repositories;

import models.User.UserEntity;

public interface UserRepository {
	UserEntity getUserById(String id);
	
	UserEntity getUserByEmail(String email);
	
	UserEntity createUser(UserEntity user);
	
	void deleteUser(int userId);
}
