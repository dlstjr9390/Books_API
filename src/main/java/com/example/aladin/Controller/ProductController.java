package com.example.aladin.Controller;

import com.example.aladin.Dto.BooksDto;
import com.example.aladin.Service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ProductController {

  private final ProductService productService;

  @GetMapping("/search")
  public List<BooksDto> searchBooks(@RequestParam String query) {

    return productService.searchQuery(query);
  }
}
