package com.example.demo.controladores.reciplas;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.resiplas.DonarPlastico;
import com.example.demo.servicio.reciplas.DonarService;

@RestController
public class DonarController {
    private static final Logger logger = LoggerFactory.getLogger(DonarController.class);
    private final DonarService donarService;

    public DonarController(DonarService donarService){
        this.donarService = donarService;
    }
    
    @PostMapping("/donar")
    //aca
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> guardarDonacion(@RequestBody DonarPlastico donacion){
        logger.info("Recibiendo donación: Usuario={}, Plástico={}, Cantidad={} {}", 
                   donacion.getIdUsuario(), donacion.getidPlastico(), 
                   donacion.getCantidadADonar(), donacion.getUnidad());
        
        donarService.guardarDonacion(donacion);
        
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Donación guardada exitosamente");
        response.put("idDonacion", donacion.getIdDonacion());
        response.put("idUsuario", donacion.getIdUsuario());
        response.put("cantidad", donacion.getCantidadADonar() + " " + donacion.getUnidad());
        
        return response;
    }
    //hasta aca
}