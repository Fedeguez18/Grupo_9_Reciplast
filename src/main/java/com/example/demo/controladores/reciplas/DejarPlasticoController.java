package com.example.demo.controladores.reciplas;


import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.resiplas.DetalleReciclaje;
import com.example.demo.servicio.reciplas.ReciclajeService;

@RestController
public class DejarPlasticoController {
    private final ReciclajeService reciclajeService;
    

    public DejarPlasticoController(ReciclajeService reciclajeService){
        this.reciclajeService = reciclajeService;

    }
    @PostMapping("/dejarPlastico")
    public String dejarPlastico(@RequestBody List<DetalleReciclaje> plasticos ){

        return reciclajeService.dejarPlastico(plasticos);

    }
}
