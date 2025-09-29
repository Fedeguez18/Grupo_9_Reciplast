package com.example.demo.controladores.Tp_Spring;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.modelo.Tp_Spring.Pokemon;

@RestController
public class PokemonContoler {

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/pokemon")
    public Pokemon pokemon(@RequestParam String name) {

        String url = "https://pokeapi.co/api/v2/pokemon/" + name;
        Pokemon pokemon = restTemplate.getForObject(url, Pokemon.class);

        return pokemon;
    }
    
}
