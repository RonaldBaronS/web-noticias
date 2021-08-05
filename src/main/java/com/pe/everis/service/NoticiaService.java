package com.pe.everis.service;

import com.pe.everis.entity.ComercioResponse;
import com.pe.everis.entity.LiberoDeporteResponse;
import com.pe.everis.entity.SbsResponse;

import java.util.List;

public interface NoticiaService {
    
    List<ComercioResponse> listarComercio();
    
    List<LiberoDeporteResponse> listarLiberoDeporte();
    
    List<SbsResponse> listarSbs();

}
