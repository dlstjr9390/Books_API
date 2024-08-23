package com.example.aladin.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

@Getter
@NoArgsConstructor
public class ProductDto {
  private String title;
  private String link;
  private String image;
  private int lprice;

  public ProductDto(JSONObject productJson) {
    this.title = productJson.getString("title");
    this.link = productJson.getString("link");
    this.image = productJson.getString("image");
    this.lprice = productJson.getInt("lprice");
  }
}