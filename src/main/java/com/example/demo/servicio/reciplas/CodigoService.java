package com.example.demo.servicio.reciplas;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CodigoDao;
import com.example.demo.dao.UsuarioDao;
import com.example.demo.modelo.resiplas.Codigo;


@Service
public class CodigoService {
    private final CodigoDao codigoDao;
    private final GenerarCodigoService codigoRanApi;
    private final UsuarioDao usuarioDao;
    private static final Logger logger = LoggerFactory.getLogger(CodigoService.class);

    public CodigoService(CodigoDao codigoDao, GenerarCodigoService codigoRanApi, UsuarioDao usuarioDao){
        this.codigoDao = codigoDao;
        this.codigoRanApi = codigoRanApi;
        this.usuarioDao = usuarioDao;
    }

    public int obtenerCodigo(int puntos){
        int codigo = codigoDao.buscarCodigo();

        if(codigo <= 0){
            List<Codigo> nuevos= codigoRanApi.generarCodigo();
            codigoDao.guardarCodigo(nuevos);
            codigo = codigoDao.buscarCodigo();
        }
        codigoDao.actualizarCodigo(codigo, puntos);

        return codigo;
    }

    public int ingresarCodigo(int codigo, int idUsuario){
        int puntosObtenidos=0;
        int puntosCodigo=0;
        if(codigoDao.verificarCodigo(codigo)){
            puntosCodigo= codigoDao.getPuntosCodigo(codigo);
            puntosObtenidos= puntosCodigo+ usuarioDao.consultarPuntos(idUsuario);
            logger.info("los puntos que se van agregar son: {}", puntosObtenidos);
            codigoDao.actualizarEstadoCodigo(codigo);
            
            usuarioDao.actualizarPuntos(puntosObtenidos, idUsuario);
        }
        
        return puntosCodigo;
    }
}
