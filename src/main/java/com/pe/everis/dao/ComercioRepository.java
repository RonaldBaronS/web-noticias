package com.pe.everis.dao;

import com.pe.everis.entity.Comercio;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ComercioRepository extends CrudRepository<Comercio, Long> {
    
    @Query("SELECT dep FROM Comercio dep WHERE dep.fecha=?1")
    public List<Comercio> findByFecha(LocalDate fecha);
    
}