package com.example.demo.servicio.reciplas;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.BeneficioDaoSQL2o;
import com.example.demo.dao.UsuarioDaoSQL2o;
import com.example.demo.modelo.resiplas.Beneficio;
@Service
public class BeneficioService {
    private UsuarioDaoSQL2o userDaoSQL;
    private BeneficioDaoSQL2o beneDaoSQL;

    public BeneficioService(UsuarioDaoSQL2o userDao, BeneficioDaoSQL2o beneDao) {
        this.userDaoSQL = userDao;
        this.beneDaoSQL = beneDao;
    }

    public List<Beneficio> consultarBeneficio(int idUser) {
        System.out.println("=== Consultando beneficios para usuario: " + idUser + " ===");
        int puntos = userDaoSQL.consultarPuntos(idUser);
        System.out.println("Puntos del usuario " + idUser + ": " + puntos);
        
        List<Beneficio> beneficios = beneDaoSQL.consultarBeneficios(puntos);
        System.out.println("Beneficios encontrados: " + beneficios.size());
        
        return beneficios;
    }
}
