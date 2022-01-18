package org.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class WebshopApplication {

    public static void main(final String[] args) {
        SpringApplication.run(WebshopApplication.class, args);
    }

}
