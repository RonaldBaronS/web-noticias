package com.pe.unmsm.dao;

import com.pe.unmsm.entity.Sbs;

import java.util.List;

public interface SbsDao {
    
    List<Sbs> findByFecha(String fecha);
    
    void saveAll(List<Sbs> entities);
    
    void save(Sbs entity);
    
}
