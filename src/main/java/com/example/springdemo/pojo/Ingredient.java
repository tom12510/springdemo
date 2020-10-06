package com.example.springdemo.pojo;

import lombok.Data;

@Data
public class Ingredient {
    private Long id;
    private String name;
    private Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
