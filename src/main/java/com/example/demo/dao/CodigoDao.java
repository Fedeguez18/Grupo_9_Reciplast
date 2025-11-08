package com.example.demo.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import com.example.demo.modelo.resiplas.Codigo;

@Repository
public  class CodigoDao extends GenericDao<Codigo> implements ICodigoDao {
    private static final Logger logger = LoggerFactory.getLogger(CodigoDao.class);

    public CodigoDao(Sql2o sql2o) {
        super(sql2o, Codigo.class);
    }

    //aca el usuario ingreso el codigo y esta verificando
    @Override
    public boolean verificarCodigo(int unicoCodigo){
        boolean correcta= false;
        Integer codigoDB;
        String sql = "SELECT unicoCodigo From codigo WHERE unicoCodigo = :unicoCodigo AND libre=true AND usado= false";
        try( Connection con = sql2o.open()){
            codigoDB = con.createQuery(sql)
            .addParameter("unicoCodigo", unicoCodigo)
            .executeScalar(Integer.class); 
        
            if(codigoDB != null){
                logger.info("verificacion correcta");
                correcta=true;
                   
            }else{
                logger.warn("El código {} no fue encontrado en la base de datos.", unicoCodigo); 
            }

            
        }catch(Exception e){
            logger.error("Error al verificar", e);
            return false;
        }

       return correcta;

    }

    public int getPuntosCodigo(int codigo){
        
        Integer puntos;
        String sql = "SELECT cantPuntos From codigo WHERE unicoCodigo = :unicoCodigo AND libre=true AND usado= false";
        try( Connection con = sql2o.open()){
            puntos = con.createQuery(sql)
            .addParameter("unicoCodigo", codigo)
            .executeScalar(Integer.class); 
            
        }catch(Exception e){
            logger.error("Error al verificar", e);
            return 0;
        }

       return puntos;


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
    public int buscarCodigo(){
        Integer codigo;

        String sql1 = "SELECT unicoCodigo FROM codigo WHERE libre = false";
        try (Connection con = sql2o.open()){
            codigo = con.createQuery(sql1)
            .executeScalar(Integer.class);

            if(codigo== null){
                codigo= 0;
            }

            logger.info("el codigo obtenido es:{}", codigo);
        } catch (Exception e){
            logger.error("Error al obtener código:{} ", e);
            return -1;
        }
        return codigo;
    }

    public void actualizarCodigo(int codigo, int puntos ){
        String sql2 = "UPDATE codigo SET cantPuntos = :puntos, libre = true WHERE unicoCodigo = :codigo";
        try (Connection con = sql2o.open()){
            con.createQuery(sql2)
            .addParameter("puntos", puntos)
            .addParameter("codigo", codigo)
            .executeUpdate();
            logger.info("se actualizo en: {} los puntos: {}", codigo, puntos);
        } catch (Exception e) {
            logger.error("Error al insertar código: " , e);
        }
    
    }

    public void actualizarEstadoCodigo(int codigo){
        String sql2 = "UPDATE codigo SET usado = true WHERE unicoCodigo = :codigo";
        try (Connection con = sql2o.open()){
            con.createQuery(sql2)
            .addParameter("codigo", codigo)
            .executeUpdate();
            logger.info("se actualizo en: {}", codigo);
        } catch (Exception e) {
            logger.error("Error al insertar código: " , e);
        }
    }
}