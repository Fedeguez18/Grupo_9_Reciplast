package com.example.demo.modelo.resiplas;


public class DonarPlastico {

    private int idDonacion;
    private int idUsuario;
    private int cantidadADonar;
    //private Date fechaHoraDisp;
    //private boolean buscarDonacion;
    private int idPlastico;
    private String unidad;
    
    public int getIdDonacion() { return idDonacion; }
    public void setIdDonacion(int idDonacion) { this.idDonacion = idDonacion; }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public int getCantidadADonar() { return cantidadADonar; }
    public void setCantidadADonar(int cantidadADonar) { this.cantidadADonar = cantidadADonar; }

    public int getidPlastico(){
        return idPlastico;
    }
    public void setidPlastico(int idPlastico){
        this.idPlastico = idPlastico; 
    }

    public String getUnidad(){
        return unidad;
    }
    public void setUnidad(String unidad){
        this.unidad= unidad;
    }

}
