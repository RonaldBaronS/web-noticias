package com.pe.everis.service;

import com.pe.everis.ScrapingWeb;
import com.pe.everis.entity.ComercioResponse;
import com.pe.everis.entity.LiberoDeporteResponse;
import com.pe.everis.entity.SbsResponse;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NoticiaServiceImpl implements NoticiaService {
    
    @Override
    public List<ComercioResponse> listarComercio() {
        log.info("NoticiaServiceImpl.listarComercio");
        List<ComercioResponse> listComercio = new ArrayList<>();
        Elements articulos = ScrapingWeb.getHTML("https://elcomercio.pe/politica").select("article");
        for (Element noticia : articulos) {
            String urlNoticia = noticia.select("a").attr("abs:href");
            Document htmlNoticia = ScrapingWeb.getHTML(urlNoticia);
            String titulo = htmlNoticia.select("h1").text();
            ComercioResponse response = new ComercioResponse();
            response.setTitulo(titulo);
            response.setInformacion(urlNoticia);
            log.info("titulo : " + titulo);
            log.info("informacion " + htmlNoticia);
            listComercio.add(response);
        }
        return listComercio;
    }
    
    @Override
    public List<LiberoDeporteResponse> listarLiberoDeporte() {
        log.info("NoticiaServiceImpl.listarLiberoDeporte");
        List<LiberoDeporteResponse> listLiberoDeporte = new ArrayList<>();
        Elements articulos = ScrapingWeb.getHTML("https://libero.pe/futbol-peruano").select("article");
        for (Element noticia : articulos) {
            String urlNoticia = noticia.select("a").attr("abs:href");
            Document htmlNoticia = ScrapingWeb.getHTML(urlNoticia);
            String titulo = htmlNoticia.select("h1").text();
            LiberoDeporteResponse response = new LiberoDeporteResponse();
            response.setTitulo(titulo);
            response.setInformacion(urlNoticia);
            log.info("titulo : " + titulo);
            log.info("informacion " + htmlNoticia);
            listLiberoDeporte.add(response);
        }
        return listLiberoDeporte;
    }
    
    @Override
    public List<SbsResponse> listarSbs() {
        log.info("NoticiaServiceImpl.listarSbs");
        List<SbsResponse> listSbs = new ArrayList<>();
        Elements articulos = ScrapingWeb.getHTML("https://www.sbs.gob.pe/noticia").select("article");
        for (Element noticia : articulos) {
            // se obtiene la url de la pagina de la notica y el codigo HTML
            String urlNoticia = noticia.select("a").attr("abs:href");
            String titulo = noticia.select("a").attr("title");
            // se muestra la informacion
            SbsResponse response = new SbsResponse();
            response.setTitulo(titulo);
            response.setInformacion(urlNoticia);
            log.info("titulo : " + titulo);
            log.info("informacion " + urlNoticia);
            listSbs.add(response);
        }
        return listSbs;
    }
    
    public static Document getHTML(String url) {
        Document html = null;
        try {
            // se obtiene el codigo HTML de una pagina web
            html = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
        } catch (Exception e) {
            System.out.println("Error al obtener el codigo HTML");
        }
        return html;
    }
    
}
