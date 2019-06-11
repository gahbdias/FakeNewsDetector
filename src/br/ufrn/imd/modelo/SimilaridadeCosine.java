package br.ufrn.imd.modelo;
import org.apache.commons.text.similarity.CosineSimilarity;
import java.util.Map;
import java.util.HashMap;

public class SimilaridadeCosine {
	
	public static Map<CharSequence, Integer> construirMapaDeFrequenciaDosTermos
	( CharSequence[] termos ) {
        Map<CharSequence, Integer> termFreqMap = new HashMap<>();
        for ( CharSequence term : termos ) {
            Integer n = termFreqMap.get( term );
            n = ( n == null ) ? 1 : ++n;
            termFreqMap.put(term, n);
        }
        
        return termFreqMap;
    }
	
	public static double calcularSimilaridadeCosine( String boato, String web ) {
		String[] boatos = boato.split(" ");
        String[] webs = web.split(" ");
        
        CharSequence[] boatosChar = boatos;
        CharSequence[] websChar = webs;
        
        Map<CharSequence, Integer> boatosFreqMap = construirMapaDeFrequenciaDosTermos(boatosChar);
        Map<CharSequence, Integer> websFreqMap = construirMapaDeFrequenciaDosTermos(websChar);

		
		CosineSimilarity cosineSimilarity = new CosineSimilarity();
		return cosineSimilarity.cosineSimilarity( boatosFreqMap, websFreqMap );
	}
}
