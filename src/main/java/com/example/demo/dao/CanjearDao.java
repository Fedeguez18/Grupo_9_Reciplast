package com.example.demo.dao;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.stereotype.Repository;
import com.example.demo.modelo.resiplas.Canjear;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class CanjearDao {

    private static final Logger logger = LoggerFactory.getLogger(CanjearDao.class);

    private final Sql2o sql2o;

    public CanjearDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    public Canjear guardarCanje(int idUser, int idBeneficio) {
        String sql = "INSERT INTO Canje (idUser, idBeneficio, fechaCanje) " +
                     "VALUES (:idUser, :idBeneficio, :fechaCanje)";

        try (Connection con = sql2o.open()) {
            Canjear canjear = new Canjear();
            canjear.setIdUser(idUser);
            canjear.setIdBeneficio(idBeneficio);
            canjear.setFechaCanje(LocalDate.now());

            Number key = (Number) con.createQuery(sql, true)
                .addParameter("idUser", idUser)
                .addParameter("idBeneficio", idBeneficio)
                .addParameter("fechaCanje", canjear.getFechaCanje())
                .executeUpdate()
                .getKey();

            canjear.setIdCanje(key.intValue());
            
            logger.info("Canje guardado correctamente: idCanje={}, idUser={}, idBeneficio={}", canjear.getIdCanje(), idUser, idBeneficio);
            return canjear;

        } catch (Exception e) {
             logger.error("Error al guardar el canje: {}", e.getMessage(), e);
            throw new RuntimeException("Error al guardar el canje", e);
        }
    }

    public boolean existeCanje(int idUser, int idBeneficio) {
    String sql = "SELECT COUNT(*) FROM Canje WHERE idUser = :idUser AND idBeneficio = :idBeneficio";

        try (Connection con = sql2o.open()) {
            int count = con.createQuery(sql)
                .addParameter("idUser", idUser)
                .addParameter("idBeneficio", idBeneficio)
                .executeScalar(Integer.class);
            boolean existe = count > 0;
            logger.debug("Verificación de canje existente: user={}, beneficio={}, existe={}", idUser, idBeneficio, existe);

            return existe;

        } catch (Exception e) {
            logger.error("Error al verificar canje: {}", e.getMessage(), e);
            return false;
        }
    }
    }

   