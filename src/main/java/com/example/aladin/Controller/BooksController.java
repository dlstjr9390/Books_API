package com.example.aladin.Controller;

import com.example.aladin.Dto.BooksDto;
import com.example.aladin.Dto.BooksStatisticsDto;
import com.example.aladin.Service.BooksService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/books")
public class BooksController {

  BooksService booksService;

  @GetMapping("/insert")
  public String insertBooks(@RequestParam String query) {
    int[] count = booksService.searchQuery(query);
    return "카테고리 " + count[1] + "건에 관한 도서" + count[0] + "건 저장 완료";
  }

  @GetMapping("/searchByMybatis")
  public List<BooksDto> searchBooksByMybatis(@RequestParam String type, @RequestParam String content) {

    return booksService.searchBooksByMybatis(type,content);
  }

  @DeleteMapping("/delete")
  public String searchBooks(@RequestParam List<Integer> booksId) {

    return booksService.deleteBooks(booksId);
  }

  @GetMapping("/statstics")
  public BooksStatisticsDto getBooksStatistics(){

    return booksService.getBooksStatistics();
  }

}
