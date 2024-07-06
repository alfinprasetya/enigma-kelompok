package com.enigma.kelompok.service;

import com.enigma.kelompok.model.Stock;

import java.util.List;

public interface StockService {
    List<Stock> getAll();
    Stock getOne(Integer id);
    Stock create(Stock req);
    Stock update(Integer id, Stock req);
    void delete(Integer id);
}