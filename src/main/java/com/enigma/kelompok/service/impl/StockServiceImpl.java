
package com.enigma.kelompok.service.impl;

import com.enigma.kelompok.model.Stock;
import com.enigma.kelompok.model.TimeSeriesResponse;
import com.enigma.kelompok.repository.StockRepository;
import com.enigma.kelompok.service.StockService;
import com.enigma.kelompok.utils.specification.StockSpecification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepo;
    private final RestClient restClient;

    public StockServiceImpl(StockRepository stockRepository) {
        this.restClient = RestClient.builder()
                .build();
        this.stockRepo = stockRepository;
    }

    @Override
    public List<Stock> getAll(String name, String code, Integer price) {
        Specification<Stock> spec = StockSpecification.getStockSpecification(name,
                code, price);
        return stockRepo.findAll(spec);
    }

    // Bagian getOne
    @Override
    public Stock getOne(Integer id) {
        return stockRepo.findById(id).orElseThrow(() -> new RuntimeException("Stock with " + id + " Not found"));
    }

    // Bagian create
    @Override
    public Stock create(Stock req) {
        return stockRepo.save(req);
    }

    // Bagian update
    @Override
    public Stock update(Integer id, Stock req) {
        Stock stock = this.getOne(id);
        stock.setName(req.getName());
        stock.setCode(req.getCode());
        stock.setPrice(req.getPrice());
        stockRepo.save(stock);
        return stock;
    }

    // Bagian delete
    @Override
    public void delete(Integer id) {
        stockRepo.deleteById(id);
    }

    public List<Stock> getFromApi(String name) {
        TimeSeriesResponse body = restClient.get()
                .uri("https://www.alphavantage.co/query?function=TIME_SERIES_MONTHLY&symbol={name}&apikey=HFEYRIWMQYVGVTHV", name)
                .retrieve()
                .body(TimeSeriesResponse.class);
        return body.toStockList();
    }
}