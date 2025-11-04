package com.example.demo.controladores.reciplas;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.servicio.reciplas.ReciclajeService;

@RestController
public class IngresarCodigoController {
    private final ReciclajeService reciclajeService;
   

    public IngresarCodigoController(ReciclajeService reciclajeService){
        this.reciclajeService= reciclajeService;
    }
    @PostMapping("/ingresarCodigo/{codigo}/{idUsuario}")
    public String ingresarCodigo(@PathVariable int codigo, @PathVariable int idUsuario){
        
        return reciclajeService.ingresarCodigo(codigo, idUsuario);
    }
}
