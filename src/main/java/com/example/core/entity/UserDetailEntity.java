package com.example.core.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "user_detail")
public class UserDetailEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column()
	private int userId;
	
	@Column(length = 100, nullable = false)
	private String fullname;

	@Column(length = 100, nullable = false)
	private String email;
	@Column(length = 11, nullable = false)
	private String phone;

	@Column()
	private Calendar birthDate;
	
	@Column(columnDefinition = "boolean default true")
	private boolean status;

	@Column(columnDefinition = "boolean default false")
	private boolean trash;
}
