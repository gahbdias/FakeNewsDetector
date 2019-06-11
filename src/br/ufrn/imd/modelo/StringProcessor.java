package br.ufrn.imd.modelo;

import java.text.Normalizer;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;


public class StringProcessor {
	
	public static String processString( String inicial, int minLength ) {
		
		String result = inicial;
		
		result = result.trim();		
		result = removerNumeros( result );
		result = removerAcentos ( result );
		result = removerMaiusculas( result );
		result = removerPontuacao( result );
		result = removerPalavrasPequenas( result, minLength );
		result = removerRepetidas( result );
		result = organizarAlfabeticamente( result );		
		result = result.trim();
		
		return result;
	}
	
	public static String removerNumeros( String comNumeros ) {
		String semNumeros = comNumeros.replaceAll("\\d","");
		return semNumeros;
	}
	public static String removerAcentos( String comAcentos ) {		
		comAcentos = org.apache.commons.lang3.StringUtils.defaultString( comAcentos );
	    String semAcentos = Normalizer.normalize( comAcentos, Normalizer.Form.NFD );
	    Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
	    return pattern.matcher( semAcentos ).replaceAll("");
	}
	public static String removerMaiusculas( String comMaiusculas ) {
		String semMaiusculas = comMaiusculas.toLowerCase();			
		return semMaiusculas;		
	}
	public static String removerPontuacao( String comPontuacao ) {
		String semPontuacao = comPontuacao.replaceAll("/^[a-z]+$/", "");
		semPontuacao = semPontuacao.replaceAll("\\p{Punct}", "");
		return semPontuacao;
	}
	public static String removerPalavrasPequenas( String comPalavrasPequenas, int minLength ) {	
		
		StringTokenizer tokenizer = new StringTokenizer(comPalavrasPequenas, " ");
		StringBuilder palavrasRemovidas = new StringBuilder();
		  while( tokenizer.hasMoreTokens() ){
		    String token = tokenizer.nextToken();
		    if( token.length() > minLength ){
		    	palavrasRemovidas.append(token);
		    	palavrasRemovidas.append(" ");
		  }
		}
		//keyboard.close();
		return palavrasRemovidas.toString();
	}
	public static String removerRepetidas( String comRepetidas ) {
		String semRepetidas = comRepetidas;
	      
		semRepetidas = Arrays.stream( semRepetidas.split("\\s+")).distinct().collect( Collectors.joining(" ") );
		
		return semRepetidas;
	}
	public static String organizarAlfabeticamente( String naoOrganizada ) {
		String organizadaAlfabeticamente;
		String[] strArray = naoOrganizada.split(" ");
		String temp;
		
		for (int i=0; i < strArray.length; i++){
            for (int j = i + 1; j < strArray.length; j++){
                if ( strArray[i].compareTo( strArray[j] ) > 0 ){
                    temp = strArray[i];
                    strArray[i] = strArray[j];
                    strArray[j] = temp;
                }
            }
        }
		
		StringBuilder builder = new StringBuilder();
		for (String string : strArray) {
		    builder.append(string+" ");
		}
		organizadaAlfabeticamente = builder.toString();
		return organizadaAlfabeticamente;
	}

}
