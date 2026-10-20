package DataAccess;

public class Pelicula {
	
	int id;
	String titulo;
	int anio;
	String genero;

	public Pelicula(int this_id, String this_titulo, int this_anio, String this_genero) {		
	    id = this_id;
	    titulo = this_titulo;
	    anio = this_anio;
	    genero = this_genero;	    
		
	}

	public int getId() {
		return id;
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

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
}
