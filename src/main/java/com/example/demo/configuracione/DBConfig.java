package com.example.demo.configuracione;

import org.sql2o.Sql2o;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {
    @Bean
    public Sql2o sql2o() {
        return new Sql2o(
            "jdbc:mysql://localhost:3306/tu_base?useSSL=false&serverTimezone=UTC",
            "tu_usuario",
            "tu_contraseña"
        );
    }
}
