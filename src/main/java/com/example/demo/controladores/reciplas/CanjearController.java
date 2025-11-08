package com.example.demo.controladores.reciplas;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.resiplas.Canjear;
import com.example.demo.servicio.reciplas.CanjearService;

@RestController
public class CanjearController {

    private final CanjearService canjearService;

    public CanjearController(CanjearService canjearService) {
        this.canjearService = canjearService;
    }

    @PostMapping("/canjear/{idUser}/{idBeneficio}")
    public Canjear canjearPuntos(@PathVariable int idUser, @PathVariable int idBeneficio){
        Canjear canje = canjearService.canjearPuntos(idUser, idBeneficio);
        
        return canje;
    }
}


//Consulta: http://localhost:8081/canjear/3/100