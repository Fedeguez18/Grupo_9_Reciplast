package com.example.demo.controladores.Tp_Spring;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.modelo.Tp_Spring.Libro;
import com.example.demo.servicio.Tp_Spring.LibroService;

@RestController
public class Controlador {
    @GetMapping("/celsiusAfahrenheit/{celsius}")
    public Map<String, Object> convertidor(@PathVariable float celsius) {
        // @PathVariable en lugar de @RequestParam porque el valor viene en la URL
        // el map lo uso para devolver un json con la respuesta

        float fahrenheit = (celsius * 9 / 5) + 32;

        Map<String, Object> respuesta = new HashMap<>(); // creo el map para devolver la respuesta en json
        respuesta.put("fahrenheit", fahrenheit);
        respuesta.put("celsius", celsius);

        return respuesta;
    }

    @GetMapping("/esPrimo/{numero}")
    public Map<String, Object> esPrimo(@PathVariable("numero") int numero) {
        boolean esPrimo = calcPrimo(numero);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("numero", numero);
        respuesta.put("esPrimo", esPrimo);
        return respuesta;
    }

    private boolean calcPrimo(int numero) {
        if (numero <= 1) return false;
        if (numero == 2) return true;
        if (numero % 2 == 0) return false;

        int limite = (int) Math.sqrt(numero);
        for (int i = 3; i <= limite; i += 2) { // solo números impares
            if (numero % i == 0) return false;
        }
        return true;
    }

    @GetMapping("/invertir/{cadena}")
    public Map<String, Object> invertir(@PathVariable("cadena") String cadena) {
        String invertida = new StringBuilder(cadena).reverse().toString();
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("original", cadena);  
        respuesta.put("invertida", invertida);
        return respuesta;
    }

    @PostMapping("/estadisticas")
    public Map<String, Object> estadisticas(@RequestBody NumerosRequest request) {
        //@RequestBody para recibir el array en formato json
        List<Integer> numeros =  request.getNumeros();

        float prom= calcPromedio(numeros);
        int min = calcMinimo(numeros);
        int max = calcMaximo(numeros);
        int cant = numeros.size();

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("promedio", prom);
        respuesta.put("minimo", min);
        respuesta.put("maximo", max);
        respuesta.put("cantidad", cant);
        return respuesta;
    }

    private float calcPromedio(List<Integer> numeros) {
        if (numeros.isEmpty()) return 0;
        int suma = 0;
        for (int num : numeros) {
            suma += num;
        }
        return (float) suma / numeros.size();
    }

    private int calcMinimo(List<Integer> numeros){
        if (numeros.isEmpty()) return 0;
        int min = numeros.get(0);
        for(int num : numeros){
            if(num < min) min = num;
        }
        return min;
    }

    private int calcMaximo(List<Integer> numeros){
        if (numeros.isEmpty()) return 0;
        int max = numeros.get(0);
        for(int num : numeros){
            if(num > max) max = num;
        }
        return max;
    }

    
    @RequestMapping("/libros")
    public class LibroController{
        private final LibroService servicio;

        public LibroController(LibroService servicio){
            this.servicio = servicio;
        }

        @GetMapping
        public List<Libro> listar(){
            return servicio.findAll();
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Libro crear(@RequestBody Libro libro){
            return servicio.save(libro);
        } 

        @GetMapping("/{id}")
        public ResponseEntity<Libro> buscar(@PathVariable Long id){
            return servicio.findById(id).map(ResponseEntity:: ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> eliminar(@PathVariable Long id){
            boolean eliminado = servicio.deleteById(id);
            if (eliminado) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
                
            }
        }
    }

}

/*  Es un DTO (Data Transfer Object):
 * se usa para transportar datos entre diferentes capas de tu aplicación, 
 * especialmente entre el cliente (frontend) y el servidor (backend). 
 * Su propósito principal es:
    # Encapsular datos: Agrupa los datos relacionados en un solo objeto
    # Transferir información: Facilita el envío de datos a través de la red (HTTP)
    # Separar responsabilidades: Mantiene separadas las entidades de base de datos de los datos 
                                que se exponen en la API
*/
class NumerosRequest {
    private List<Integer> numeros;

    public List<Integer> getNumeros() {
        return numeros;
    }

    public void setNumeros(List<Integer> numeros) {
        this.numeros = numeros;
    }
}