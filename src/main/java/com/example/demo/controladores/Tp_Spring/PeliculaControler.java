package com.example.demo.controladores;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modelo.Pelicula;
import com.example.demo.servicio.PeliculaService;

@RestController
@RequestMapping("/peliculas")
public class PeliculaControler {
    
    private final PeliculaService peliculaService;
    
    public PeliculaControler(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping("/mostrar")
    public List<Pelicula> mostrar() {
        return peliculaService.buscarTodo();
    }

    @GetMapping("/buscar")
    public Pelicula buscar(@RequestParam String titulo) {
        return peliculaService.buscarPorTitulo(titulo).orElse(null);
    }

    @PostMapping("/crear")
    public Pelicula crear(@RequestBody Pelicula pelicula) {
        return peliculaService.guardar(pelicula);
    }

    @DeleteMapping("/eliminar")
    public boolean eliminar(int id) {
        return peliculaService.eliminar(id);
    }
}
