package com.example.aladin.Mapper;

import com.example.aladin.Entity.Customer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {
  void withdraw(Long customerId);
}
