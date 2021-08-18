package com.pe.unmsm.service;

import com.pe.unmsm.ScrapingWeb;
import com.pe.unmsm.dao.ComercioDao;
import com.pe.unmsm.dao.LiberoDao;
import com.pe.unmsm.dao.SbsDao;
import com.pe.unmsm.entity.Comercio;
import com.pe.unmsm.entity.Libero;
import com.pe.unmsm.entity.Sbs;
import com.pe.unmsm.model.ComercioResponse;
import com.pe.unmsm.model.LiberoDeporteResponse;
import com.pe.unmsm.model.SbsResponse;
import com.pe.unmsm.util.NoticiasUtil;

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
    
    @Autowired
    private LiberoDao daoLibero;
    
    @Autowired
    private SbsDao daoSbs;
    
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
        //https://www.sbs.gob.pe/noticia
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
    
    @Override
    public List<Comercio> buscarPorFecha(String fecha) {
        log.info("NoticiaServiceImpl.buscarPorFecha");
        return dao.findByFecha(fecha);
    }
    
    @Override
    public List<Libero> buscarPorFechaLibero(String fecha) {
        log.info("NoticiaServiceImpl.buscarPorFechaLibero");
        return daoLibero.findByFecha(fecha);
    }
    
    @Override
    public List<Sbs> buscarPorFechaSbs(String fecha) {
        log.info("NoticiaServiceImpl.buscarPorFechaSbs");
        return daoSbs.findByFecha(fecha);
    }

    /*
     * Método que realiza registros cada cierto tiempo.
     */
    //@Scheduled(cron = "0 */5 * * * *")
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
            entity.setFecha(fecha.toString());
            log.info("Guardando cada registros en BD ..... ");
            System.out.println("Titulo ->  "+entity.getTitulo());
            System.out.println("Fecha -> "+entity.getFecha());
            dao.save(entity);
        }
    }
    
    /*
     * Método que realiza registros cada cierto tiempo.
     */
    //@Scheduled(cron = "0 */5 * * * *")
    @Override
    public void registrarListaLibero() {
        log.info("NoticiaServiceImpl.registrarListaLibero");
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
            /*Guardando cada registro en BD*/
            Libero entity = new Libero();
            entity.setTitulo(response.getTitulo());
            entity.setInformacion(response.getInformacion());
            DateTimeFormatter formato = DateTimeFormatter.ofPattern(NoticiasUtil.DATE_TIME_FORMATTER);
            LocalDate fecha = LocalDate.parse(obtenerFechaDelSistema(), formato);
            entity.setFecha(fecha.toString());
            log.info("Guardando cada registros en BD ..... ");
            System.out.println("Titulo ->  "+entity.getTitulo());
            daoLibero.save(entity);
        }
    }
    
    /*
     * Método que realiza registros cada cierto tiempo.
     */
    //@Scheduled(cron = "0 */5 * * * *")
    @Override
    public void registrarListaSbs() {
        log.info("NoticiaServiceImpl.registrarListaSbs");
        List<SbsResponse> listSbs = new ArrayList<>();
        //https://www.sbs.gob.pe/noticia
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
            /*Guardando cada registro en BD*/
            Sbs entity = new Sbs();
            entity.setTitulo(response.getTitulo());
            entity.setInformacion(response.getInformacion());
            DateTimeFormatter formato = DateTimeFormatter.ofPattern(NoticiasUtil.DATE_TIME_FORMATTER);
            LocalDate fecha = LocalDate.parse(obtenerFechaDelSistema(), formato);
            entity.setFecha(fecha.toString());
            log.info("Guardando cada registros en BD ..... ");
            System.out.println("Titulo ->  "+entity.getTitulo());
            System.out.println("Fecha -> "+entity.getFecha());
            daoSbs.save(entity);
        }
        
        
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
    
    
    //@Scheduled(fixedRate = 3000)
    //@Scheduled(cron = "3 * * * * ?")
}
