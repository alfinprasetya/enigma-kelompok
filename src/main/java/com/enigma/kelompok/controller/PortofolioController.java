package com.enigma.kelompok.controller;

import com.enigma.kelompok.model.Portofolio;
import com.enigma.kelompok.service.PortofolioService;
import com.enigma.kelompok.utils.dto.CreatePortofolioDTO;
import com.enigma.kelompok.utils.response.PageWrapper;
import com.enigma.kelompok.utils.response.Res;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portofolios")
@RequiredArgsConstructor
public class PortofolioController {

    private final PortofolioService portofolioService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreatePortofolioDTO request) {
        return Res.renderJson(
                portofolioService.create(request),
                "Created",
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @PageableDefault(page = 0, size = 10)Pageable pageable
            ) {
        Page<Portofolio> res = portofolioService.getAll(pageable);
        PageWrapper<Portofolio> result = new PageWrapper<>(res);
        return Res.renderJson(
                result,
                "FOUND",
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        return Res.renderJson(
                portofolioService.getOne(id),
                "Found a portofolio",
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        portofolioService.delete(id);
    }

}
