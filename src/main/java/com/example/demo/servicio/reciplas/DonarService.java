package com.example.demo.servicio.reciplas;

import org.springframework.stereotype.Service;

import com.example.demo.dao.cuDonar.DonarDao;
import com.example.demo.modelo.resiplas.DonarPlastico;

@Service
public class DonarService {
    
    private final DonarDao donarSql2o;

    public DonarService(DonarDao donarSql2o){
        this.donarSql2o = donarSql2o;
    }

    public DonarPlastico guardarDonacion(DonarPlastico donacion){
       return  donarSql2o.guardarDonacion(donacion);
        
    }
}
