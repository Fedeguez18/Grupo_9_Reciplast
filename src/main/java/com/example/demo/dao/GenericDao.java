package com.example.demo.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;

public class GenericDao <Entidad> {
    protected final Sql2o sql2o; 
    private final Class<Entidad> tipo;
    private final String tabla;
    
    private static final Logger logger = LoggerFactory.getLogger(GenericDao.class);

    public GenericDao(Sql2o sql2o, Class<Entidad> tipo){
        this.sql2o = sql2o;
        this.tipo = tipo;
        this.tabla = tipo.getSimpleName().toLowerCase();
    }

    public void insertar(Entidad entidad){
        try(Connection con = sql2o.open()){
            Field[] campos = tipo.getDeclaredFields();

            List<String> columnasDB = new ArrayList<>();
            List<String> sobreNombre = new ArrayList<>();

            for(Field campo : campos){
                campo.setAccessible(true);
                columnasDB.add(campo.getName());
                sobreNombre.add(":" + campo.getName());
            }

            String sql = String.format(
                "INSERT INTO %s (%s) VALUES (%s)",
                tabla,
                String.join(", ", columnasDB),
                String.join(", ", sobreNombre)

            );
            Query query = con.createQuery(sql);

            for(Field campo : campos){
                campo.setAccessible(true);
                Object valor = campo.get(entidad);
                query.addParameter(campo.getName(), valor);
            }

            query.executeUpdate();
        }catch (Exception e) {
            logger.error("Error en insert genérico:{} " + e.getMessage());
        } 
    }

}
