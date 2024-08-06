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
  public String searchBooks(@RequestParam String query) {
    int[] count = productService.searchQuery(query);
    return "카테고리 " + count[1] + "건에 관한 도서" + count[0] + "건 저장 완료";
  }
}
