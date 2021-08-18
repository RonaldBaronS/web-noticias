package com.pe.unmsm.dao;

import com.pe.unmsm.entity.Sbs;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SbsDaoImpl implements SbsDao {

    @Autowired
    private SbsRepository repository;
    
    @Override
    public void save(Sbs entity) {
        log.info("SbsDaoImpl.save");
        repository.save(entity);
    }
    
    @Override
    public void saveAll(List<Sbs> entities) {
        log.info("SbsDaoImpl.saveAll");
        if(existeRegistro()) {
            repository.deleteAll();
            repository.saveAll(entities);
        }else {
            repository.saveAll(entities);
        }
    }
    
    @Override
    public List<Sbs> findByFecha(String fecha) {
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
