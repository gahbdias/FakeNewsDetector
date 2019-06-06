package br.ufrn.imd.visao;
import java.util.Scanner;

import br.ufrn.imd.controle.CSVToHashmapController;
import br.ufrn.imd.controle.WebToHashmapController;
import br.ufrn.imd.controle.SimilaridadeController;

public class Application {
	
	public static void main(String[] args) {
		System.out.println("########################");
		System.out.println("### FakeNewsDetector ###");
		System.out.println("########################");
		System.out.println("Iniciando aplicação...");
		System.out.println("");
		CSVToHashmapController controladorCSV = new CSVToHashmapController();
		WebToHashmapController controladorWeb = new WebToHashmapController();
		System.out.println("");
		
		int minLength = lerMinLength();
		
		System.out.println("");		
		controladorCSV.criarBoatosCSVHashmap( minLength );
		System.out.println("");
		
		controladorWeb.criarBoatosHashmap( minLength );
		System.out.println("");
		
		controladorWeb.criarBBCHashmap( minLength );
		System.out.println("");
		
		System.out.println("Iniciando análise de similaridade das noticias armazenadas...");
		
		SimilaridadeController comparadorBoatosxBoatos = 
					new SimilaridadeController ( controladorWeb.getBoatosMap().getMapaNoticias(), controladorCSV.getBoatosCSV().getMapaNoticias() );
		comparadorBoatosxBoatos.compararMaps();
		
		SimilaridadeController comparadorBBCxBoatos = 
				new SimilaridadeController ( controladorWeb.getBbcMap().getMapaNoticias(), controladorCSV.getBoatosCSV().getMapaNoticias() );
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
