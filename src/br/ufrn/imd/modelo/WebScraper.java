package br.ufrn.imd.modelo;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import br.ufrn.imd.modelo.Noticia;

public class WebScraper {
	
	public static Noticia scrapBoatos( String urlString ) {
		System.out.println("Carregando noticia de " + urlString );
		int id = 0;
		Noticia n = new Noticia();
		
	    try {
	      Document doc = Jsoup.connect( urlString ).get();
	    	  
	      Elements content = doc.select(".post .entry-content p");
	      Elements time = doc.select("time[datetime]");
	    	  
	      n.setConteudo( content.first().text() );
	      n.setId(id);	    	 
	      n.setLink( urlString );	    	  
	      n.setTimestamp( time.attr("datetime") );   
	      
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }  	    
	    return n;
	  }
	
	public static Noticia scrapBBC( String urlString ) {
		System.out.println("Carregando noticia de " + urlString );
		int id = 0;
		Noticia n = new Noticia();
		
		try {
		      Document doc = Jsoup.connect( urlString ).get();
		    	  
		      Elements content = doc.select(".story-body__inner p");
		      Elements time = doc.select(".data[data-datetime]");
		    	  	    	  
		      n.setConteudo( content.text() );
		      n.setId(id);	    	 
		      n.setLink( urlString );	    	  
		      n.setTimestamp( time.attr("data-datetime") );

		    } catch (IOException e) {
		    	e.printStackTrace();
		    }	
		return n;		
	}
}
