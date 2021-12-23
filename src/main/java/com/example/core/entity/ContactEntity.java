package com.example.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "contact")
public class ContactEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, nullable = false)
	private String title;

	@Column(length = 100, nullable = false)
	private String alias;

	@Column()
	private String value;
	
	@Column(columnDefinition = "boolean default true")
	private boolean status;

	@Column(columnDefinition = "boolean default false")
	private boolean trash;
}
