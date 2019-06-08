package br.ufrn.imd.modelo;

import br.ufrn.imd.modelo.Noticia;

public class WebProcessor {
	
	private Noticia noticiaProcessada;
	
	public WebProcessor( Noticia novaWebNoticia ) {
		noticiaProcessada = novaWebNoticia;
	}

	public Noticia processarTextos( Integer minLength ) {
		noticiaProcessada.setTextoProcessado( StringProcessor.processString( noticiaProcessada.getConteudo(), minLength ) );
		return noticiaProcessada;
	}

	public Noticia getNoticiaProcessada() {
		return noticiaProcessada;
	}

	public void setNoticiaProcessada(Noticia webNoticia) {
		this.noticiaProcessada = webNoticia;
	}
}
