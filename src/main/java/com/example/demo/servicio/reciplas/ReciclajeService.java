package com.example.demo.servicio.reciplas;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.DetalleReciclajeDao;
import com.example.demo.modelo.resiplas.DetalleReciclaje;

@Service
public class ReciclajeService {
    private final CodigoService codigoService;
    private final PuntosService puntosService;
    private final DetalleReciclajeDao detalleDao;

    public ReciclajeService(CodigoService codigoService, PuntosService puntosService,  DetalleReciclajeDao detalleDao){
        this.codigoService = codigoService;
        this.puntosService = puntosService;
        this.detalleDao = detalleDao;
    }

    public String dejarPlastico(List<DetalleReciclaje> recList){
        
        int puntosTotales= puntosService.generarPuntos(recList);
        

        if(puntosTotales <= 0){
            return "El plastico no existe ";
        }

        detalleDao.guardar(recList);
        int codigo = codigoService.obtenerCodigo(puntosTotales);

        if(codigo < 0){
            return "No se a podido generar el codigo";
        }

        return "El codigo generado es:"+ codigo;
    }

    public String ingresarCodigo(int codigo, int idUsuario){
        String mensaje;
        int puntosObtenidos;
        puntosObtenidos = codigoService.ingresarCodigo(codigo, idUsuario);

        if(puntosObtenidos==0){
            mensaje= "Codigo incorrecto";
        }else{
            mensaje="Se agregaron " + String.valueOf(puntosObtenidos) + " puntos a tu cuenta";
        }
         
        return mensaje;
    }


}
