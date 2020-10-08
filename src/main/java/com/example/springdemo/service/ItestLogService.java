package com.example.springdemo.service;

import com.example.springdemo.pojo.Ingredient;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface ItestLogService {

    Ingredient getDataByParams(List<String> params) throws ExecutionException, InterruptedException;
}
