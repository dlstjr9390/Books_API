package com.example.aladin.Entity;

import com.example.aladin.Dto.BooksDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Books {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long booksId;

  @Column
  private long itemId;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String author;

  @Column(nullable = false)
  private Date pubDate;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String isbn;

  @Column(nullable = false)
  private long price;

  @Column(nullable = false)
  private String publisher;

  @Column(nullable = false)
  private long categoryId;

  public Books(BooksDto booksDto) {
    this.itemId = booksDto.getItemId();
    this.title = booksDto.getTitle();
    this.author = booksDto.getAuthor();
    this.pubDate = Date.valueOf(booksDto.getPubDate());
    this.description = booksDto.getDescription();
    this.isbn = booksDto.getIsbn();
    this.price = booksDto.getPrice();
    this.publisher = booksDto.getPublisher();
    this.categoryId = booksDto.getCategoryId();
  }
}
