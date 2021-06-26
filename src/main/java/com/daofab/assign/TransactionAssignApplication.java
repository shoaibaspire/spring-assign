package com.daofab.assign;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configurable
@SpringBootApplication
@EnableJpaRepositories(basePackages ={"com.daofab.repo","com.daofab.entity"})
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.daofab.assembler","com.daofab.assign","com.daofab.model","com.daofab.repo","com.daofab.entity"})
@EntityScan("com.daofab.entity")
public class TransactionAssignApplication {

	public static void main(String[] args) {
		SpringApplication.run(TransactionAssignApplication.class, args);
	}
	
}
