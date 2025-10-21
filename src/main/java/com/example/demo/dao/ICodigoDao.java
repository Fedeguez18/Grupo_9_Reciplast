package com.example.demo.dao;

import java.util.List;

import com.example.demo.modelo.resiplas.Codigo;

public interface ICodigoDao {
    public int verificarCodigo(String codigo);
    public void guardarCodigo(List<Codigo> codigos);
    public String obtenerCodigo(int puntos);
} 