package com.example.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.core.entity.ProductEntity;


@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,Long>{

}
