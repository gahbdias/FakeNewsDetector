package br.ufrn.imd.modelo;

import org.apache.commons.text.similarity.LevenshteinDistance;

public class DistanciaLevenshtein {
	
	  public static double calcularSimilaridadeLevenshtein( String boato, String web ) {
		  String maior = boato; 
		  String menor = web;
		    if (boato.length() < web.length()) { // longer should always have greater length
		    	maior = web; 
		    	menor = boato;
		    }
		    int longerLength = maior.length();
	    
	    LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
	    return (longerLength - levenshteinDistance.apply(maior, menor)) / (double) longerLength;
	  }
}
