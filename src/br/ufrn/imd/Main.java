package br.ufrn.imd;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	
	private static Stage applicationStage;
	
	private Scene inicialScene;
	private static Scene webScrapScene;
	private static Scene similaridadeScene;
	
	private BorderPane telalInicial;
	private static BorderPane telaWebScrap;
	private static BorderPane telaSimilaridade;
	
	@Override
	public void start( Stage primaryStage ) {		
		applicationStage = primaryStage;
		applicationStage.setTitle("Fake News Detector");
		Main.applicationStage.setResizable(false);
		initTelaPrincipal();
	}
	
	public void initTelaPrincipal() {
		try {
			// Carregar FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource( "visao/FXMLTelaInicial.fxml" ) );
			telalInicial = (BorderPane) loader.load();
			
			// Mostrar scene
			inicialScene = new Scene(telalInicial);
			applicationStage.setScene( inicialScene );
			applicationStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void initTelaWebScrap() {
		try {
			// Carregar FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource( "visao/FXMLTelaWebScrap.fxml" ) );
			telaWebScrap = (BorderPane) loader.load();
			
			// Mostrar scene
			webScrapScene = new Scene( telaWebScrap );
			applicationStage.setScene( webScrapScene );
			applicationStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void initTelaSimilaridade() {
		try {
			// Carregar FXML
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation( Main.class.getResource( "visao/FXMLTelaSimilaridade.fxml" ) );
			telaSimilaridade = (BorderPane) loader.load();
			
			// Mostrar scene
			similaridadeScene = new Scene( telaSimilaridade );
			applicationStage.setScene( similaridadeScene );
			applicationStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public Stage getStageInicial() {
		return applicationStage;
	}

	public void setStageInicial( Stage stageInicial ) {
		Main.applicationStage = stageInicial;
	}	
}
