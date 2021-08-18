package com.pe.unmsm.dao;

import com.pe.unmsm.entity.Sbs;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SbsRepository extends CrudRepository<Sbs, Long> {
    
    @Query("SELECT dep FROM Sbs dep WHERE dep.fecha=?1")
    public List<Sbs> findByFecha(String fecha);

}
