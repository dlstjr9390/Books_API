package com.example.aladin.Mapper;

import com.example.aladin.Entity.Books;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BooksMapper {
  List<Books> searchBooks(String type,String content);

  void deleteBooks(Long booksId);
}
