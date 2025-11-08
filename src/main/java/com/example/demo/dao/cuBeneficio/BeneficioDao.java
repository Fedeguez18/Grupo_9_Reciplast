package com.example.demo.dao.cuBeneficio;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.example.demo.modelo.resiplas.Beneficio;

@Repository
public class BeneficioDao implements IBeneficioDao {
    
    private static final Logger logger = LoggerFactory.getLogger(BeneficioDao.class);
    
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
            logger.debug("Consultando beneficios disponibles para usuario con {} puntos", puntos);

            List<Beneficio> resultados = con.createQuery(sql)
                    .addParameter("puntos", puntos)
                    .executeAndFetch(Beneficio.class);
            
            logger.info("Consulta de beneficios exitosa. Se encontraron {} beneficios disponibles.", resultados.size());
            return resultados;
        } catch (Exception e) {

            logger.error("Error al consultar beneficios con puntos {}: {}", puntos, e.getMessage(), e);
            return new ArrayList<>();
        }
    }

    @Override
    public int getPuntosBeneficio(int idBeneficio) {
        String sql = "SELECT Ptos_req FROM Beneficio WHERE idBeneficio = :idBeneficio";

        try (Connection con = sql2o.open()) {
            logger.debug("Obteniendo puntos requeridos para beneficio con id={}", idBeneficio);

            Integer puntos = con.createQuery(sql)
                    .addParameter("idBeneficio", idBeneficio)
                    .executeScalar(Integer.class);
            int valor = puntos != null ? puntos : 0;
            logger.info("Puntos requeridos para beneficio {}: {}", idBeneficio, valor);
            return valor;
        } catch (Exception e) {
            logger.error("Error al consultar puntos del beneficio con id {}: {}", idBeneficio, e.getMessage(), e);
            return 0;
        }
    }

    @Override
    public int getStock(int idBeneficio) {
    String sql = "SELECT stock FROM beneficio WHERE idBeneficio = :idBeneficio";
    try(Connection con = sql2o.open()) {
        logger.debug("Consultando stock del beneficio id={}", idBeneficio);


        Integer stock = con.createQuery(sql)
                    .addParameter("idBeneficio", idBeneficio)
                    .executeScalar(Integer.class);

            int valor = stock != null ? stock : 0;
            logger.info("Stock actual del beneficio {}: {}", idBeneficio, valor);
            return valor;
        } catch (Exception e) {
            logger.error("Error al consultar el stock del beneficio con id {}: {}", idBeneficio, e.getMessage(), e);
            return 0;
        }
}
    @Override
    public void actualizarStock(int idBeneficio, int nuevoStock) {
    String sql = "UPDATE Beneficio SET stock = :nuevoStock WHERE idBeneficio = :idBeneficio";
    try (Connection con = sql2o.open()) {
        logger.debug("Actualizando stock de beneficio id={} a {}", idBeneficio, nuevoStock);

        con.createQuery(sql)
                    .addParameter("nuevoStock", nuevoStock)
                    .addParameter("idBeneficio", idBeneficio)
                    .executeUpdate();
                    

           
         
    } catch (Exception e) {
        logger.error("Error al actualizar el stock del beneficio con id {}: {}", idBeneficio, e.getMessage(), e);
    }
}
}
