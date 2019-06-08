package br.ufrn.imd.controle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Hyperlink;
import javafx.event.ActionEvent;
import br.ufrn.imd.modelo.WebScraper;
import edu.stanford.ejalbert.BrowserLauncher;
import edu.stanford.ejalbert.exception.BrowserLaunchingInitializingException;
import edu.stanford.ejalbert.exception.UnsupportedOperatingSystemException;
import br.ufrn.imd.Main;
import br.ufrn.imd.modelo.Noticia;
import br.ufrn.imd.modelo.SHAConverter;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class TelaWebScrapController {
	
	private String noticiaURL;
	private Noticia webScraperNoticia;
	boolean isWebNoticiaLoaded;
	private String webSHA;
	
	private HashMap<String,Noticia> hashmapBoatos;
	Integer minLength;	
	
	private static Stage webScraperStage;
	private Scene comparaSHAScene;	
	private BorderPane telaComparaSHA;
	
	@FXML private Hyperlink boatosLink;
	@FXML private Hyperlink bbcLink;
	@FXML private TextField URLInput;	
	@FXML private Button btURLInputOK;
	@FXML private Label inputAviso;
	
	public TelaWebScrapController(){
		webScraperNoticia = new Noticia();
	}
	
	public void inicializarAtributosTelaWebScraper( Stage iS, Integer mL, HashMap<String,Noticia> hB ){
		webScraperStage = iS;
		minLength = mL;
		hashmapBoatos = hB;
	}
	
	public void clicarbtURLInputOK( ActionEvent event ) {
		
		isWebNoticiaLoaded = false;
		noticiaURL = URLInput.getText();
			
		if( noticiaURL.startsWith("https://www.boatos.org/") ) {
			webScraperNoticia = WebScraper.scrapBoatos( noticiaURL, minLength );
			isWebNoticiaLoaded = true;
			inputAviso.setVisible(false);
		}
		else if ( noticiaURL.startsWith("https://www.bbc.com/portuguese/") ) {
			webScraperNoticia = WebScraper.scrapBBC( noticiaURL, minLength );
			isWebNoticiaLoaded = true;
			inputAviso.setVisible(false);
		}
		else {
			inputAviso.setVisible(true);
		}
		
		if ( isWebNoticiaLoaded ) {
			System.out.println( "Noticia carregada com sucesso!\nTexto processado: [" + webScraperNoticia.getTextoProcessado() + "]" );
			generateSHA();
			initTelaComparaSHA();
		}
	}
	
	public void generateSHA() {
		try {
			webSHA = SHAConverter.stringToSha1( webScraperNoticia.getTextoProcessado() );
			System.out.println("SHA gerado a partir da noticia: " + webSHA );
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public void abrirHyperlink ( String urlString ) {
		try {
			BrowserLauncher launcher = new BrowserLauncher();
			launcher.openURLinBrowser( urlString );
		} catch ( BrowserLaunchingInitializingException | UnsupportedOperatingSystemException e ) {
			e.printStackTrace();
		}
	}
	
	public void clicarBbcHyperlink( ActionEvent event ) {
		abrirHyperlink("https://www.bbc.com/portuguese/");
	}
	
	public void clicarBoatosHyperlink( ActionEvent event ) {
		abrirHyperlink("https://www.boatos.org/");
	}

	public void initTelaComparaSHA() {
		try {			
			// Carregar FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource( "visao/FXMLTelaComparaSHA.fxml" ) );
			telaComparaSHA = ( BorderPane ) loader.load();
			
			// Enviar dados
			comparaSHAScene = new Scene( telaComparaSHA );
			webScraperStage.setScene( comparaSHAScene );
			TelaComparaSHAController thirdScreen = loader.getController();
			thirdScreen.inicializarAtributosTelaComparaSHA( webScraperStage, hashmapBoatos, webScraperNoticia, webSHA, minLength );
						
			// Mostrar scene			
			webScraperStage.setScene( comparaSHAScene );
			webScraperStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}

	public String getNoticiaURL() {
		return noticiaURL;
	}

	public void setNoticiaURL(String noticiaURL) {
		this.noticiaURL = noticiaURL;
	}

	public Noticia getWebScraperNoticia() {
		return webScraperNoticia;
	}

	public void setWebScraperNoticia(Noticia webScraperNoticia) {
		this.webScraperNoticia = webScraperNoticia;
	}

	public boolean isWebNoticiaLoaded() {
		return isWebNoticiaLoaded;
	}

	public void setWebNoticiaLoaded(boolean isWebNoticiaLoaded) {
		this.isWebNoticiaLoaded = isWebNoticiaLoaded;
	}

	public Hyperlink getBoatosLink() {
		return boatosLink;
	}

	public void setBoatosLink(Hyperlink boatosLink) {
		this.boatosLink = boatosLink;
	}

	public Hyperlink getBbcLink() {
		return bbcLink;
	}

	public void setBbcLink(Hyperlink bbcLink) {
		this.bbcLink = bbcLink;
	}

	public TextField getURLInput() {
		return URLInput;
	}

	public void setURLInput(TextField uRLInput) {
		URLInput = uRLInput;
	}

	public Button getBtURLInputOK() {
		return btURLInputOK;
	}

	public void setBtURLInputOK(Button btURLInputOK) {
		this.btURLInputOK = btURLInputOK;
	}

	public Label getInputAviso() {
		return inputAviso;
	}

	public void setInputAviso(Label inputAviso) {
		this.inputAviso = inputAviso;
	}	
	
}
