package com.example.aladin.Entity;

import com.example.aladin.Dto.ProductDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long productId;

  @Column(nullable = false)
  String title;

  @Column(nullable = false)
  String link;

  @Column(nullable = false)
  String image;

  @Column(nullable = false)
  String maker;

  @Column(nullable = false)
  int lprice;

  public Product(ProductDto productDto) {
    this.title = productDto.getTitle();
    this.link = productDto.getImage();
    this.image = productDto.getTitle();
    this.maker = productDto.getMaker();
    this.lprice = productDto.getLprice();
  }
}
