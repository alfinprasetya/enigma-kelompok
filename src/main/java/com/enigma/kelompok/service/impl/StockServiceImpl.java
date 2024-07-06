
package com.enigma.kelompok.service.impl;

import com.enigma.kelompok.model.Stock;
import com.enigma.kelompok.repository.StockRepository;
import com.enigma.kelompok.service.StockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepo;

public StockServiceImpl(StockRepository stockRepo){
    this.stockRepo = stockRepo;
}


//Bagian getAll
    @Override
    public List<Stock> getAll() {
        return stockRepo.findAll();
    }

    //Bagian getOne
    @Override
public Stock getOne(Integer id){
return stockRepo.findById(id).orElseThrow(() -> new RuntimeException("Stock with " + id + " Not found"));
}

//Bagian create
@Override
public Stock create(Stock req){
return stockRepo.save(req);
}

//Bagian update
@Override
public Stock update(Integer id, Stock req){
Stock stock =this.getOne(id);
stock.setName(req.getName());
stock.setCode(req.getCode());
stock.setPrice(req.getPrice());
stockRepo.save(stock);
return stock;
}

//Bagian delete
    @Override
    public void delete(Integer id){
    stockRepo.deleteById(id);
}
}
