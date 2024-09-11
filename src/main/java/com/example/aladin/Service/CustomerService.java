package com.example.aladin.Service;

import com.example.aladin.Dto.SignupRequestDto;
import com.example.aladin.Entity.Customer;
import com.example.aladin.Mapper.CustomerMapper;
import com.example.aladin.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class CustomerService {

  private final PasswordEncoder passwordEncoder;
  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  public String signup(SignupRequestDto signupRequestDto) {

    String userID = signupRequestDto.getUserID();
    String password = passwordEncoder.encode(signupRequestDto.getPassword());
    String name = signupRequestDto.getName();
    String email = signupRequestDto.getEmail();

    if(duplicationUserIDCheck(userID)){
      throw new IllegalArgumentException("중복된 회원 아이디 입니다.");
    }

    if(duplicationEmailCheck(email)){
      throw new IllegalArgumentException("중복된 이메일 입니다,");
    }

    Customer customer = new Customer(name, userID, password, email);
    customerRepository.save(customer);

    return "회원가입 완료";
  }

  public String withdraw(Long customerId) {

    customerMapper.withdraw(customerId);
    return "회원탈퇴 완료";
  }

  private Boolean duplicationEmailCheck(String insertedEmail){
    return customerRepository.existsByEmail(insertedEmail);
  }

  private Boolean duplicationUserIDCheck(String insertedUserID){
    return customerRepository.existsByUserID(insertedUserID);
  }

}
