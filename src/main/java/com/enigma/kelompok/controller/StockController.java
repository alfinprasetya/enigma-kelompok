package com.enigma.kelompok.controller;

import com.enigma.kelompok.model.Stock;
import com.enigma.kelompok.service.StockService;
import com.enigma.kelompok.utils.response.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String code,
            @RequestParam(required = false) Integer price,
            @PageableDefault(page = 0, size = 10)Pageable pageable) {
        return (ResponseEntity<?>) stockService.getAll(name, code, price);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        Stock stock = stockService.getOne(id);
        return Res.renderJson(stock, "Success", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Stock req) {
        Stock createdStock = stockService.create(req);
        return Res.renderJson(createdStock, "Stock created successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Stock req) {
        Stock updatedStock = stockService.update(id, req);
        return Res.renderJson(updatedStock, "Stock updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        stockService.delete(id);
    }
}
