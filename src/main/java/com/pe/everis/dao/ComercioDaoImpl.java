package com.pe.everis.dao;

import com.pe.everis.entity.Comercio;

import java.time.LocalDate;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ComercioDaoImpl implements ComercioDao {

    @Autowired
    private ComercioRepository repository;
    
    @Override
    public void save(Comercio entity) {
        log.info("ComercioDaoImpl.save");
        repository.save(entity);
    }
    
    @Override
    public void saveAll(List<Comercio> entities) {
        log.info("ComercioDaoImpl.saveAll");
        if(existeRegistro()) {
            repository.deleteAll();
            repository.saveAll(entities);
        }else {
            repository.saveAll(entities);
        }
    }
    
    @Override
    public List<Comercio> findByFecha(LocalDate fecha) {
        return repository.findByFecha(fecha);
    }
    
    private boolean existeRegistro() {
        if(repository.count()==0) {
            return false;
        }else {
            return true;
        }
    }
    
}
