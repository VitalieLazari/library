package com.example.library.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;

@Configuration
public class MongoConfiguration
{
    private Environment environment;
    public MongoConfiguration(Environment environment){
        this.environment = environment;
    }
    @Bean
    @DependsOn("embeddedMongoServer")
    public MongoClient mongoClient() {
        int port =
                this.environment.getProperty("local.mongo.port",
                        Integer.class);
        return new MongoClient("localhost",port);
    }
}
