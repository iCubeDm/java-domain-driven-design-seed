package com.example.persistence;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan({"com.example.persistence.repository"})
@Import({MemoryDataSourceConfig.class, PersistentDataSourceConfig.class, JpaConfig.class})
public class PersistenceConfig {

}
