package br.ufrn.imd.modelo;
import java.util.HashMap;

public class BoatosStock {
	private HashMap<String, String> boatos;
	
	BoatosStock(){
		boatos = new HashMap<String,String>();
		boatos.put( "K1", new String( "V1" ));
	}

	public HashMap<String, String> getBoatos() {
		return boatos;
	}

	public void setBoatos(HashMap<String, String> boatos) {
		this.boatos = boatos;
	}

}
