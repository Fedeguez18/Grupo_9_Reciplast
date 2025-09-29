package com.example.demo.modelo.resiplas;

import java.sql.Date;

import lombok.Data;
@Data
public class Beneficio {
    private String tipoBeneficio;
    private String NombreBeneficio;
    private Date fechaVigencia;
    private int idBeneficio;
    private int puntosRequeridos;
    private String descripcion;
    private String Categoria;
    private Boolean Activo = false;

}
