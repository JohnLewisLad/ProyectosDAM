package DataAccess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Operaciones CRUD sobre el fichero de texto CSV de películas.
 * Usa únicamente java.io y java.nio (sin librerías externas).
 */
public class CRUDTXT {

	private static final String CABECERA = "id,titulo,año,genero";

	private File file;					// fichero de texto
	private List<Pelicula> peliculas;	// películas cargadas en memoria

	public CRUDTXT(String txtFileName) throws IOException {
		file = new File(txtFileName);
		peliculas = new ArrayList<>();
		cargar();
	}

	// Carga el CSV en la lista de películas, ignorando la cabecera
	private void cargar() throws IOException {
		peliculas.clear();

		try (BufferedReader lector = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {

			String linea;
			while ((linea = lector.readLine()) != null) {
				if (linea.isBlank()) {
					continue;
				}

				// Cada línea es: id,titulo,año,genero
				String[] partes = linea.split(",", 4);
				if (partes.length < 4) {
					continue;
				}

				try {
					int id = Integer.parseInt(partes[0].trim());
					String titulo = partes[1].trim();
					int año = Integer.parseInt(partes[2].trim());
					String genero = partes[3].trim();
					peliculas.add(new Pelicula(id, titulo, año, genero));
				} catch (NumberFormatException e) {
					// La cabecera no empieza por un número: se ignora
				}
			}
		}
	}

	// Devuelve todas las películas del fichero como objetos Pelicula
	public List<Pelicula> listar() {
		return new ArrayList<>(peliculas);
	}

	// Calcula el id de la nueva película: el mayor id registrado + 1
	public int siguienteId() {
		int maximo = -1;
		for (Pelicula pelicula : peliculas) {
			if (pelicula.getId() > maximo) {
				maximo = pelicula.getId();
			}
		}
		return maximo + 1;
	}

	// CREAR: calcula el id, añade la nueva película a la lista y la devuelve
	// (aún no se guarda en disco)
	public Pelicula crear(String titulo, int año, String genero) {
		Pelicula pelicula = new Pelicula(siguienteId(), titulo, año, genero);
		peliculas.add(pelicula);
		return pelicula;
	}

	// ACTUALIZAR: modifica titulo, año y genero de la película cuyo id coincide.
	// El id no se modifica nunca: solo se usa para localizar la película.
	public boolean actualizar(Pelicula pelicula) {
		for (Pelicula actual : peliculas) {
			if (actual.getId() == pelicula.getId()) {
				actual.setTitulo(pelicula.getTitulo());
				actual.setAño(pelicula.getAño());
				actual.setGenero(pelicula.getGenero());
				return true;
			}
		}
		return false;
	}

	// ELIMINAR: borra de la lista la película cuyo id coincide con el indicado
	public boolean eliminar(int id) {
		Iterator<Pelicula> iterador = peliculas.iterator();
		while (iterador.hasNext()) {
			if (iterador.next().getId() == id) {
				iterador.remove();
				return true;
			}
		}
		return false;
	}

	// Guarda la lista en el fichero de texto en formato CSV
	public void guardar() throws IOException {
		try (BufferedWriter escritor = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {

			escritor.write(CABECERA);
			escritor.newLine();

			for (Pelicula pelicula : peliculas) {
				escritor.write(pelicula.getId() + ","
						+ pelicula.getTitulo() + ","
						+ pelicula.getAño() + ","
						+ pelicula.getGenero());
				escritor.newLine();
			}
		}
	}
}
