package com.example.demo;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service
public class LibroService {
    private final List<Libro> libros = new CopyOnWriteArrayList<>();
    /*Es una List thread-safe: varias hebras pueden leerla sin bloqueo seguro.
    Cómo funciona: cada vez que haces una operación de escritura (add/remove/etc.), 
    la lista crea una copia interna y escribe sobre esa copia. 
    Las lecturas siguen viendo la versión anterior hasta que termina la escritura. */
    private final AtomicLong idGenerator = new AtomicLong(1); //AtomicLong es un contador atómico y thread-safe.

    public List<Libro> findAll(){
        return Collections.unmodifiableList(libros);
    }

    public Optional<Libro> findById(Long id) {
        return libros.stream().filter(l -> Objects.equals(l.getId(), id)).findFirst();
        /*hago una lectura, filtro por IDs hasta que uno coincida con el parametro, 
        y devuelvo el primero que encuentro */
    }

    public Libro save(Libro libro){
        if(libro.getId() == null){
            libro.setId(idGenerator.getAndIncrement());
        }else{
            libros.removeIf(l-> Objects.equals(l.getId(), libro.getId()));
        }
        libros.add(libro); 
        return libro;
    }

    public boolean deleteById(Long id) {
        return libros.removeIf(l -> Objects.equals(l.getId(), id));
    }
}