package br.ufrn.imd.visao;
import java.util.Scanner;

import br.ufrn.imd.modelo.CSVToHashmap;
import br.ufrn.imd.modelo.WebToHashmap;
import br.ufrn.imd.controle.TelaSimilaridadeController;

public class Application {
	
	public static void main(String[] args) {

		//CSVToHashmap controladorCSV = new CSVToHashmap();
		
		WebToHashmap controladorWeb = new WebToHashmap();
		System.out.println("");
		
		//int minLength = lerMinLength();
		
		System.out.println("");		
		controladorCSV.criarBoatosCSVHashmap( minLength );
		System.out.println("");
		
		controladorWeb.criarBoatosHashmap( minLength );
		System.out.println("");
		
		controladorWeb.criarBBCHashmap( minLength );
		System.out.println("");
		
		System.out.println("Iniciando análise de similaridade das noticias armazenadas...");
		
		TelaSimilaridadeController comparadorBoatosxBoatos = 
					new TelaSimilaridadeController ( controladorWeb.getBoatosMap().getMapaNoticias(), controladorCSV.getBoatosCSV().getMapaNoticias() );
		comparadorBoatosxBoatos.compararMaps();
		
		TelaSimilaridadeController comparadorBBCxBoatos = 
				new TelaSimilaridadeController ( controladorWeb.getBbcMap().getMapaNoticias(), controladorCSV.getBoatosCSV().getMapaNoticias() );
		comparadorBBCxBoatos.compararMaps();		
	}
	
	public static int lerMinLength() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Digite o tamanho das palavras que serão removidas:");
		int minLength = keyboard.nextInt();
		keyboard.close();
		return minLength;		
	}

}
