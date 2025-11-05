package com.example.demo.configuracione;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sql2o.Sql2o;

@Configuration
public class DBConfig {
    private Sql2o sql2o;

    @Bean
    public Sql2o sql2o() {
        if(sql2o == null){
            sql2o= new Sql2o(
            "jdbc:mysql://localhost:3306/reciplast?useSSL=false&serverTimezone=UTC",
            "root",
            ""
        );
        }
        return sql2o;
    }
}
