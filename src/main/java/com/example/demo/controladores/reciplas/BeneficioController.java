package com.example.demo.controladores.reciplas;

import java.util.List;

import com.example.demo.modelo.resiplas.Beneficio;
import com.example.demo.servicio.reciplas.BeneficioService;


import org.springframework.web.bind.annotation.*;


@RestController
//@RequestMapping("/beneficios")
public class BeneficioController {
    private BeneficioService beneficioService;
    
    public BeneficioController(BeneficioService beneficioService) {
        this.beneficioService = beneficioService;
    }

    @GetMapping("/beneficio/{idUser}")
    public List<Beneficio> consultarBeneficio(@PathVariable int idUser) {
        return beneficioService.consultarBeneficio(idUser);
    }
}
