package com.example.demo.controladores;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controlador {
    @GetMapping("/celsiusAfahrenheit/:celcius")
    public float convertidor(@RequestParam float celsius) {
        return (celsius * 9/5) + 32;
        
    }
}
