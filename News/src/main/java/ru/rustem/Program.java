package ru.rustem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.rustem.config.SpringConfig;

@SpringBootApplication
public class Program {
    public static void main(String[] args) {
        SpringApplication.run(SpringConfig.class, args);
    }
}
