package com.sp.file;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan
public class SpTest1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpTest1Application.class, args);
	}

}
