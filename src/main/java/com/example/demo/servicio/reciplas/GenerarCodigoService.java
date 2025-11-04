package com.example.demo.servicio.reciplas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.modelo.resiplas.Codigo;

@Service
public class GenerarCodigoService {
    
    
    public GenerarCodigoService(){
        
    }

    public List<Codigo> generarCodigo() {
        List<Codigo> lista = new ArrayList<>();
        String urlApi="https://www.random.org/integers/?num=5&min=1000&max=9999&col=1&base=10&format=plain&rnd=new";

        try {
            URL url = new URL(urlApi);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String linea;
            while ((linea = in.readLine()) != null) {
                // Si la API devuelve un número por línea
                String valor = linea.trim();
                lista.add(new Codigo(valor));
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
    

}
