package com.example.demo.servicio.reciplas;

import org.springframework.stereotype.Service;

import com.example.demo.dao.DonarDaoSQL2o;
import com.example.demo.modelo.resiplas.DonarPlastico;

@Service
public class DonarService {
    
    private final DonarDaoSQL2o donarSql2o;

    public DonarService(DonarDaoSQL2o donarSql2o){
        this.donarSql2o = donarSql2o;
    }

    public void guardarDonacion(DonarPlastico donacion){
        System.out.println("holaaaaaaaaaaaaaaaaaa " + /*donacion.getUnidad()+ " " + donacion.getCantidadADonar() + " " +*/ donacion.getidPlastico() + " "+ donacion.getIdUsuario());
        donarSql2o.guardarDonacion(donacion);
    }
}
