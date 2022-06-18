package com.projectt.projectts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectt.projectts.domain.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
