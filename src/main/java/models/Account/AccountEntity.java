package models.Account;

import java.time.LocalDateTime;

public class AccountEntity {
	private String id;
	private String username;
	private String password;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private byte[] profilePicture; 
	private int userId;
	
	public AccountEntity(String id, String username, String password, LocalDateTime createdAt, LocalDateTime updatedAt,
			byte[] profilePicture, int userId) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.profilePicture = profilePicture;
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
