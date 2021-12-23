package com.example.core.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "order_detail")
public class OrderDetailEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column()
	private Long orderId;
	@Column()
	private Long productId;
	@Column()
	private Long versionId;
	@Column()
	private float price;

	@Column()
	private float salePrice;
	@Column()
	private Long quantity;
	@Column()
	private Calendar date;
	
	@Column(columnDefinition = "boolean default true")
	private boolean status;

	@Column(columnDefinition = "boolean default false")
	private boolean trash;
}
