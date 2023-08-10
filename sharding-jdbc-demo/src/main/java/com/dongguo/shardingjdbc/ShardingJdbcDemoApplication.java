package com.dongguo.shardingjdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShardingJdbcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShardingJdbcDemoApplication.class, args);
        System.out.println("swagger：http://localhost:8080/swagger-ui/index.html");
    }

}
