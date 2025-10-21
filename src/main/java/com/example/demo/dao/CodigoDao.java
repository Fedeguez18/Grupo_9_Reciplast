package com.example.demo.dao;

import java.util.List;

import org.sql2o.Connection;

import org.sql2o.Sql2o;

import com.example.demo.modelo.resiplas.Codigo;

public  class CodigoDao implements ICodigoDao {
    private Sql2o sql2o;

    public CodigoDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public int verificarCodigo(String unicoCodigo ){
        Integer cantPuntos = 0;
        String sql = "SELECT cantPuntos From codigo WHERE unicoCodigo = :unicoCodigo  AND usado == false";
        try( Connection con = sql2o.open()){
            cantPuntos = con.createQuery(sql)
            .addParameter("unicoCodigo", unicoCodigo)
            .executeScalar(Integer.class); 
        }
        return cantPuntos;
    }
    
    @Override
    public void guardarCodigo(List<Codigo> codigos){
        String sql = "INSERT INTO codigo (unicoCodigo, usado, cantPuntos) VALUES (:unicoCodigo, :usado, :cantPuntos)";
        try (Connection con = sql2o.open()){
            for (Codigo codigo : codigos){
                con.createQuery(sql)
                .addParameter("unicoCodigo", codigo.getUnicoCodigo())
                .addParameter("usado", codigo.getUsado())
                .addParameter("cantPuntos", codigo.getCantPuntos())
                .executeUpdate();
            }
        } catch (Exception e){
            System.err.println("Error al guardar códigos: " + e.getMessage());
        }
    }

    @Override
    public String obtenerCodigo(int puntos){
        String Codigo = null;

        

        String sql1 = "SELECT unicoCodigo FROM codigo WHERE usado = false";
        try (Connection con = sql2o.open()){
            Codigo = con.createQuery(sql1)
            .executeScalar(String.class);
        } catch (Exception e){
            System.err.println("Error al obtener código: " + e.getMessage());
        }

        String sql2 = "INSERT INTO codigo (cantPuntos) VALUES (:puntos) WHERE unicoCodigo = :Codigo";
        try (Connection con = sql2o.open()){
            con.createQuery(sql2)
            .addParameter("puntos", puntos)
            .addParameter("Codigo", Codigo)
            .executeUpdate();
            
        } catch (Exception e) {
            System.err.println("Error al insertar código: " + e.getMessage());
        }
        
        return Codigo;

    }
}