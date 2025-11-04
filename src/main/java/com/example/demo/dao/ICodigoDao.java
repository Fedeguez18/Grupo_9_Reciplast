package com.example.demo.dao;

import java.util.List;

import com.example.demo.modelo.resiplas.Codigo;

public interface ICodigoDao {
    public boolean verificarCodigo(int codigo);
    public void guardarCodigo(List<Codigo> codigos);
    public int buscarCodigo();
} 