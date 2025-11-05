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
    private int stock;

    
    public Beneficio() {
    }

    
    /*public Beneficio(int id, String Nombre_benef, String tipo, String categoria, 
                    int puntosReq, Date fechaVigencia, boolean activo, String descripcion) {
        this.idBeneficio = id;
        this.Nombre_benef = Nombre_benef;
        this.Tipo = tipo;
        this.Categoria = categoria;
        this.Ptos_req = puntosReq;
        this.Fecha_vigencia = fechaVigencia;
        this.Activo = activo;
        this.Descripcion = descripcion;
    }*/

    // Getters y Setters (AGREGAR SETTERS también)
    public int getIdBeneficio() { return idBeneficio; }
    public void setIdBeneficio(int idBeneficio) { this.idBeneficio = idBeneficio; }
    
    public String getNombre_benef() { return Nombre_benef; }
    public void setNombre_benef(String nombre_benef) { this.Nombre_benef = nombre_benef; }
    
    public String getTipo() { return Tipo; }
    public void setTipo(String tipo) { this.Tipo = tipo; }
    
    public String getCategoria() { return Categoria; }
    public void setCategoria(String categoria) { this.Categoria = categoria; }
    
    public int getPtos_req() { return Ptos_req; }
    public void setPtos_req(int ptos_req) { this.Ptos_req = ptos_req; }
    
    public Date getFecha_vigencia() { return Fecha_vigencia; }
    public void setFecha_vigencia(Date fecha_vigencia) { this.Fecha_vigencia = fecha_vigencia; }
    
    public boolean isActivo() { return Activo; }
    public void setActivo(boolean activo) { this.Activo = activo; }
    
    public String getDescripcion() { return Descripcion; }
    public void setDescripcion(String descripcion) { this.Descripcion = descripcion; }
}