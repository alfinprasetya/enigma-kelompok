package com.enigma.kelompok.service.impl;

import com.enigma.kelompok.model.PortDetail;
import com.enigma.kelompok.repository.PortDetailRepository;
import com.enigma.kelompok.service.PortDetailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PortDetailServiceImpl implements PortDetailService {
    private final PortDetailRepository detailRepository;

    public PortDetailServiceImpl(PortDetailRepository detailRepository){
        this.detailRepository = detailRepository;
    }

    @Override
    public Page<PortDetail> getAll(Pageable pageable) {
        return detailRepository.findAll(pageable);
    }

    @Override
    public PortDetail getOne(Integer id) {
        return detailRepository.findById(id).orElse(null);
    }

    @Override
    public PortDetail create(PortDetail request) {
        return detailRepository.save(request);
    }

    @Override
    public void delete(Integer id) {
        detailRepository.deleteById(id);
    }
}
