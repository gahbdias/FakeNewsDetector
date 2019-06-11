package br.ufrn.imd.modelo;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import br.ufrn.imd.modelo.Noticia;
import br.ufrn.imd.modelo.WebProcessor;

public class WebScraper {	
	public static Noticia scrapBoatos( String urlString, Integer minLength ) {
		System.out.println("Carregando noticia de " + urlString );
		int id = 0;
		Noticia n = new Noticia();
		
	    try {
	      Document doc = Jsoup.connect( urlString ).get();
	    	  
	      Elements content = doc.select("p > em > span[style*=#ff0000]");
	      Elements time = doc.select("time[datetime]");
	      	      
	      n.setConteudo( content.text() );
	      System.out.println( "Conteudo da noticia: " + n.getConteudo() );
	      n.setId(id);	    	 
	      n.setLink( urlString );	    	  
	      n.setTimestamp( time.attr("datetime") ); 
	      
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	    
		WebProcessor boatosProcessor = new WebProcessor(n);
	    n = boatosProcessor.processarTextos ( minLength );
	    
	    return n;
	  }
	public static Noticia scrapBBC( String urlString, Integer minLength ) {
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
		
		WebProcessor bbcProcessor = new WebProcessor(n);
	    n = bbcProcessor.processarTextos ( minLength );
		
		return n;		
	}
}
