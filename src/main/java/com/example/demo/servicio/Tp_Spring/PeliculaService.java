package com.example.demo.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;
import com.example.demo.modelo.Pelicula;

@Service
public class PeliculaService {
    private final List<Pelicula> peliculas = new ArrayList<>(); 
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public List<Pelicula> buscarTodo() {
        return peliculas;
    }

    public Optional<Pelicula> buscarPorTitulo(String titulo) {
        return peliculas.stream().filter(pelicula -> Objects.equals(pelicula.getTitulo(), titulo)).findFirst();
    }

    public Pelicula guardar(Pelicula pelicula) {
        if(pelicula.getId() == 0){
            pelicula.setId(idGenerator.getAndIncrement());
            
        } else {
            peliculas.removeIf(p -> Objects.equals(p.getId(), pelicula.getId()));
            
        }

        peliculas.add(pelicula);
        return pelicula;
    }

    public boolean eliminar(int id) {
        return peliculas.removeIf(pelicula -> Objects.equals(pelicula.getId(), id));
    }
}
