package com.example.demo.modelo;

public class Pokemon {
    private int id;
    private String name;
    private int height;
    private int weight;

    public Pokemon(String name, int id, int height, int weight) {
        this.name = name;
        this.id = id;
        this.height = height;
        this.weight = weight;
    }

    public String getNombre() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getHeight() {
        return height;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
}
