package com.example.demo.dao;

import org.sql2o.Connection;

import org.sql2o.Sql2o;

public class PlasticoDao implements IPlasticoDao {
    private final Sql2o sql2o;

    public PlasticoDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    
    @Override
    public int consultarPlastico(int idPlastico) {
       String sql = "SELECT cantPuntos FROM plastico WHERE idPlastico = :idPlastico";

       try(Connection con =  sql2o.open()){
            Integer puntos = con.createQuery(sql)
                .addParameter("idPlastico", idPlastico)
                .executeScalar(Integer.class);

            if (puntos == null) {
                return 0;
            }
            
            return puntos;
       }catch(Exception e){
            System.err.println("Error al consultar puntos del plástico: " + e.getMessage());
            return 0;
       }
    }
}


