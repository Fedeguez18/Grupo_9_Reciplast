package com.example.demo.servicio.reciplas;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UsuarioDao;
import com.example.demo.dao.cuBeneficio.BeneficioDao;
import com.example.demo.modelo.resiplas.Beneficio;

@Service
public class BeneficioService {
    private final UsuarioDao userDaoSQL;
    private final BeneficioDao beneDaoSQL;

    public BeneficioService(UsuarioDao userDao, BeneficioDao beneDao) {
        this.userDaoSQL = userDao;
        this.beneDaoSQL = beneDao;
    }

    public List<Beneficio> consultarBeneficio(int idUser) {
        
        int puntos = userDaoSQL.consultarPuntos(idUser);
        
        List<Beneficio> beneficios = beneDaoSQL.consultarBeneficios(puntos);
        
        return beneficios;
    }
}
