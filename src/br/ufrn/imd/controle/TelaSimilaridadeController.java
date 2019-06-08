package br.ufrn.imd.controle;

import br.ufrn.imd.modelo.Noticia;
import br.ufrn.imd.modelo.SimilaridadeCosine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import br.ufrn.imd.Main;
import br.ufrn.imd.modelo.DistanciaLevenshtein;

import java.util.Map;
import java.util.HashMap;

public class TelaSimilaridadeController {
	
	private Noticia testNew;
	private HashMap<String,Noticia> hashmapBoatos;
	private double threshold;
	private boolean isFakeCosine;
	private boolean isFakeLevenshtein;
	
	@FXML private Button btBuscarSHA;
	@FXML private Button btThresholdInput;
	@FXML private Slider thresholdSlider;
	@FXML private Label labelLevenshtein;
	@FXML private Label labelCosine;
	@FXML private Label labelSHA;
	
	
	private static Stage similaridadeStage;
	private Scene finalScene;	
	private BorderPane telaFinal;	
	
	public void inicializarAtributosTelaSimilaridade( Stage wSS, HashMap<String,Noticia> hB, Noticia wSN ) {
		similaridadeStage = wSS;
		hashmapBoatos = hB;
		testNew = wSN;
		isFakeCosine = false;
		isFakeLevenshtein = false;
	}
	
	public void clicarBtThresholdInput( ActionEvent event ) {
		if( thresholdSlider == null ) {
			threshold = 0;
		}else { 
			threshold = thresholdSlider.getValue();
		}
		compararTestNew();
	}	
	
	public void compararTestNew() {			
			compararCosine( testNew.getConteudo() );
			compararLevenshtein( testNew.getConteudo() );
			System.out.println("");
	}
	
	public void compararCosine( String web ) {
		double coeficienteSimilaridadeCosine = 0.0;
		for( Map.Entry<String,Noticia> boato : hashmapBoatos.entrySet() ) {
			coeficienteSimilaridadeCosine = 
					SimilaridadeCosine.calcularSimilaridadeCosine( web, boato.getValue().getConteudo() );	
		}
		if ( ( coeficienteSimilaridadeCosine * 100) >= threshold ) {
			System.out.println( "O coeficiente de similaridade calculado utilizando o algoritmo de Cosine indica que a noticia é FALSA.");
			System.out.println( "COSINE: " + coeficienteSimilaridadeCosine + " / THRESHOLD: " + threshold );
		}
		else {
			System.out.println( "O coeficiente de similaridade calculado utilizando o algoritmo de Cosine indica que a noticia NÃO é falsa.");
			System.out.println( "COSINE: " + coeficienteSimilaridadeCosine + " / THRESHOLD: " + threshold );
		}
	}
	
	public void compararLevenshtein( String web ) {
		double coeficienteSimilaridadeLevenshtein = 0.0;
		for( Map.Entry<String,Noticia> boato : hashmapBoatos.entrySet() ) {
			coeficienteSimilaridadeLevenshtein =
					DistanciaLevenshtein.calcularSimilaridadeLevenshtein( web, boato.getValue().getConteudo() );	
		}
		if ( (coeficienteSimilaridadeLevenshtein * 100) >= threshold ) {
			System.out.println( "O coeficiente de similaridade calculado utilizando Distancia de Levenshtein indica que a noticia é FALSA.");
			System.out.println( "COSINE: " + coeficienteSimilaridadeLevenshtein + " / THRESHOLD: " + threshold );
		}
		else {
			System.out.println( "O coeficiente de similaridade calculado utilizando Distancia de Levenshtein indica que a noticia NÃO é falsa.");
			System.out.println( "COSINE: " + coeficienteSimilaridadeLevenshtein + " / THRESHOLD: " + threshold );
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
			lastScreen.inicializarAtributosTelaFinal( similaridadeStage, isFakeCosine, isFakeLevenshtein );
						
			// Mostrar scene			
			similaridadeStage.setScene( finalScene );
			similaridadeStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
