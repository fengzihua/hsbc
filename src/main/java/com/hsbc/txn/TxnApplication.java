package com.hsbc.txn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TxnApplication {

	public static void main(String[] args) {
		SpringApplication.run(TxnApplication.class, args);
	}

}
