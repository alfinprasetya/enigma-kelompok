package com.enigma.kelompok.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.enigma.kelompok.model.PortDetail;
import com.enigma.kelompok.model.Portofolio;
import com.enigma.kelompok.model.Stock;
import com.enigma.kelompok.model.User;
import com.enigma.kelompok.repository.PortofolioRepository;
import com.enigma.kelompok.service.PortDetailService;
import com.enigma.kelompok.service.PortofolioService;
import com.enigma.kelompok.service.StockService;
import com.enigma.kelompok.service.UserService;
import com.enigma.kelompok.utils.dto.CreatePortofolioDTO;

import jakarta.transaction.Transactional;

@Service
public class PortofolioServiceImpl implements PortofolioService {

  @Autowired
  private PortofolioRepository portofolioRepository;

  @Autowired
  private UserService userService;

  @Autowired
  private StockService stockService;

  @Autowired
  private PortDetailService portDetailService;

  @Override
  public Page<Portofolio> getAll(Pageable pageable) {
    return portofolioRepository.findAll(pageable);
  }

  @Override
  public Portofolio getOne(Integer id) {
    return portofolioRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Portofolio not found"));
  }

  @Override
  @Transactional
  public Portofolio create(CreatePortofolioDTO request) {
    User user = userService.getOne(request.getUser_id());
    Stock stock = stockService.getOne(request.getStock_id());

    Portofolio portofolio = Portofolio.builder()
        .user(user)
        .total_amount(stock.getPrice() * request.getQuantity_lot())
        .build();

    PortDetail portDetail = PortDetail.builder()
        .portofolio(portofolio)
        .stock(stock)
        .price(stock.getPrice())
        .quantity_lot(request.getQuantity_lot())
        .build();

    portDetailService.create(portDetail);

    return portofolioRepository.save(portofolio);
  }

  @Override
  public void delete(Integer id) {
    portofolioRepository.deleteById(id);
  }

}
