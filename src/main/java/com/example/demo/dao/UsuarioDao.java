package com.example.demo.dao;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class UsuarioDao implements IUsuarioDao {
    
    private static final Logger logger = LoggerFactory.getLogger(UsuarioDao.class);

    private final Sql2o sql2o;

    
    public UsuarioDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    
    @Override
    public int consultarPuntos(int idUser) {
        String sql = "SELECT puntos FROM Usuario WHERE id = :id";

        try (Connection con = sql2o.open()) {
            logger.debug("Consultando puntos del usuario con id={}", idUser);

            Integer puntos = con.createQuery(sql)
                    .addParameter("id", idUser)
                    .executeScalar(Integer.class);

            int valor = puntos != null ? puntos : 0;
            logger.info("Usuario {} tiene actualmente {} puntos", idUser, valor);
            return valor;
        } catch (Exception e) {
            logger.error("Error al consultar puntos del usuario con id {}: {}", idUser, e.getMessage(), e);
            return 0;
        }
    }

    public void actualizarPuntos(int idUser, int nuevosPuntos) {
    String sql = "UPDATE Usuario SET puntos = :puntos WHERE id = :id";
    try (Connection con = sql2o.open()) {
        
        logger.debug("Actualizando puntos del usuario id={} a {}", idUser, nuevosPuntos);

        con.createQuery(sql)
            .addParameter("puntos", nuevosPuntos)
            .addParameter("id", idUser)
            .executeUpdate();
    } catch (Exception e) {
        logger.error("Error al actualizar puntos del usuario con id {}: {}", idUser, e.getMessage(), e);
    }
}
}


