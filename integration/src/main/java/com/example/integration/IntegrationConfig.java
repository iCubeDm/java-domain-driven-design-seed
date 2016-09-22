package com.example.integration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan({
        "com.example.integration.message"
})
@PropertySource("classpath:integration.properties")
public class IntegrationConfig {
}
