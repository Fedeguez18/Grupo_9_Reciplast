package com.example.demo.servicio.reciplas;

import org.springframework.stereotype.Service;

import com.example.demo.dao.cuBeneficio.BeneficioDao;
import com.example.demo.dao.CanjearDao;
import com.example.demo.dao.UsuarioDao;
import com.example.demo.modelo.resiplas.Canjear;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CanjearService {

    private final CanjearDao canjearDao;
    private final UsuarioDao usuarioDao;
    private final BeneficioDao beneficioDao;
    private static final Logger logger = LoggerFactory.getLogger(CanjearService.class);


    public CanjearService(CanjearDao canjearDao, UsuarioDao usuarioDao, BeneficioDao beneficioUsuarioDao) {
        this.canjearDao = canjearDao;
        this.usuarioDao = usuarioDao;
        this.beneficioDao = beneficioUsuarioDao;
    }

    public Canjear canjearPuntos(int idUser, int idBeneficio) {
        logger.debug("Iniciando proceso de canje: user={}, beneficio={}", idUser, idBeneficio);

        // 1. Verificar si ya fue canjeado
        if (canjearDao.existeCanje(idUser, idBeneficio)) {
            logger.warn("El usuario {} ya canjeó el beneficio {}", idUser, idBeneficio);
            throw new RuntimeException("Este beneficio ya fue canjeado anteriormente.");
            
        }

        // 2. Consultar puntos del usuario y del beneficio
        int puntosUser = usuarioDao.consultarPuntos(idUser);
        int puntosBeneficio = beneficioDao.getPuntosBeneficio(idBeneficio);
        int stock = beneficioDao.getStock(idBeneficio);

        logger.debug("Puntos usuario: {}, puntos beneficio: {}, stock disponible: {}", puntosUser, puntosBeneficio, stock);

        //No hay stock
        if (stock <= 0) {
             logger.warn("Sin stock disponible para beneficio {}", idBeneficio);
            throw new RuntimeException("Sin stock disponible para este beneficio.");
        }

        if (puntosUser < puntosBeneficio) {
            logger.warn("Usuario {} no tiene puntos suficientes ({} < {})", idUser, puntosUser, puntosBeneficio);
            throw new RuntimeException("No tiene puntos suficientes para este beneficio.");
        }

        // Restar puntos al usuario 
        int puntosRestantes = puntosUser - puntosBeneficio;



        usuarioDao.actualizarPuntos(puntosRestantes, idUser);
        beneficioDao.actualizarStock(idBeneficio, stock - 1);

        // Registrar el canje
        Canjear canje = canjearDao.guardarCanje(idUser, idBeneficio);

        logger.info("Canje registrado con éxito para usuario {} y beneficio {}", idUser, idBeneficio);
        
        // Actualizar estado del beneficio (si lo aplicó un determinado usuario)
        //beneficioDao.actualizarActivo(idBeneficio, false);



        return canje;
    }
}



    /*private final UsuarioDao usuarioDao;
    private final BeneficioDao beneficioDao;
    private final CanjearDao canjearDao;

    public CanjearService(UsuarioDao usuarioDao, BeneficioDao beneficioDao, CanjearDao canjearDao) {
        this.usuarioDao = usuarioDao;
        this.beneficioDao = beneficioDao;
        this.canjearDao = canjearDao;
    }

    public String canjearPuntos(int idUser, int idBeneficio) {
        try {
            // 1. Verificar si ya fue canjeado
            if (canjearDao.existeCanje(idUser, idBeneficio)) {
                return "Este beneficio ya fue canjeado anteriormente.";
            }

            // 2. Consultar puntos del usuario y del beneficio
            int puntosUsuario = usuarioDao.consultarPuntos(idUser);
            int puntosBeneficio = beneficioDao.getPuntosBeneficio(idBeneficio);

            if (puntosUsuario < puntosBeneficio) {
                return "Los puntos son insuficientes. Recolecta más para alcanzar el beneficio.";
            }

            // 3. Restar puntos y registrar canje
            int nuevosPuntos = puntosUsuario - puntosBeneficio;
            usuarioDao.actualizarPuntos(idUser, nuevosPuntos);
            canjearDao.registrarCanje(idUser, idBeneficio);

            // 4. Desactivar beneficio (si aplica)
            beneficioDao.actualizarActivo(idBeneficio, false);

            return "¡Canje exitoso! El beneficio ya puede ser usado.";
        } catch (Exception e) {
            System.err.println("Error en canje: " + e.getMessage());
            return "Ocurrió un error al procesar el canje.";
        }
    }

    
}*/
