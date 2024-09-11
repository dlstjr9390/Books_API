package com.example.aladin.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long customerId;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String userID;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private Boolean isActive = true;

  public Customer(String name, String userID, String password, String email){
    this.name = name;
    this.userID = userID;
    this.password = password;
    this.email = email;
  }
}
