package br.ufrn.imd.modelo;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.ufrn.imd.modelo.Noticia;

public class WebScraper {
	
	public static List<Noticia> scrapBoatos() {
		List<Noticia> listaBoatos = new ArrayList<Noticia>();
		System.out.println("Carregando noticias de https://www.boatos.org/...");
		
	    try {
	      Document doc = Jsoup.connect("https://www.boatos.org/").get();
	      
	      Elements links = doc.select(".entry-title a[href]");
    	  int id = 0;
    	  
	      for (Element link : links) {
	    	  String url = link.attr("href");
	    	  Document boato = Jsoup.connect(url).get();
	    	  
	    	  Elements content = boato.select(".post .entry-content p");
	    	  Elements time = boato.select("time[datetime]");
	    	  
	    	  Noticia n = new Noticia();	    	  
	    	  n.setConteudo( content.first().text() );
	    	  n.setId(id);	    	 
	    	  n.setLink(url);	    	  
	    	  n.setTimestamp( time.attr("datetime") );
	    	  
	    	  listaBoatos.add(n);
	    	  
	    	  id++;
	    	  
	      }
	      
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }  	    
	    return listaBoatos;
	  }
	
	public static List<Noticia> scrapBBC() {
		List<Noticia> listaBBC = new ArrayList<Noticia>();
		System.out.println("Carregando noticias de https://www.bbc.com/portuguese/brasil...");
		
		try {
		      Document doc = Jsoup.connect("https://www.bbc.com/portuguese/brasil").get();
		      Elements links = doc.select("#comp-recent-media a[href]");
		      int id = 0;
	    	  
		      for (Element link : links) {
		    	  String url = link.attr("href");
		    	  url = "https://www.bbc.com"+url;
		    	  Document boato = Jsoup.connect(url).get();
		    	  
		    	  Elements content = boato.select(".story-body__inner p");
		    	  Elements time = boato.select(".data[data-datetime]");
		    	  
		    	  Noticia n = new Noticia();	    	  
		    	  n.setConteudo( content.text() );
		    	  n.setId(id);	    	 
		    	  n.setLink(url);	    	  
		    	  n.setTimestamp( time.attr("data-datetime") );
		    	  
		    	  listaBBC.add(n);
		    	  
		    	  id++;
		      }
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }	
		return listaBBC;		
	}
}
