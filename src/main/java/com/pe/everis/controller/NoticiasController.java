package com.pe.everis.controller;

import com.pe.everis.entity.Comercio;
import com.pe.everis.model.ComercioResponse;
import com.pe.everis.model.LiberoDeporteResponse;
import com.pe.everis.model.SbsResponse;
import com.pe.everis.service.NoticiaServiceImpl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/views/clientes")
public class NoticiasController {

    @Autowired
    private NoticiaServiceImpl service;

    @GetMapping("/index")
    public String greeting() {
        return "index";
    }
    
    @GetMapping("/comerciosform")
    public String fechasForm(Model model) {
        model.addAttribute("comercio", new Comercio());
        return "/views/clientes/comercioform";
    }
    
    @GetMapping("/comercio")
    public String buscarComercioPorFecha(@RequestParam String fecha, Model model,
            @ModelAttribute("comercio") Comercio comercio) {
        model.addAttribute("comercioPorFecha",service.buscarPorFecha(fecha));
        return "/views/clientes/comercioform";
    }
    
    @GetMapping("/")
    public String listarComercio(Model model) {
        List<ComercioResponse> listar = service.listarComercio();
        model.addAttribute("titulo", "Noticias Diario el Comercio");
        model.addAttribute("noticias", listar);
        return "/views/clientes/listarComercio";
    }

    @GetMapping("/libero")
    public String listarLiberoDeporte(Model model) {
        List<LiberoDeporteResponse> listar = service.listarLiberoDeporte();
        model.addAttribute("titulo", "Noticias Líbero Deportes");
        model.addAttribute("noticias", listar);
        return "/views/clientes/listarLibero";
    }

    @GetMapping("/sbs")
    public String listarSbs(Model model) {
        List<SbsResponse> listar = service.listarSbs();
        model.addAttribute("titulo", "Noticias Superintencia de Banca,Seguros y AFP");
        model.addAttribute("noticias", listar);
        return "/views/clientes/listarSbs";
    }
    
}
