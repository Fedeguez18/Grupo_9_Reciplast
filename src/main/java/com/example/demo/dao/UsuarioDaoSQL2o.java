package com.example.demo.dao;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class UsuarioDaoSQL2o implements IUsuarioDao {
    private final Sql2o sql2o;

    
    public UsuarioDaoSQL2o(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    
    @Override
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

