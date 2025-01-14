package com.devsuperior.dsmeta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.devsuperior.dsmeta.entities"})
public class DsmetaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DsmetaApplication.class, args);
	}
}
