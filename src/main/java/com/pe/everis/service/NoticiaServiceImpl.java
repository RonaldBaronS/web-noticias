package com.pe.everis.service;

import com.pe.everis.ScrapingWeb;
import com.pe.everis.dao.ComercioDao;
import com.pe.everis.entity.Comercio;
import com.pe.everis.model.ComercioResponse;
import com.pe.everis.model.LiberoDeporteResponse;
import com.pe.everis.model.SbsResponse;
import com.pe.everis.util.NoticiasUtil;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NoticiaServiceImpl implements NoticiaService {
    
    @Autowired
    private ComercioDao dao;
    
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
    
    /*
     * Método que realiza registros cada cierto tiempo.
     */
    //@Scheduled(fixedRate = 3000)
    @Override
    public void registrarListaComercio() {
        log.info("NoticiaServiceImpl.registrarListaComercio");
        List<ComercioResponse> listComercio = new ArrayList<>();
        Elements articulos = ScrapingWeb.getHTML("https://elcomercio.pe/politica").select("article");
        for (Element noticia : articulos) {
            String urlNoticia = noticia.select("a").attr("abs:href");
            Document htmlNoticia = ScrapingWeb.getHTML(urlNoticia);
            String titulo = htmlNoticia.select("h1").text();
            ComercioResponse response = new ComercioResponse();
            response.setTitulo(titulo);
            response.setInformacion(urlNoticia);
            listComercio.add(response);
            /*Guardando cada registro en BD*/
            Comercio entity = new Comercio();
            entity.setTitulo(response.getTitulo());
            entity.setInformacion(response.getInformacion());
            DateTimeFormatter formato = DateTimeFormatter.ofPattern(NoticiasUtil.DATE_TIME_FORMATTER);
            LocalDate fecha = LocalDate.parse(obtenerFechaDelSistema(), formato);
            entity.setFecha(fecha);
            log.info("Guardando cada registros en BD ..... ");
            System.out.println("Titulo ->  "+entity.getTitulo());
            dao.save(entity);
        }
    }
    
    @Override
    public List<Comercio> buscarPorFecha(String fecha) {
        log.info("NoticiaServiceImpl.buscarPorFecha");
        DateTimeFormatter formato = DateTimeFormatter.ofPattern(NoticiasUtil.DATE_TIME_FORMATTER);
        LocalDate fecha1 = LocalDate.parse(fecha, formato); 
        return dao.findByFecha(fecha1);
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
        //https://www.sbs.gob.pe/noticia
        Elements articulos = ScrapingWeb.getHTML("https://gestion.pe/noticias/sbs").select("article");
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
    
    private String obtenerFechaDelSistema() {
        Calendar localTime = Calendar.getInstance();
        localTime.setTimeInMillis(localTime.getTimeInMillis());
        Date fecha1 = new Date(localTime.getTimeInMillis());
        SimpleDateFormat isoFormat = new SimpleDateFormat(NoticiasUtil.DATE_TIME_FORMATTER);
        String startDateTime = isoFormat.format(fecha1);
        return startDateTime;
    }
    
}
