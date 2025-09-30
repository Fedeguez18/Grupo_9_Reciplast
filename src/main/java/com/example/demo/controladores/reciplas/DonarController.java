package com.example.demo.controladores.reciplas;

import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.resiplas.DonarPlastico;
import com.example.demo.servicio.reciplas.DonarService;

@RestController

public class DonarController {
    Logger logger;
    private final DonarService donarService;

    public DonarController(DonarService donarService){
        this.donarService = donarService;
    }
    
    @PostMapping("/donar")
    public void guardarDonacion(@RequestBody DonarPlastico donacion){
        
        System.out.println("holaaaaaaaaaaaaaaaaaa " + /*donacion.getUnidad()+ " " + donacion.getCantidadADonar() + " " +*/ donacion.getidPlastico() + " "+ donacion.getIdUsuario());
        donarService.guardarDonacion(donacion);
        logger.info("Se llamó al endpoint /hola"); 
    }
}
