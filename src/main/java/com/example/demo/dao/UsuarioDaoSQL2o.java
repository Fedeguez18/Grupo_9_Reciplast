package com.example.demo.dao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDaoSQL2o {
    private final Sql2o sql2o;

    // Constructor por inyección de dependencias
    public UsuarioDaoSQL2o(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    /**
     * Consulta la cantidad de puntos de un usuario por su id (DNI).
     *
     * @param idUser identificador del usuario (DNI en la tabla Usuario).
     * @return puntos acumulados del usuario. Si no existe, retorna 0.
     */
    public int consultarPuntos(int idUser) {
        String sql = "SELECT Cant_ptos FROM Usuario WHERE DNI = :id";

        try (Connection con = sql2o.open()) {
            Integer puntos = con.createQuery(sql)
                    .addParameter("id", idUser)
                    .executeScalar(Integer.class);

            return puntos != null ? puntos : 0;
        } catch (Exception e) {
            System.err.println("Error al consultar puntos de usuario con id " + idUser + ": " + e.getMessage());
            return 0;
        }
    }
}

