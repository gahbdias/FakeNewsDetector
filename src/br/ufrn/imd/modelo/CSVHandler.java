package br.ufrn.imd.modelo;

import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder; 

import br.ufrn.imd.modelo.Noticia;

public class CSVHandler {
	
	public static List<Noticia> csvToBean (){
		System.out.println("Convertendo boatos.csv para instancias da classe Noticia...");

		CSVReader csvReader = null; 
		try { 
			csvReader = new CSVReader( new FileReader( "././csv/boatos.csv" ) ); 
		} 
		catch ( FileNotFoundException e ) { 
			e.printStackTrace(); 
		} 		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Noticia> NoticiasLista = 
			new CsvToBeanBuilder( csvReader ).withType(Noticia.class).build().parse();
		
		return NoticiasLista;
	}
}
