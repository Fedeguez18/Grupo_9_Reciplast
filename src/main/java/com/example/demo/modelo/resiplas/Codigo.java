package com.example.demo.modelo.resiplas;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Codigo {
    private String unicoCodigo;
    private Boolean usado = false;
    private int cantPuntos;
    private boolean libre= false;

    public Codigo(String unicoCodigo){
        this.unicoCodigo = unicoCodigo;
    }
}
