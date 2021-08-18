package com.pe.unmsm.dao;

import com.pe.unmsm.entity.Comercio;

import java.util.List;

public interface ComercioDao {
    
    List<Comercio> findByFecha(String fecha);
    
    void saveAll(List<Comercio> entities);
    
    void save(Comercio entity);
    
}
