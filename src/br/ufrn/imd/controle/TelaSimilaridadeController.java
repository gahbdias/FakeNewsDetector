package br.ufrn.imd.controle;

import br.ufrn.imd.modelo.Noticia;
import br.ufrn.imd.modelo.SimilaridadeCosine;
import br.ufrn.imd.modelo.DistanciaLevenshtein;

import java.util.Map;
import java.util.Map.Entry;
import java.util.HashMap;

public class TelaSimilaridadeController {
	public static HashMap<String,Noticia> webCompareTo;
	public static HashMap<String,Noticia> CSVCompareTo;
	
	public TelaSimilaridadeController( HashMap<String,Noticia> web , HashMap<String,Noticia> CSV ){
		webCompareTo = web;
		CSVCompareTo = CSV;
	}
	
	public void compararMaps() {
		String url;
		Map.Entry<String,Noticia> getURL = webCompareTo.entrySet().iterator().next();
		Noticia element = getURL.getValue();
		if( element.getLink().contains("boatos") ) {
			url = "# BOATOS.ORG #";
		}
		else {
			url = "# BBC BRASIL #";
		}	
				
		for( Map.Entry<String,Noticia> entry : webCompareTo.entrySet() ) {
			System.out.println("");
			System.out.println( "Buscando SHA compatível da noticia de id ["
								+ entry.getValue().getId() 
								+ "] do HashMap de " + url );
			if( possuiSHAIgual( entry ) ) {
				System.out.println( "A noticia de id ["
									+ entry.getValue().getId() 
									+ "] possui uma SHA igual a boatos.csv, logo é uma FAKE NEW!" );
			}
			else {
				System.out.println( "A noticia de id ["
									+ entry.getValue().getId() 
									+ "] não possui uma SHA igual a boatos.csv. "
									+ "Será necessário fazer uma análise de similaridade do conteúdo." );
				compararCosine( entry.getValue().getConteudo() );
				compararLevenshtein( entry.getValue().getConteudo() );
				System.out.println("");
			}
		}
	}
	
	private boolean possuiSHAIgual(Entry<String, Noticia> entry) {
		boolean temSHAigual = false;
		temSHAigual = CSVCompareTo.containsKey( entry.getKey() );
		return temSHAigual;
	}
	
	public static void compararCosine( String web ) {
		double coeficienteSimilaridadeCosine = 0.0;
		for( Map.Entry<String,Noticia> boato : CSVCompareTo.entrySet() ) {
			coeficienteSimilaridadeCosine = 
					SimilaridadeCosine.calcularSimilaridadeCosine( web, boato.getValue().getConteudo() );	
		}
		System.out.println("O coeficiente de similaridade utilizando o algoritmo de similaridade Cosine é de: "
				+ coeficienteSimilaridadeCosine);
	}
	
	public void compararLevenshtein( String web ) {
		double coeficienteSimilaridadeLevenshtein = 0.0;
		for( Map.Entry<String,Noticia> boato : CSVCompareTo.entrySet() ) {
			coeficienteSimilaridadeLevenshtein =
					DistanciaLevenshtein.calcularSimilaridadeLevenshtein( web, boato.getValue().getConteudo() );	
		}
		System.out.println("O coeficiente de similaridade utilizando o algoritmo de Distancia de Levenshtein é de: "
				+ coeficienteSimilaridadeLevenshtein);
	}

}
