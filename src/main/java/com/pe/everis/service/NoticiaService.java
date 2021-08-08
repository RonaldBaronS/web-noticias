package com.pe.everis.service;

import com.pe.everis.entity.Comercio;
import com.pe.everis.model.ComercioResponse;
import com.pe.everis.model.LiberoDeporteResponse;
import com.pe.everis.model.SbsResponse;

import java.time.LocalDate;
import java.util.List;

public interface NoticiaService {
    
    List<ComercioResponse> listarComercio() ;
    
    void registrarListaComercio();
    
    List<Comercio> buscarPorFecha(String fecha);
    
    List<LiberoDeporteResponse> listarLiberoDeporte();
    
    List<SbsResponse> listarSbs();

}
