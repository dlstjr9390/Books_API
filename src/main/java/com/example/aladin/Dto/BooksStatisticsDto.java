package com.example.aladin.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BooksStatisticsDto {
  private Double totalPrice;
  private Double avgPrice;
  private Long mostCategoryId;
  private String mostPublisher;

}
