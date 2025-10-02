package com.example.demo.controladores.reciplas;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.resiplas.DonarPlastico;
import com.example.demo.servicio.reciplas.DonarService;

@RestController
public class DonarController {
   
    private final DonarService donarService;

    public DonarController(DonarService donarService){
        this.donarService = donarService;
    }
    
    @PostMapping("/donar")
    //@ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> guardarDonacion(@RequestBody DonarPlastico donacion){
        
        donarService.guardarDonacion(donacion);
        
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Donación guardada exitosamente");
        response.put("idDonacion", donacion.getIdDonacion());
        response.put("idUsuario", donacion.getIdUsuario());
        response.put("cantidad", donacion.getCantidadADonar() + " " + donacion.getUnidad());
        
        return response;
    }
   
}

/*
 {
  "idUsuario": 1,
  "idPlastico": 10,
  "cantidadADonar": 10,
  "fechaHoraDisp": 2025-10-03,
  "buscar": 1,
  "unidad": "kg"
}
 */