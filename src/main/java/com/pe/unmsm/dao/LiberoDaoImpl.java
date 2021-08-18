package com.pe.unmsm.dao;

import com.pe.unmsm.entity.Libero;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LiberoDaoImpl  implements LiberoDao{

    @Autowired
    private LiberoRepository repository;
    
    @Override
    public void save(Libero entity) {
        log.info("LiberoDaoImpl.save");
        repository.save(entity);
    }
    
    @Override
    public void saveAll(List<Libero> entities) {
        log.info("LiberoDaoImpl.saveAll");
        if(existeRegistro()) {
            repository.deleteAll();
            repository.saveAll(entities);
        }else {
            repository.saveAll(entities);
        }
    }
    
    @Override
    public List<Libero> findByFecha(String fecha) {
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