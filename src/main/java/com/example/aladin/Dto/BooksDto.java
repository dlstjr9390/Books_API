package com.example.aladin.Dto;

import com.example.aladin.Entity.Books;
import java.sql.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

@Getter
@Setter
@NoArgsConstructor
public class BooksDto {
  private long itemId;
  private String title;
  private String author;
  private String pubDate;
  private String description;
  private String isbn;
  private long price;
  private String publisher;
  private long categoryId;

  public BooksDto(JSONObject bookJson) {
    this.itemId = bookJson.getLong("itemId");
    this.title = bookJson.getString("title");
    this.author = bookJson.getString("author");
    this.pubDate = bookJson.getString("pubDate");
    this.description = bookJson.getString("description");
    this.isbn = bookJson.getString("isbn");
    this.price = bookJson.getLong("priceStandard");
    this.publisher = bookJson.getString("publisher");
    this.categoryId = bookJson.getLong("categoryId");
  }

  public BooksDto(Books books) {
    this.itemId = books.getItemId();
    this.title = books.getTitle();
    this.author = books.getAuthor();
    this.pubDate = String.valueOf(books.getPubDate());
    this.description = books.getDescription();
    this.isbn = books.getIsbn();
    this.price = books.getPrice();
    this.publisher = books.getPublisher();
    this.categoryId = books.getCategoryId();
  }

}
