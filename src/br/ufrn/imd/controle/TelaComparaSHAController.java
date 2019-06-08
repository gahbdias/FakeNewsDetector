package br.ufrn.imd.controle;

import br.ufrn.imd.modelo.Noticia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import br.ufrn.imd.Main;
import java.util.HashMap;

public class TelaComparaSHAController {
	
	private Noticia testNew;
	private HashMap<String,Noticia> hashmapBoatos;
	private String webSHA;
	
	private Scene webScraperScene;	
	private BorderPane telaWebScraper;
	Integer minLength;
	
	@FXML private Button btBuscarSHA;
	@FXML private Button voltarParaWebscraper;
	@FXML private Button btStartSimilaridade;
	@FXML private Label labelSHA;
	@FXML private Label compatibleSHA;
	@FXML private Label nonCompatibleSHA;
	
	private static Stage comparaSHAStage;
	private Scene similaridadeScene;	
	private BorderPane telaSimilaridade;	
	
	public void inicializarAtributosTelaComparaSHA( Stage wSS, HashMap<String,Noticia> hB, Noticia wSN, String wSHA, Integer mL ) {
		comparaSHAStage = wSS;
		hashmapBoatos = hB;
		testNew = wSN;
		webSHA = wSHA;
		minLength = mL;
		labelSHA.setText(webSHA);
		labelSHA.setVisible(true);
	}
	
	public void clicarBtBuscarSHA( ActionEvent event ) {
		btBuscarSHA.setVisible(false);
		System.out.println( "Buscando SHA compatível com noticia extraida de " + testNew.getLink() );
		if( possuiSHAIgual( webSHA ) ) {
			System.out.println( "A noticia possui SHA igual a boatos.csv, logo é uma FAKE NEW!" );
			compatibleSHA.setVisible(true);
			voltarParaWebscraper.setVisible(true);
		}
		else {
			System.out.println( "A noticia extraida de " + testNew.getLink() 
								+ " não possui uma SHA igual a boatos.csv. \n"
								+ "Será necessário fazer uma análise de similaridade do conteúdo." );
			nonCompatibleSHA.setVisible(true);
			btStartSimilaridade.setVisible(true);	
		}
	}
	
	private boolean possuiSHAIgual( String entry ) {
		boolean temSHAigual = false;
		temSHAigual = hashmapBoatos.containsKey( entry );
		return temSHAigual;
	}
	
	public void initTelaSimilaridade() {
		try {			
			// Carregar FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource( "visao/FXMLTelaSimilaridade.fxml" ) );
			telaSimilaridade = ( BorderPane ) loader.load();
			
			// Enviar dados
			similaridadeScene = new Scene( telaSimilaridade );
			comparaSHAStage.setScene( similaridadeScene );
			TelaSimilaridadeController fourthScreen = loader.getController();
			fourthScreen.inicializarAtributosTelaSimilaridade( comparaSHAStage, hashmapBoatos, testNew );
						
			// Mostrar scene			
			comparaSHAStage.setScene( similaridadeScene );
			comparaSHAStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	public void initTelaWebScrap() {
		
		try {
			// Carregar FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource( "visao/FXMLTelaWebScrap.fxml" ) );
			telaWebScraper = (BorderPane) loader.load();
			
			// Enviar dados
			webScraperScene = new Scene( telaWebScraper );
			comparaSHAStage.setScene( webScraperScene );
			TelaWebScrapController secondScreen = loader.getController();
			secondScreen.inicializarAtributosTelaWebScraper( comparaSHAStage, minLength, hashmapBoatos );
			
			// Mostrar scene			
			comparaSHAStage.setScene( webScraperScene );
			comparaSHAStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
