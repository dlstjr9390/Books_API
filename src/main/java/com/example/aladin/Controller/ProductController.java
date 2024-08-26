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
@RequestMapping("/api")
public class ProductController {

  private final ProductService productService;

  @GetMapping("/insert")
  public String insertBooks(@RequestParam String query) {
    int[] count = productService.searchQuery(query);
    return "카테고리 " + count[1] + "건에 관한 도서" + count[0] + "건 저장 완료";
  }

  @GetMapping("/search")
  public List<BooksDto> searchBooks(@RequestParam String type, @RequestParam String content) {

    return productService.searchBooks(type,content);
  }

  @GetMapping("/searchNaverProduct")
  public List<ProductDto> searchNaverProduct(@RequestParam String query){

    return productService.searchProducts(query);
  }

  @GetMapping("/searchByMybatis")
  public List<BooksDto> searchBooksByMybatis(@RequestParam String type, @RequestParam String content) {

    return productService.searchBooksByMybatis(type,content);
  }

  @DeleteMapping("/delete")
  public String searchBooks(@RequestParam List<Integer> booksId) {

    return productService.deleteBooks(booksId);
  }
}
