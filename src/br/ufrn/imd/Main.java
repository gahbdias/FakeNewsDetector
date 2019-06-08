package br.ufrn.imd;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import br.ufrn.imd.controle.TelaInicialController;
import javafx.scene.image.Image;

public class Main extends Application {
	
	private static Stage applicationStage;
	private Scene inicialScene;	
	private BorderPane telalInicial;
	
	@Override
	public void start( Stage primaryStage ) {
		applicationStage = primaryStage;
		applicationStage.setTitle("Fake News Detector");
		applicationStage.getIcons().add( new Image( "file:../../../../../img/fakeNewsLogo.png" ) );
		Main.applicationStage.setResizable(false);
		initTelaPrincipal();
	}
	
	public void initTelaPrincipal() {
		try {
			// Carregar FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource( "visao/FXMLTelaInicial.fxml" ) );
			telalInicial = (BorderPane) loader.load();
			
			// Enviar dados
			inicialScene = new Scene( telalInicial );
			applicationStage.setScene( inicialScene );
			TelaInicialController firstScreen = loader.getController();
			firstScreen.inicializarAtributosTelaInicial( applicationStage );
			
			// Mostrar scene
			applicationStage.show();
			
		} catch( Exception e ) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public Stage getApplicationStage() {
		return applicationStage;
	}

	public void setApplicationStage( Stage stageInicial ) {
		Main.applicationStage = stageInicial;
	}	
}
