package com.example.demo.dao.cuBeneficio;

import java.util.List;

import com.example.demo.modelo.resiplas.Beneficio;

public interface IBeneficioDao {
    
    public List<Beneficio> consultarBeneficios(int puntos);

}
