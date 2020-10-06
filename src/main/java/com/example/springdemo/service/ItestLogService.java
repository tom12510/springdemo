package com.example.springdemo.service;

import com.example.springdemo.pojo.Ingredient;

import java.util.List;

public interface ItestLogService {
    Ingredient getDataByParams(List<String> params);
}
