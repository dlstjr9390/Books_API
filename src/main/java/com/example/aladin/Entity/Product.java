package com.example.aladin.Entity;

import com.example.aladin.Dto.ProductDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product", indexes = {
    @Index(name = "title", columnList = "title"),
    @Index(name = "maker", columnList = "maker"),
    @Index(name = "lprice", columnList = "lprice"),
    @Index(name = "idx_maker_lprice", columnList = "maker,lprice")
})
@Getter
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
    this.link = productDto.getLink();
    this.image = productDto.getImage();
    this.maker = productDto.getMaker();
    this.lprice = productDto.getLprice();
  }
}
