package com.pe.unmsm.dao;

import com.pe.unmsm.entity.Libero;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LiberoRepository extends CrudRepository<Libero, Long> {
    
    @Query("SELECT x FROM Libero x WHERE x.fecha=?1")
    public List<Libero> findByFecha(String fecha);
    
}
