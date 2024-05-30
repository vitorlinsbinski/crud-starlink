package models.User;

import java.time.LocalDate;

public class UserEntity {
	private int id;
	private String fullName;
	private String phone;
	private String residentialAddress;
	private LocalDate birthday;
	private String gender;
	
	public UserEntity(int id, String fullName, String phone, String residentialAddress, LocalDate birthday, String gender) {
		this.id = id;
		this.fullName = fullName;
		this.phone = phone;
		this.residentialAddress = residentialAddress;
		this.birthday = birthday;
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

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
}
