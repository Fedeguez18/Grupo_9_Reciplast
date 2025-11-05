package com.example.demo.controladores.reciplas;

import org.springframework.web.bind.annotation.*;
import com.example.demo.servicio.reciplas.CanjearService;
import com.example.demo.modelo.resiplas.Canjear;

@RestController
public class CanjearController {

    private final CanjearService canjearService;

    public CanjearController(CanjearService canjearService) {
        this.canjearService = canjearService;
    }

    @GetMapping("/canjear/{idUser}/{idBeneficio}")
    public Canjear canjearPuntos(@PathVariable int idUser, @PathVariable int idBeneficio){
        Canjear canje = canjearService.canjearPuntos(idUser, idBeneficio);
        
        return canje;
    }
}


//Consulta: http://localhost:8081/canjear/2/3