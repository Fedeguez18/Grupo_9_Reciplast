package com.example.demo.controladores.reciplas;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.TestR;

@RestController
public class Test {
    private final TestR testRepository;

    public Test(TestR testRepository) {
        this.testRepository = testRepository;
    }

    @GetMapping("/test-db")
    public String testDb() {
        return testRepository.probarConexion() ? "✅ Conectado a la BD" : "❌ Error al conectar";
    }
}
