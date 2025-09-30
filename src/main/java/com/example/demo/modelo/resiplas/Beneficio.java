package com.example.demo.modelo.resiplas;
import java.util.Date;


public class Beneficio {
    private int idBeneficio;
    private String Nombre_benef;
    private String Categoria;
    private String Tipo;
    private int Ptos_req;
    private Date Fecha_vigencia;
    private boolean Activo;
    private String Descripcion;

    // Constructor
    public Beneficio(int id, String Nombre_benef, String tipo, String categoria, 
                    int puntosReq, Date fechaVigencia, boolean activo, String descripcion) {
        this.idBeneficio = id;
        this.Nombre_benef = Nombre_benef;
        this.Tipo = tipo;
        this.Categoria = categoria;
        this.Ptos_req = puntosReq;
        this.Fecha_vigencia = fechaVigencia;
        this.Activo = activo;
        this.Descripcion = descripcion;
    }

    // Getters
    public int getId() { return idBeneficio; }
    public String getNombreBeneficio() { return Nombre_benef; }
    public String getTipoBeneficio() { return Tipo; }
    public String getCategoria() { return Categoria; }
    public int getPuntosRequeridos() { return Ptos_req; }
    public Date getFechaVigencia() { return Fecha_vigencia; }
    public boolean getActivo() { return Activo; }
    public String getDescripcion() { return Descripcion; }
    
}
