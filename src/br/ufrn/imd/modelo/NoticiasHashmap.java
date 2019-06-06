package br.ufrn.imd.modelo;
import java.util.HashMap;
import br.ufrn.imd.modelo.Noticia;

public class NoticiasHashmap {
	public HashMap<String, Noticia> mapaNoticias;
	
	public NoticiasHashmap() {
		mapaNoticias = new HashMap<String, Noticia>();
	}

	public HashMap<String, Noticia> getMapaNoticias() {
		return mapaNoticias;
	}

	public void setMapaNoticias(HashMap<String, Noticia> mapaNoticias) {
		this.mapaNoticias = mapaNoticias;
	}
}
