package com.enigma.kelompok.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enigma.kelompok.model.PortDetail;

@Repository
public interface PortDetailRepository extends JpaRepository<PortDetail, Integer> {

}
