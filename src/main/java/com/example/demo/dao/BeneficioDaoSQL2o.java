package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;
import com.example.demo.modelo.resiplas.Beneficio;

@Repository
public class BeneficioDaoSQL2o {
    private final Sql2o sql2o;

    public BeneficioDaoSQL2o(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

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
            
            System.out.println("Consultando beneficios con puntos: " + puntos + 
                             " - Resultados: " + resultados.size());
            return resultados;
        } catch (Exception e) {
            System.err.println("Error al consultar beneficios: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
