package com.tftdle;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TftdleApplication {
	public static void main(String[] args) {
		SpringApplication.run(TftdleApplication.class, args);
		System.out.println("Running");
	}

}
