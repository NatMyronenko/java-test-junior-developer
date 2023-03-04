package com.example.java.test.junior.developer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2
public class JavaTestJuniorDeveloperApplication {

    public  static void main(String[] args) {

        SpringApplication.run(JavaTestJuniorDeveloperApplication.class,args);
        System.out.println("@@app started");
    }

}
