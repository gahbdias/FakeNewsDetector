package br.ufrn.imd.controle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TelaFinalController {
	
	private double cosineResult;
	private double levenshteinResult;
	private double threshold;
	
	@FXML private Label status;	
	@FXML private Label nonFake;	
	@FXML private Label isFake;	
	@FXML private Label thresholdLabel;
	@FXML private Label levenshteinValueLabel;
	@FXML private Label cosineValueLabel;	
	
	public void inicializarAtributosTelaFinal( double cR, double lR, double thr ) {
		cosineResult = cR;
		levenshteinResult = lR;
		threshold = thr;
		
		defineLabels();
	}
	
	public void defineLabels() {
		double levenshteinShow = levenshteinResult * 100;		
		levenshteinValueLabel.setText( String.format("%.1f", levenshteinShow) + "%"  );
		
		double cosineShow = cosineResult * 100;	
		System.out.println("##### COSINE SHOW: " + cosineShow );
		cosineValueLabel.setText( String.format("%.1f", cosineShow ) + "%"  );
		
		thresholdLabel.setText("Threshold: " + threshold );
		
		if ( cosineShow > threshold || levenshteinShow > threshold ) {
			isFake.setVisible(true);			
		}
		else {
			nonFake.setVisible(true);
		}
	}	
}
