package com.example.demo.servicio.reciplas;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.PlasticoDao;
import com.example.demo.modelo.resiplas.DetalleReciclaje;

@Service
public class PuntosService {
    private final PlasticoDao plasticoDao;
    
    public PuntosService(PlasticoDao plasticoDao){
        this.plasticoDao=plasticoDao; 
    }

    public int generarPuntos(List<DetalleReciclaje> recList){
        int puntosTotales = 0;
        for(DetalleReciclaje d : recList){
            int puntosPorUnidad= d.getKg() * plasticoDao.consultarPuntosPlastico(d.getIdPlastico());
            puntosTotales += puntosPorUnidad;
        }

        return puntosTotales;
    }

}
