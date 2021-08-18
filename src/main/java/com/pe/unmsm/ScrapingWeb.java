package com.pe.unmsm;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScrapingWeb{
    /**
     * Metodo que obtine el codigo HTML de la pagina web
     * @param url de la pagina
     * @return codigo html
     */
    public static Document getHTML(String url){
        Document html = null;
        try {
            //se obtiene el codigo HTML de una pagina web
            html = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
        } catch (Exception e) {
            System.out.println("Error al obtener el codigo HTML");
        }
        return html;
    }
    /**
     * Metodo para Scrapiar pagina del DiarioADN 
     */
    public void scraping(){
        
        Elements articulos = ScrapingWeb.getHTML("https://www.sbs.gob.pe/noticia").select("article");
        for (Element noticia : articulos) {
            try {
                //se obtiene la url de la pagina de la notica y el codigo HTML
                String urlNoticia =  noticia.select("a").attr("abs:href");
                String titulo =  noticia.select("a").attr("title");
                //se muestra la informacion 
                System.out.println(" ________________________________________");
                System.out.println("Titulo >>>"+titulo);
                System.out.println("Para mayor inforamcion consultar  >>>"+ urlNoticia);
                System.out.println("_________________________________________");
            } catch (Exception e) {
                System.out.println("Error al entrar en la notica");
            }
        }
    }
    public static void main(String[] args) {
       new ScrapingWeb().scraping();
    }
}
