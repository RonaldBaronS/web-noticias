package com.pe.unmsm.dao;

import com.pe.unmsm.entity.Libero;

import java.util.List;

public interface LiberoDao {
    
    List<Libero> findByFecha(String fecha);
    
    void saveAll(List<Libero> entities);
    
    void save(Libero entity);
    
}
