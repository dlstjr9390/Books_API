package com.example.aladin.Repository;

import com.example.aladin.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  Product findProductByProductId(Long productId);

}
