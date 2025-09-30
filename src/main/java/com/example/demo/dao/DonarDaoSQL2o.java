package com.example.demo.dao;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.example.demo.modelo.resiplas.DonarPlastico;

@Repository
public class DonarDaoSQL2o {
    
    private final Sql2o sql2o;

    public DonarDaoSQL2o(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    public void guardarDonacion(DonarPlastico donacion){
        String sql = "INSERT into donacion (Usuario_DNI, Plastico_idPlastico, Cant_plastico, Unidad)" +
                    "VALUES (:Usuario_DNI, :Plastico_idPlastico, :Cant_plastico, :Unidad)";

        try (Connection con = sql2o.open()){
            int generar = (int) con.createQuery(sql, true)
                .addParameter("Usuario_DNI",  donacion.getIdUsuario())
                .addParameter("Plastico_idPlastico", donacion.getidPlastico() )
                .addParameter("Cant_plastico", donacion.getCantidadADonar())
                .addParameter("Unidad", donacion.getUnidad())
                .executeUpdate()
                .getKey();
            
            donacion.setIdDonacion(generar);

        }
    }




}
