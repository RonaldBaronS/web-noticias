package com.pe.everis.dao;

import com.pe.everis.entity.Comercio;

import java.time.LocalDate;
import java.util.List;

public interface ComercioDao {
    
    List<Comercio> findByFecha(LocalDate fecha);
    
    void saveAll(List<Comercio> entities);
    
    void save(Comercio entity);

}
