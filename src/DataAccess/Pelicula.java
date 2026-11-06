package DataAccess;

import java.io.Serializable;

public class Pelicula implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	final int id;
	String titulo;
	int año;
	String genero;

	public Pelicula(int this_id, String this_titulo, int this_año, String this_genero) {		
	    id = this_id;
	    titulo = this_titulo;
	    año = this_año;
	    genero = this_genero;	    
		
	}

	public int getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getAño() {
		return año;
	}

	public void setAño(int año) {
		this.año = año;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
}
