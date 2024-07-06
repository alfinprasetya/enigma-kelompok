package com.enigma.kelompok.repository;

import com.enigma.kelompok.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
}