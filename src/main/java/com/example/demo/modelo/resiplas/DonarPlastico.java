package com.example.demo.modelo.resiplas;

import java.sql.Date;

import lombok.Data;

@Data
public class DonarPlastico {
    private int idDonacion;
    private String NombreUsuario;
    private String TipoPlastico;
    private float cantidadADonar;
    private Date fechaHoraDisponible;
    private Boolean buscar = false;
}
