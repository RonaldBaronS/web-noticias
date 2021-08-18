package com.pe.unmsm.service;

import com.pe.unmsm.entity.Comercio;
import com.pe.unmsm.entity.Libero;
import com.pe.unmsm.entity.Sbs;
import com.pe.unmsm.model.ComercioResponse;
import com.pe.unmsm.model.LiberoDeporteResponse;
import com.pe.unmsm.model.SbsResponse;

import java.util.List;

public interface NoticiaService {
    
    List<ComercioResponse> listarComercio() ;
    List<Comercio> buscarPorFecha(String fecha);
    void registrarListaComercio();
    
    List<LiberoDeporteResponse> listarLiberoDeporte();
    List<Libero> buscarPorFechaLibero(String fecha);
    void registrarListaLibero();
    
    List<SbsResponse> listarSbs();
    List<Sbs> buscarPorFechaSbs(String fecha);
    void registrarListaSbs();
    
}
