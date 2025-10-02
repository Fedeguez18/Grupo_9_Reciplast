package com.example.demo.dao;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.example.demo.modelo.resiplas.DonarPlastico;

@Repository
public class DonarDaoSQL2o implements IDonacionDao {
    
    private final Sql2o sql2o;

    public DonarDaoSQL2o(Sql2o sql2o){
        this.sql2o = sql2o;
    }

    @Override
    public void guardarDonacion(DonarPlastico donacion){
        String sql = "INSERT INTO donacion (Usuario_DNI, Plastico_idPlastico, Cant_plastico, Unidad, buscar, fechaHoraDisp) " +
                    "VALUES (:Usuario_DNI, :Plastico_idPlastico, :Cant_plastico, :Unidad, :buscar, :fechaHoraDisp)";

        try (Connection con = sql2o.open()){
            
            Number key = (Number) con.createQuery(sql, true)
            
                .addParameter("Usuario_DNI", donacion.getIdUsuario())
                .addParameter("Plastico_idPlastico", donacion.getidPlastico())
                .addParameter("Cant_plastico", donacion.getCantidadADonar())
                .addParameter("Unidad", donacion.getUnidad())
                .addParameter("buscar", donacion.getBuscar())
                .addParameter("fechaHoraDisp", donacion.getFechaHoraDisp ())
                .executeUpdate()
                .getKey();
            
            if (key != null) {
                donacion.setIdDonacion(key.intValue());
                System.out.println("Donación guardada exitosamente con ID: " + key.intValue());
            }
            
        } catch (Exception e) {
            System.err.println("Error al guardar donación: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("No se pudo guardar la donación", e);
        }
    }
}
