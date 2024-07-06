package com.enigma.kelompok.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.enigma.kelompok.model.Portofolio;
import com.enigma.kelompok.utils.dto.CreatePortofolioDTO;

public interface PortofolioService {
  Page<Portofolio> getAll(Pageable pageable);

  Portofolio getOne(Integer id);

  Portofolio create(CreatePortofolioDTO request);

  void delete(Integer id);
}
