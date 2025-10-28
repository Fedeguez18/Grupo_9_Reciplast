package com.example.demo.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.example.demo.modelo.resiplas.Codigo;

public  class CodigoDao extends GenericDao<Codigo> implements ICodigoDao {

    private static final Logger logger = LoggerFactory.getLogger(CodigoDao.class);

    public CodigoDao(Sql2o sql2o) {
        super(sql2o, Codigo.class);
    }

    //aca el usuario ingreso el codigo y esta verificando
    @Override
    public int verificarCodigo(String unicoCodigo ){
        Integer cantPuntos = 0;
        String sql = "SELECT cantPuntos From codigo WHERE unicoCodigo = :unicoCodigo  AND usado == false";
        try( Connection con = sql2o.open()){
            cantPuntos = con.createQuery(sql)
            .addParameter("unicoCodigo", unicoCodigo)
            .executeScalar(Integer.class); 
            logger.info("verificacion correcta, los puntos son", cantPuntos);
        }catch(Exception e){
            logger.error("Error al verificar", e);
        }
        return cantPuntos;
    }
    
    // esto toma los codigos de la api y los guarda
    @Override
    public void guardarCodigo(List<Codigo> codigos){
        
        try{
            for (Codigo codigo : codigos){
                insertar(codigo);
            }
            logger.info("se guardaron {} codigos", codigos.size());
        } catch (Exception e){
            logger.error("Error al guardar códigos: ", e);
        }
    }

    //esto es para que la maquina devuelva un codigo
    @Override
    public String obtenerCodigo(int puntos){
        String codigo = null;

        String sql1 = "SELECT unicoCodigo FROM codigo WHERE usado = false";
        try (Connection con = sql2o.open()){
            codigo = con.createQuery(sql1)
            .executeScalar(String.class);

            logger.info("el codigo obtenido es:", codigo);
        } catch (Exception e){
            logger.error("Error al obtener código: ", e);
        }

        String sql2 = "UPDATE codigo SET cantPuntos = :puntos, usado = false WHERE unicoCodigo = :codigo";
        try (Connection con = sql2o.open()){
            con.createQuery(sql2)
            .addParameter("puntos", puntos)
            .addParameter("Codigo", codigo)
            .executeUpdate();
            logger.info("se actualizo en: ", codigo, "los punto: ", puntos);
        } catch (Exception e) {
            logger.error("Error al insertar código: " , e);
        }
        
        return codigo;

    }
}