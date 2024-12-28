package com.ust.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ust.inventory.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
