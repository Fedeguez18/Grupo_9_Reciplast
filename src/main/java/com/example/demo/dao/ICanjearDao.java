package com.example.demo.dao;

import com.example.demo.modelo.resiplas.Canjear;

public interface ICanjearDao {
    Canjear canjearPuntos(int idUser, int idBeneficio);
    boolean registrarCanje(int idUser, int idBeneficio);
    boolean existeCanje(int idUser, int idBeneficio);
}