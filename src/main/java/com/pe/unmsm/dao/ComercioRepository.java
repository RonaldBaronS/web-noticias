package com.pe.unmsm.dao;

import com.pe.unmsm.entity.Comercio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ComercioRepository extends CrudRepository<Comercio, Long> {
    
    @Query("SELECT dep FROM Comercio dep WHERE dep.fecha=?1")
    public List<Comercio> findByFecha(String fecha);
    
}