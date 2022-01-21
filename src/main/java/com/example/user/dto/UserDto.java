package com.example.user.dto;

import java.util.Calendar;

public class UserDto {
	private long id;
	private long typeId;
	private String avatar;
	private String fullname;
	private String email;
	private String phone;
	private Calendar birthDate;
	private int level;
	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public  long getTypeId() {
		return typeId;
	}
	public void setTypeId( long typeId) {
		this.typeId = typeId;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
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
	public Calendar getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

}
