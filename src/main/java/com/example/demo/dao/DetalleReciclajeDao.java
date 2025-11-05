package com.example.demo.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

import com.example.demo.modelo.resiplas.DetalleReciclaje;

@Repository
public class DetalleReciclajeDao extends GenericDao<DetalleReciclaje> {
    
    private static final Logger logger = LoggerFactory.getLogger(CodigoDao.class);

    public DetalleReciclajeDao(Sql2o sql2o){
        super(sql2o, DetalleReciclaje.class);
    }

    public void guardar(List<DetalleReciclaje> recList){
        try{
            for (DetalleReciclaje r : recList){
                insertar(r);
            }
            logger.info("se guardo el detalle {}", recList.size());
        } catch (Exception e){
            logger.error("Error al guardar el detalle: ", e);
        }
    }
}
