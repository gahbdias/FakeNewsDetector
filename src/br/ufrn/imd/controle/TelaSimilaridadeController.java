package br.ufrn.imd.controle;

import br.ufrn.imd.modelo.Noticia;
import br.ufrn.imd.modelo.SimilaridadeCosine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
//import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;
import br.ufrn.imd.Main;
import br.ufrn.imd.modelo.DistanciaLevenshtein;
import java.util.Map;
import java.util.HashMap;

public class TelaSimilaridadeController {
	
	private Noticia testNew;
	private HashMap<String,Noticia> hashmapBoatos;
	private double threshold;
	
	private double cosineResult;
	private double levenshteinResult;
	
	@FXML private Button btBuscarSHA;
	@FXML private Button btThresholdInput;
	@FXML private Spinner<Integer> thresholdInput;
	@FXML private Text startAnalysisText;
	@FXML private Text endAnalysisText;
	
	private static Stage similaridadeStage;
	private Scene finalScene;	
	private BorderPane telaFinal;	
	
	public void inicializarAtributosTelaSimilaridade( Stage cSSS, HashMap<String,Noticia> hB, Noticia wSN ) {
		similaridadeStage = cSSS;
		hashmapBoatos = hB;
		testNew = wSN;
		startAnalysisText.setVisible(false);
		endAnalysisText.setVisible(false);
	}
	
	public void clicarBtThresholdInput( ActionEvent event ) {
		threshold = thresholdInput.getValue();
		compararTestNew();
	}	
	
	public void compararTestNew() {
		startAnalysisText.setVisible(true);
		
		System.out.println("Iniciando Cosine...");
		compararCosine( testNew.getTextoProcessado() );
		System.out.println("Cosine concluido!");
		System.out.println("");
			
		System.out.println("Iniciando Levenshtein...");
		compararLevenshtein( testNew.getTextoProcessado() );
		System.out.println("Levenshtein concluido!");
			
		startAnalysisText.setVisible(false);
		endAnalysisText.setVisible(true);
			
		initTelaFinal();
	}
	
	public void compararCosine( String web ) {
		
		cosineResult = 0.0;
		double biggerCosine = 0.0;
		
		for( Map.Entry<String,Noticia> boato : hashmapBoatos.entrySet() ) {			
			cosineResult = SimilaridadeCosine.calcularSimilaridadeCosine( web, boato.getValue().getTextoProcessado() );
			if ( cosineResult > biggerCosine ) {
				biggerCosine = cosineResult;
			}			
		}
		
		cosineResult = biggerCosine;
		
		
		if ( ( cosineResult * 100) >= threshold ) {
			System.out.println( "O coeficiente de similaridade calculado utilizando o algoritmo de Cosine indica que a noticia é FALSA.");
			System.out.println( "COSINE: " + cosineResult + " / THRESHOLD: " + threshold );
		}
		else {
			System.out.println( "O coeficiente de similaridade calculado utilizando o algoritmo de Cosine indica que a noticia NÃO é falsa.");
			System.out.println( "COSINE: " + cosineResult + " / THRESHOLD: " + threshold );
		}
		
	}
	
	public void compararLevenshtein( String web ) {
		
		levenshteinResult = 0.0;
		double biggerLev = 0.0;
		
		for( Map.Entry<String,Noticia> boato : hashmapBoatos.entrySet() ) {
			levenshteinResult = DistanciaLevenshtein.calcularSimilaridadeLevenshtein( web, boato.getValue().getTextoProcessado() );	
			if ( levenshteinResult > biggerLev ) {
				biggerLev = levenshteinResult;
			}
		}
		
		levenshteinResult = biggerLev;
		
		if ( (levenshteinResult * 100) >= threshold ) {
			System.out.println( "O coeficiente de similaridade calculado utilizando Distancia de Levenshtein indica que a noticia é FALSA.");
			System.out.println( "LEVENSHTEIN: " + levenshteinResult + " / THRESHOLD: " + threshold );
		}
		else {
			System.out.println( "O coeficiente de similaridade calculado utilizando Distancia de Levenshtein indica que a noticia NÃO é falsa.");
			System.out.println( "LEVENSHTEIN: " + levenshteinResult + " / THRESHOLD: " + threshold );
		}
		
	}

	public void initTelaFinal() {
		try {			
			// Carregar FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource( "visao/FXMLTelaFinal.fxml" ) );
			telaFinal = ( BorderPane ) loader.load();
			
			// Enviar dados
			finalScene = new Scene( telaFinal );
			similaridadeStage.setScene( finalScene );
			TelaFinalController lastScreen = loader.getController();
			
			System.out.println("### COSINE: " + cosineResult );
			System.out.println("### LEV: " + levenshteinResult );
			
			lastScreen.inicializarAtributosTelaFinal( cosineResult, levenshteinResult, threshold );
						
			// Mostrar scene			
			similaridadeStage.setScene( finalScene );
			similaridadeStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
