package com.example.demo.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

@Repository
public class PlasticoDao implements IPlasticoDao {
    private final Sql2o sql2o;
    private static final Logger logger = LoggerFactory.getLogger(CodigoDao.class);

    public PlasticoDao(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    
    @Override
    public int consultarPuntosPlastico(int idPlastico) {
       String sql = "SELECT cantPuntos FROM plastico WHERE idPlastico = :idPlastico";

       try(Connection con =  sql2o.open()){
            Integer puntos = con.createQuery(sql)
                .addParameter("idPlastico", idPlastico)
                .executeScalar(Integer.class);

            if (puntos == null) {
                logger.info("El Id:{} no existe:",idPlastico);
                return 0;
            }//borrar
            logger.info("los puntos obtenidos son: {} del ID {}", puntos, idPlastico);
            return puntos;
       }catch(Exception e){
            logger.error("Error al consultar puntos del plástico: {} ", e.getMessage());
            return -1;
       }
    }
}


