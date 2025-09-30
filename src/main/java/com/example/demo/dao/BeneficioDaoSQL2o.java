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

    // Constructor con inyección de dependencias
    public BeneficioDaoSQL2o(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    /**
     * Consulta todos los beneficios que un usuario puede canjear según sus puntos.
     *
     * @param puntos cantidad de puntos del usuario.
     * @return lista de beneficios disponibles.
     */
    public List<Beneficio> consultarBeneficios(int puntos) {
        String sql = "SELECT idBeneficio, Nombre_benef, Tipo, Categoria, " +
                     "Ptos_req, Fecha_vigencia, Activo, Descripcion " +
                     "FROM Beneficio " +
                     "WHERE Ptos_req <= :puntos " +
                     "AND Activo = 1 " +
                     "AND Fecha_vigencia >= CURDATE()";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("puntos", puntos)
                    .executeAndFetch(Beneficio.class);
        } catch (Exception e) {
            System.err.println("Error al consultar beneficios: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
