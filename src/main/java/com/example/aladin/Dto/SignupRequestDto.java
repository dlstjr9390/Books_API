package com.example.aladin.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignupRequestDto {
  private String name;

  private String userID;

  private String password;

  private String email;
}
