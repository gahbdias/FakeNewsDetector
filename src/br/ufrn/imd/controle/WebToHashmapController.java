package br.ufrn.imd.controle;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import br.ufrn.imd.modelo.Noticia;
import br.ufrn.imd.modelo.NoticiasHashmap;
import br.ufrn.imd.modelo.SHAConverter;
import br.ufrn.imd.modelo.StringProcessor;
import br.ufrn.imd.modelo.WebScraper;

public class WebToHashmapController extends ToHashmapController {
	
	private NoticiasHashmap boatosMap;
	private NoticiasHashmap bbcMap;
	private List<Noticia> listaBoatos;
	private List<Noticia> listaBBC;
	
	public WebToHashmapController() {
		boatosMap = new NoticiasHashmap();
		bbcMap = new NoticiasHashmap();
		listaBoatos = WebScraper.scrapBoatos();
		listaBBC = WebScraper.scrapBBC();
	}

	@Override
	public void processarTextos( int minLength ) {
		for ( Noticia n : listaBoatos ) {
			n.setTextoProcessado( StringProcessor.processString( n.getConteudo(), minLength ) );
		}		
		for ( Noticia n : listaBBC ) {
			n.setTextoProcessado( StringProcessor.processString( n.getConteudo(), minLength ) );
		}
	}
	
	public void criarBoatosHashmap(int minLength) {
		System.out.println("Criando HashMap de https://www.boatos.org/...");
		HashMap<String,Noticia> temp = new HashMap<String,Noticia>();
		processarTextos(minLength);
		
		for ( Noticia n : listaBoatos ) {
			try {
				temp.put( SHAConverter.stringToSha1( n.getTextoProcessado() ), n );
			} catch ( NoSuchAlgorithmException e ) {
				e.printStackTrace();
			}
		}		
		boatosMap.setMapaNoticias(temp);
		System.out.println("HashMap boatosMap criado com sucesso!");
	}
	
	public void criarBBCHashmap(int minLength) {
		System.out.println("Criando HashMap de https://www.bbc.com/portuguese/brasil...");
		HashMap<String,Noticia> temp = new HashMap<String,Noticia>();
		
		for ( Noticia n : listaBBC ) {
			try {
				temp.put( SHAConverter.stringToSha1( n.getTextoProcessado() ), n );
			} catch ( NoSuchAlgorithmException e ) {
				e.printStackTrace();
			}
		}		
		bbcMap.setMapaNoticias(temp);
		System.out.println("HashMap bbcMap criado com sucesso!");
	}
	
	public void imprimirBBCMap() {
		System.out.println( "### BBC.COM ###" );
		for ( String key : bbcMap.getMapaNoticias().keySet() ) {
		    Noticia value = bbcMap.getMapaNoticias().get(key);
		    System.out.println( "CHAVE:" + key + ", VALOR: " + value.getTextoProcessado() );
		}
	}
	
	public void imprimirBoatosMap() {
		System.out.println( "### BOATOS.ORG ###" );
		
		for ( String key : boatosMap.getMapaNoticias().keySet() ) {
		    Noticia value = boatosMap.getMapaNoticias().get(key);
		    System.out.println( "CHAVE:" + key + ", VALOR: " + value.getTextoProcessado() );
		}
	}

	public NoticiasHashmap getBoatosMap() {
		return boatosMap;
	}

	public void setBoatosMap(NoticiasHashmap boatosMap) {
		this.boatosMap = boatosMap;
	}

	public NoticiasHashmap getBbcMap() {
		return bbcMap;
	}

	public void setBbcMap(NoticiasHashmap bbcMap) {
		this.bbcMap = bbcMap;
	}

	public List<Noticia> getListaBoatos() {
		return listaBoatos;
	}

	public void setListaBoatos(List<Noticia> listaBoatos) {
		this.listaBoatos = listaBoatos;
	}

	public List<Noticia> getListaBBC() {
		return listaBBC;
	}

	public void setListaBBC(List<Noticia> listaBBC) {
		this.listaBBC = listaBBC;
	}
}
