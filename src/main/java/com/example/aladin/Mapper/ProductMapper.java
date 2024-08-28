package com.example.aladin.Mapper;

import com.example.aladin.Dto.ProductDto;
import com.example.aladin.Entity.Books;
import com.example.aladin.Entity.Product;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
  List<Product> searchProductByPrice(String type, Integer intContent);

  void deleteProduct(Long productId);
}
