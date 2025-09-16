package com.example.demo.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.*;

import com.example.demo.modelo.Libro;
import com.example.demo.servicio.LibroService;

@RestController
@RequestMapping("/libros")
    public class LibroController{
        private final LibroService servicio;

        public LibroController(LibroService servicio){
            this.servicio = servicio;
        }

        @GetMapping("/listar")
        public List<Libro> listar(){
            return servicio.findAll();
        }

        @PostMapping("/crear")
        // @ResponseStatus(HttpStatus.CREATED)
        public Libro crear(@RequestBody Libro libro){
            return servicio.save(libro);
        } 

        @GetMapping("/buscar/{id}")
        public ResponseEntity<Libro> buscar(@PathVariable Long id){
            return servicio.findById(id).map(ResponseEntity:: ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @DeleteMapping("/eliminar/{id}")
        public ResponseEntity<Void> eliminar(@PathVariable Long id){
            boolean eliminado = servicio.deleteById(id);
            if (eliminado) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.notFound().build();
                
            }
        }
    }
    