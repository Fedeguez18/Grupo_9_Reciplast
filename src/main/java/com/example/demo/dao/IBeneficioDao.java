package com.example.demo.dao;

import java.util.List;

import com.example.demo.modelo.resiplas.Beneficio;

public interface IBeneficioDao {
    
    public List<Beneficio> consultarBeneficios(int puntos);
    public int getPuntosBeneficio(int idBeneficio);
    public int getStock(int idBeneficio);
    public void actualizarStock(int idBeneficio, int nuevoStock);

}
