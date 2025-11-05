package com.example.demo.modelo.resiplas;
import java.time.LocalDate;

public class Canjear {
    private Number idCanje;
    private int idUser;
    private int idBeneficio;
    private LocalDate fechaCanje;

    public Canjear() {
    }

    public Canjear(int idCanje, int idUser, int idBeneficio, LocalDate fechaCanje) {
        this.idCanje = idCanje;
        this.idUser = idUser;
        this.idBeneficio = idBeneficio;
        this.fechaCanje = fechaCanje;
    }

    public Number getIdCanje() {
        return idCanje;
    }

    public void setIdCanje(int idCanje) {
        this.idCanje = idCanje;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdBeneficio() {
        return idBeneficio;
    }

    public void setIdBeneficio(int idBeneficio) {
        this.idBeneficio = idBeneficio;
    }

    public LocalDate getFechaCanje() {
        return fechaCanje;
    }

    public void setFechaCanje(LocalDate fechaCanje) {
        this.fechaCanje = fechaCanje;
    }
    
}
