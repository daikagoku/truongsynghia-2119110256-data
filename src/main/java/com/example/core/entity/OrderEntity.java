package com.example.core.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "order")
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column()
	private Long userId;
	
	@Column()
	private Calendar date;
	
	@Column(columnDefinition = "boolean default true")
	private boolean status;

	@Column(columnDefinition = "boolean default false")
	private boolean trash;
}
