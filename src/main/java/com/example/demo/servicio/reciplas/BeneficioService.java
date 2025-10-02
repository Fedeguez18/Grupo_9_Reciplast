package com.example.demo.servicio.reciplas;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.BeneficioDaoSQL2o;
import com.example.demo.dao.UsuarioDaoSQL2o;
import com.example.demo.modelo.resiplas.Beneficio;
@Service
public class BeneficioService {
    private final UsuarioDaoSQL2o userDaoSQL;
    private final BeneficioDaoSQL2o beneDaoSQL;

    public BeneficioService(UsuarioDaoSQL2o userDao, BeneficioDaoSQL2o beneDao) {
        this.userDaoSQL = userDao;
        this.beneDaoSQL = beneDao;
    }

    public List<Beneficio> consultarBeneficio(int idUser) {
        
        int puntos = userDaoSQL.consultarPuntos(idUser);
        
        List<Beneficio> beneficios = beneDaoSQL.consultarBeneficios(puntos);
        
        return beneficios;
    }
}
