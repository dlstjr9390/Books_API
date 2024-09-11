package com.example.aladin.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignupRequestDto {

  @NotBlank(message = "이름을 입력해주세요.")
  @Pattern(regexp = "^[가-힣]*$", message = "한글만 입력 가능합니다.")
  private String name;

  @NotBlank(message = "아이디를 입력해주세요.")
  @Pattern(regexp = "^.[a-zA-Z0-9]{6,20}$", message = "6~20자, 영어 대소문자와 숫자 가능")
  private String userID;

  @NotBlank(message = "비밀번호를 입력해주세요.")
  @Pattern(
      regexp = "^(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?])"
          + "[A-Za-z\\d!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]"
          + "{8,20}$", message = "8~20자, 특수문자 반드시 포함"
  )
  private String password;

  @NotBlank(message = "이메일을 입력해주세요.")
  @Email(message = "이메일 형식")
  private String email;
}
