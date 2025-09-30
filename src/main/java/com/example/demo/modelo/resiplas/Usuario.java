package com.example.demo.modelo.resiplas;

public class Usuario {
    private int id;
    private int puntos;

    public Usuario(int id, int puntos) {
        this.id = id;
        this.puntos = puntos;
    }

    public int getId() {
        return id;
    }

    public int getPuntos() {
        return puntos;
    }
}
