package com.example.demo.dao.cuBeneficio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.example.demo.modelo.resiplas.Beneficio;

@Repository
public class BeneficioDao implements IBeneficioDao {
    private final Sql2o sql2o;

    public BeneficioDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    } 

    @Override
    public List<Beneficio> consultarBeneficios(int puntos) {
        String sql = "SELECT idBeneficio, Nombre_benef, Tipo, Categoria, " +
                     "Ptos_req, Fecha_vigencia, Activo, Descripcion " +
                     "FROM Beneficio " +
                     "WHERE Ptos_req <= :puntos " +
                     "AND Activo = 1 " +
                     "AND Fecha_vigencia >= CURDATE()";

        try (Connection con = sql2o.open()) {
            List<Beneficio> resultados = con.createQuery(sql)
                    .addParameter("puntos", puntos)
                    .executeAndFetch(Beneficio.class);
            
            
            return resultados;
        } catch (Exception e) {
            System.err.println("Error al consultar beneficios: " + e.getMessage());
            
            return new ArrayList<>();
        }
    }
}
