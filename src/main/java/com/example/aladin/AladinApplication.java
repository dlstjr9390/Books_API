package com.example.aladin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.example.aladin.Mapper")
@SpringBootApplication
public class AladinApplication {

	public static void main(String[] args) {
		SpringApplication.run(AladinApplication.class, args);
	}

}
