package models.User;

public interface UserDAO {
	UserEntity getUserByEmail(String email);
	void createUser(UserEntity user);
	void deleteUser(int userId);
}
