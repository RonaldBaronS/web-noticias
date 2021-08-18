package com.pe.unmsm.controller;

import com.pe.unmsm.entity.Comercio;
import com.pe.unmsm.entity.Libero;
import com.pe.unmsm.entity.Sbs;
import com.pe.unmsm.model.ComercioResponse;
import com.pe.unmsm.model.LiberoDeporteResponse;
import com.pe.unmsm.model.SbsResponse;
import com.pe.unmsm.service.NoticiaServiceImpl;
import com.pe.unmsm.util.NoticiasUtil;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/noticia")
public class ApisController {
    
    @Autowired
    private NoticiaServiceImpl service;
    
    @GetMapping("/comercio")
    public List<ComercioResponse> getComercio() {
        log.info("ApisController.getComercio");
        return service.listarComercio();
    }
    
    @PostMapping("comercio/crear")
    public String postComercio() {
        log.info("ApisController.postComercio");
        service.registrarListaComercio();
        return NoticiasUtil.MENSAJE_EXITOSO;
    }
    
    @GetMapping("/comercio/buscarPorFecha")
    public List<Comercio> buscarComercio(@RequestParam(name = "fecha") String fecha) {
        log.info("ApisController.buscarComercio");
        return service.buscarPorFecha(fecha);
    }
    
    @GetMapping("/libero")
    public List<LiberoDeporteResponse> getLiberoDeporte() {
        log.info("ApisController.getLiberoDeporte");
        return service.listarLiberoDeporte();
    }
    
    @PostMapping("/libero/crear")
    public String postLibero() {
        log.info("ApisController.postLibero");
        service.registrarListaLibero();
        return NoticiasUtil.MENSAJE_EXITOSO;
    }
    
    @GetMapping("/libero/buscarPorFecha")
    public List<Libero> buscarLibero(@RequestParam(name = "fecha") String fecha) {
        log.info("ApisController.buscarLibero");
        return service.buscarPorFechaLibero(fecha);
    }
    
    @GetMapping("/sbs")
    public List<SbsResponse> getSbs(){
        log.info("ApisController.getSbs");
        return service.listarSbs();
    }
    
    @PostMapping("/sbs/crear")
    public String postSbs() {
        log.info("ApisController.postSbs");
        service.registrarListaSbs();
        return NoticiasUtil.MENSAJE_EXITOSO;
    }
    
    @GetMapping("/sbs/buscarPorFecha")
    public List<Sbs> buscarSbs(@RequestParam(name = "fecha") String fecha) {
        log.info("ApisController.buscarSbs");
        return service.buscarPorFechaSbs(fecha);
    }
    
}