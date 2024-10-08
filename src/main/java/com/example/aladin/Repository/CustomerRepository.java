package com.example.aladin.Repository;

import com.example.aladin.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  Customer findByCustomerId(Long customerId);

  boolean existsByEmail(String email);

  boolean existsByUserID(String userID);
}
