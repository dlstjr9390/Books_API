package com.example.aladin.Controller;

import com.example.aladin.Dto.SignupRequestDto;
import com.example.aladin.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping("/signup")
  public String signup(SignupRequestDto signupRequestDto){
    return customerService.signup(signupRequestDto);
  }

  @PatchMapping("/withdraw")
  public String withdraw(Long customerId){
    return customerService.withdraw(customerId);
  }
}
