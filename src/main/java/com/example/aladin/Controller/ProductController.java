package com.example.aladin.Controller;

import com.example.aladin.Dto.BooksDto;
import com.example.aladin.Dto.ProductDto;
import com.example.aladin.Entity.Books;
import com.example.aladin.Service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

  private final ProductService productService;



  @GetMapping("/searchNaverShop")
  public List<ProductDto> searchNaverProduct(@RequestParam String query){

    return productService.searchProducts(query);
  }


  @GetMapping("/searchProduct")
  public List<ProductDto> searchProductByMybatis(
      @RequestParam String type,
      @RequestParam String content

  )
  {
    return productService.searchProductByMybatis(type, content);
  }

  @DeleteMapping
  public String deleteProduct(@RequestParam List<Integer> productId ){

    return productService.deleteProduct(productId);

  }

}
