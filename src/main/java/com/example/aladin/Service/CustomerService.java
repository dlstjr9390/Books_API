package com.example.aladin.Service;

import com.example.aladin.Dto.SignupRequestDto;
import com.example.aladin.Entity.Customer;
import com.example.aladin.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CustomerService {

  private final PasswordEncoder passwordEncoder;
  private final CustomerRepository customerRepository;

  public String signup(SignupRequestDto signupRequestDto) {

    String userID = signupRequestDto.getUserID();
    String password = passwordEncoder.encode(signupRequestDto.getPassword());
    String name = signupRequestDto.getName();
    String email = signupRequestDto.getEmail();

    Customer customer = new Customer(name, userID, password, email);
    customerRepository.save(customer);

    return "회원가입 완료";
  }
}
