package com.example.aladin.Dto;

import com.example.aladin.Entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Getter
@NoArgsConstructor
public class ProductDto {
  private String title;
  private String link;
  private String image;
  private String maker;
  private int lprice;

  public ProductDto(JSONObject productJson) {
    this.title = productJson.getString("title");
    this.link = productJson.getString("link");
    this.image = productJson.getString("image");
    this.maker = productJson.getString("maker");
    this.lprice = productJson.getInt("lprice");
  }

  public ProductDto(Product product){
    this.title = product.getTitle();
    this.link = product.getLink();
    this.image = product.getImage();
    this.maker = product.getMaker();
    this.lprice = product.getLprice();
  }
}