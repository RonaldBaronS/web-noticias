package com.pe.everis.controller;

import com.pe.everis.entity.ComercioResponse;
import com.pe.everis.entity.LiberoDeporteResponse;
import com.pe.everis.entity.SbsResponse;
import com.pe.everis.service.NoticiaServiceImpl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/noticia")
public class ApisController {
    
    @Autowired
    private NoticiaServiceImpl service;
    
    @GetMapping("/comercio/politica")
    public List<ComercioResponse> getComercio(){
        log.info("ApisController.getComercio");
        return service.listarComercio();
    }
    
    @GetMapping("/libero/deporte")
    public List<LiberoDeporteResponse> getLiberoDeporte() {
        log.info("ApisController.getLiberoDeporte");
        return service.listarLiberoDeporte();
    }
    
    @GetMapping("/sbs/economia")
    public List<SbsResponse> getSbs(){
        log.info("ApisController.getSbs");
        return service.listarSbs();
    }
    
}
