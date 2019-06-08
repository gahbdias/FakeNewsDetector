package br.ufrn.imd.modelo;
import java.util.HashMap;
import br.ufrn.imd.modelo.Noticia;

public class DataStock {
	private HashMap<String, Noticia> boatosCSV;
	private Noticia webNoticia;
	
	public HashMap<String, Noticia> getBoatosCSV() {
		return boatosCSV;
	}
	public void setBoatosCSV(HashMap<String, Noticia> boatosCSV) {
		this.boatosCSV = boatosCSV;
	}
	public Noticia getWebNoticia() {
		return webNoticia;
	}
	public void setWebNoticia(Noticia webNoticia) {
		this.webNoticia = webNoticia;
	}	
}
