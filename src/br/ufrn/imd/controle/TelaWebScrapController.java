package br.ufrn.imd.controle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Hyperlink;
import javafx.event.ActionEvent;
import br.ufrn.imd.modelo.WebScraper;
import edu.stanford.ejalbert.BrowserLauncher;
import edu.stanford.ejalbert.exception.BrowserLaunchingInitializingException;
import edu.stanford.ejalbert.exception.UnsupportedOperatingSystemException;
import br.ufrn.imd.Main;
import br.ufrn.imd.modelo.Noticia;
import br.ufrn.imd.modelo.WebScraper;
import br.ufrn.imd.Main;

public class TelaWebScrapController {
	
	private String noticiaURL;
	private Noticia webNoticia;
	boolean isWebNoticiaLoaded;
	
	@FXML private Hyperlink boatosLink;
	@FXML private Hyperlink bbcLink;
	@FXML private TextField URLInput;	
	@FXML private Button btURLInputOK;
	@FXML private Label inputAviso;
	
	public void clicarbtURLInputOK( ActionEvent event ) {
		
		webNoticia = new Noticia();
		isWebNoticiaLoaded = false;
		noticiaURL = URLInput.getText();
			
		if( noticiaURL.contains("boatos") ) {
			webNoticia = WebScraper.scrapBoatos( noticiaURL );
			isWebNoticiaLoaded = true;
			inputAviso.setVisible(false);
		}
		else if ( noticiaURL.contains("bbc") ) {
			webNoticia = WebScraper.scrapBBC( noticiaURL );
			isWebNoticiaLoaded = true;
			inputAviso.setVisible(false);
		}
		else {
			inputAviso.setVisible(true);
		}
		
		if ( isWebNoticiaLoaded ) {
			Main.initTelaSimilaridade();
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
}
