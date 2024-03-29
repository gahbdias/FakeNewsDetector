package br.ufrn.imd.controle;

import br.ufrn.imd.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import java.util.HashMap;

import br.ufrn.imd.modelo.CSVToHashmap;
import br.ufrn.imd.modelo.Noticia;
import br.ufrn.imd.controle.TelaWebScrapController;

public class TelaInicialController {
	
	private CSVToHashmap HashmapCSVGen;
	Integer minLength;	
	
	private static Stage inicialStage;
	private Scene webScraperScene;	
	private BorderPane telaWebScraper;
	
	@FXML private Button btIniciar;	
	@FXML private Label status;	
	@FXML private Label msgInsertMinLength;	
	@FXML private Spinner<Integer> minLengthInput;	
	@FXML private Button btMinLengthInputOK;
	@FXML private Label legendaMinLengthInput;
	
	public void inicializarAtributosTelaInicial( Stage appStage ) {
		inicialStage = appStage;
	}
	
	public void clicarBtIniciar( ActionEvent event ) {
		status.setVisible(true);
		HashmapCSVGen = new CSVToHashmap();
		status.setText("Leitura concluída!");
				
		btIniciar.setVisible(false);
		status.setVisible(false);
		
		msgInsertMinLength.setVisible(true);
		minLengthInput.setVisible(true);
		btMinLengthInputOK.setVisible(true);
		legendaMinLengthInput.setVisible(true);
	}
	
	public void clicarBtMinLengthInputOK( ActionEvent event ) {
		minLength = minLengthInput.getValue();
		HashmapCSVGen.criarBoatosCSVHashmap( minLength );
		
		msgInsertMinLength.setVisible(false);
		minLengthInput.setVisible(false);
		btMinLengthInputOK.setVisible(false);
		legendaMinLengthInput.setVisible(false);
		
		initTelaWebScrap();
	}
	
	public void initTelaWebScrap() {
		
		HashMap<String,Noticia> hashmapBoatos = HashmapCSVGen.getBoatosCSV().getMapaNoticias();
		
		try {
			// Carregar FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource( "visao/FXMLTelaWebScrap.fxml" ) );
			telaWebScraper = (BorderPane) loader.load();
			
			// Enviar dados
			webScraperScene = new Scene( telaWebScraper );
			inicialStage.setScene( webScraperScene );
			TelaWebScrapController secondScreen = loader.getController();
			secondScreen.inicializarAtributosTelaWebScraper( inicialStage, minLength, hashmapBoatos );
			
			// Mostrar scene			
			inicialStage.setScene( webScraperScene );
			inicialStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
}
