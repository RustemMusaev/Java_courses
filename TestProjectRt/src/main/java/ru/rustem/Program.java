package ru.rustem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.rustem.config.SpringConfig;

/**
 * Main class for run project
 */
@SpringBootApplication
public class Program {
    public static void main(String[] args) {
        SpringApplication.run(SpringConfig.class, args);
    }
}
