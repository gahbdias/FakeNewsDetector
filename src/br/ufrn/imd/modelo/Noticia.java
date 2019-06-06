package br.ufrn.imd.modelo;

public class Noticia {
	private String textoProcessado;
	private int id;
	private String conteudo;
	private String link;
	private String timestamp;

	public String getTextoProcessado() {
		return textoProcessado;
	}
	public void setTextoProcessado(String textoProcessado) {
		this.textoProcessado = textoProcessado;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getConteudo() {
		return conteudo;
	}
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
