package com.example.aladin.Dto;

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
}
