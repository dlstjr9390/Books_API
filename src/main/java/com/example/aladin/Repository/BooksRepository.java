package com.example.aladin.Repository;

import com.example.aladin.Entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository  extends JpaRepository<Books, Long>, BooksRepositoryCustom {
   Books findBooksByBooksId(Long booksId);

}
