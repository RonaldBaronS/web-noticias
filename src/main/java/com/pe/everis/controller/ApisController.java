package com.pe.everis.controller;

import com.pe.everis.entity.Comercio;
import com.pe.everis.model.ComercioResponse;
import com.pe.everis.model.LiberoDeporteResponse;
import com.pe.everis.model.SbsResponse;
import com.pe.everis.service.NoticiaServiceImpl;
import com.pe.everis.util.NoticiasUtil;

import java.time.LocalDate;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    
    @GetMapping("/comercio/politica")
    public List<ComercioResponse> getComercio() {
        log.info("ApisController.getComercio");
        return service.listarComercio();
    }
    
    @PostMapping("crear/comercio/politica")
    public String postComercio() {
        log.info("ApisController.postComercio");
        service.registrarListaComercio();
        return NoticiasUtil.MENSAJE_EXITOSO;
    }
    
    @GetMapping("/comercio/buscarPorFecha")
    public List<Comercio> buscarComercio(@RequestParam(name = "fecha")
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    String fecha){
        log.info("ApisController.buscarComercio");
        return service.buscarPorFecha(fecha);
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
