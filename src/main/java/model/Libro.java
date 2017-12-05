package model;

public class Libro {
	int id;
	String titulo;
	String categoria;
	String autor;
	String url;
	
	
	public  Libro(int id,String n, String t,String a, String d) {
		this.id = id;
		this.titulo = n;
		this.categoria = t;
		this.autor= a;
		this.url = d;	
	}
	

	public int getId() {
		return id;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getCategoria() {
		return categoria;
	}


	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}
}	