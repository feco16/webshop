package org.webshop.productmanagement.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.webshop.productmanagement.entity.Product;

public interface WriteProductRepository extends JpaRepository<Product, Long> {
}
