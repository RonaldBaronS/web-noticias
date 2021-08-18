package com.pe.unmsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebJsoupApplication {
    
    public static void main(String[] args) {
        
        SpringApplication.run(WebJsoupApplication.class, args);
        
    }
}
