package models.User;

import java.time.LocalDate;

public class UserEntity {
	private int id;
	private String fullName;
	private String email;
	private String phone;
	private String residentialAddress;
	private LocalDate birthdate;
	private String gender;
	
	public UserEntity(int id, String fullName, String email, String phone, String residentialAddress, LocalDate birthday, String gender) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.residentialAddress = residentialAddress;
		this.birthdate = birthday;
		this.gender = gender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(LocalDate birthday) {
		this.birthdate = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
