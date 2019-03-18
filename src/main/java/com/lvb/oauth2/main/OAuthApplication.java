package com.lvb.oauth2.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages={"com.lvb.oauth2.security","com.lvb.oauth2.service","com.lvb.oauth2.endpoints"})
@EntityScan(basePackages={"com.lvb.oauth2.entity"})
@EnableJpaRepositories(basePackages={"com.lvb.oauth2.dao"})
public class OAuthApplication {
   public static void main(String n[]){
	   SpringApplication.run(OAuthApplication.class, n);
   }
}
