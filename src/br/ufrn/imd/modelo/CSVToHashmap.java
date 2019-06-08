package br.ufrn.imd.modelo;

import java.util.List;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class CSVToHashmap extends ToHashmap {
	
	private NoticiasHashmap boatosCSV;
	private List<Noticia> listaNoticias;
	
	public CSVToHashmap() {
		boatosCSV = new NoticiasHashmap();
		listaNoticias = CSVHandler.csvToBean();
	}
	
	@Override
	public void processarTextos( Integer minLength ) {
		for ( Noticia n : listaNoticias ) {
			n.setTextoProcessado( StringProcessor.processString( n.getConteudo(), minLength ) );
		}
	}
	
	public void criarBoatosCSVHashmap(int minLength) {
		System.out.println("Criando HashMap de boatos.csv...");
		HashMap<String,Noticia> temp = new HashMap<String,Noticia>();
		processarTextos(minLength);
		
		for ( Noticia n : listaNoticias ) {
			try {
				temp.put( SHAConverter.stringToSha1( n.getTextoProcessado() ), n );
			} catch ( NoSuchAlgorithmException e ) {
				e.printStackTrace();
			}
		}
		
		boatosCSV.setMapaNoticias(temp);
		System.out.println("HashMap boatosCSV criado com sucesso!");
	}
	
	public void imprimeBoatosCSV() {
		for ( String key : boatosCSV.getMapaNoticias().keySet() ) {
		    Noticia value = boatosCSV.getMapaNoticias().get(key);
		    System.out.println( "CHAVE:" + key + ", VALOR: " + value.getTextoProcessado() );
		}
	}

	public NoticiasHashmap getBoatosCSV() {
		return boatosCSV;
	}

	public void setBoatosCSV(NoticiasHashmap boatosCSV) {
		this.boatosCSV = boatosCSV;
	}

	public List<Noticia> getListaNoticias() {
		return listaNoticias;
	}

	public void setListaNoticias(List<Noticia> listaNoticias) {
		this.listaNoticias = listaNoticias;
	}
}
