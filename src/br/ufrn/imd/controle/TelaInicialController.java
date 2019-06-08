package br.ufrn.imd.controle;

import br.ufrn.imd.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import br.ufrn.imd.modelo.CSVToHashmap;

public class TelaInicialController {
	
	private CSVToHashmap HashmapCSVGen;
	Integer minLength;
	
	@FXML private Button btIniciar;	
	@FXML private Label status;	
	@FXML private Label msgInsertMinLength;	
	@FXML private Spinner<Integer> minLengthInput;	
	@FXML private Button btMinLengthInputOK;
	@FXML private Label legendaMinLengthInput;
	
	
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
	
	public void clicarbtMinLengthInputOK( ActionEvent event ) {
		minLength = minLengthInput.getValue();
		HashmapCSVGen.criarBoatosCSVHashmap( minLength );
		
		msgInsertMinLength.setVisible(false);
		minLengthInput.setVisible(false);
		btMinLengthInputOK.setVisible(false);
		legendaMinLengthInput.setVisible(false);
		
		Main.initTelaWebScrap();
	}
	
	
	
	
	
}
