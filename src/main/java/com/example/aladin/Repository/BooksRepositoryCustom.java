package com.example.aladin.Repository;

import com.example.aladin.Entity.Books;
import java.awt.print.Book;
import java.util.List;

public interface BooksRepositoryCustom {
  List<Books> findBooks(String type, String content);
}
