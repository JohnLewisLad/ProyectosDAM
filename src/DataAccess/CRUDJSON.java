package DataAccess;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Operaciones CRUD sobre el fichero JSON de películas usando Jackson.
 */
public class CRUDJSON {

	private File file;				// fichero JSON
	private ObjectMapper mapper;	// objeto ObjectMapper de Jackson
	private ArrayNode peliculas;	// array "peliculas" del documento

	public CRUDJSON(String jsonFileName) throws IOException {
		// Cargar el fichero JSON y obtener el array "peliculas"
		file = new File(jsonFileName);
		mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(file);
		peliculas = (ArrayNode) root.get("peliculas");
	}

	// Devuelve todas las películas del fichero como objetos Pelicula
	public List<Pelicula> listar() {
		/* 
		 * Se usa TypeReference<List<Map<String, Object>>> porque Jackson no puede
		 * construir directamente un Pelicula: no tiene constructor sin argumentos
		 * ni setters para el id (es final), y no usamos anotaciones. Por eso cada
		 * elemento del array se convierte primero en un Map genérico (clave -> valor)
		 * y después se traduce a Pelicula manualmente con el constructor.
		 * 
		 * ES DECIR: listar() convierte primero a List<Map<String, Object>> (tipos genéricos; número, texto...)
		 * y luego construye cada Pelicula con su constructor. 
		 */
		TypeReference<List<Map<String, Object>>> ref = new TypeReference<List<Map<String, Object>>>() {};
		List<Map<String, Object>> datos = mapper.convertValue(peliculas, ref);

		List<Pelicula> lista = new ArrayList<>();
		for (Map<String, Object> dato : datos) {
			lista.add(new Pelicula(
					((Number) dato.get("id")).intValue(),
					(String) dato.get("titulo"),
					((Number) dato.get("año")).intValue(),
					(String) dato.get("genero")));
		}
		return lista;
	}

	// Calcula el id de la nueva película: el mayor id registrado + 1
	public int siguienteId() {
		int maximo = 0;
		for (JsonNode nodo : peliculas) {
			int id = nodo.get("id").asInt();
			if (id > maximo) {
				maximo = id;
			}
		}
		return maximo + 1;
	}

	// CREAR: calcula el id, añade la nueva película al array y la devuelve
	// (aún no se guarda en disco)
	public Pelicula crear(String titulo, int año, String genero) {
		Pelicula pelicula = new Pelicula(siguienteId(), titulo, año, genero);

		ObjectNode nodo = mapper.createObjectNode();
		nodo.put("id", pelicula.getId());
		nodo.put("titulo", pelicula.getTitulo());
		nodo.put("año", pelicula.getAño());
		nodo.put("genero", pelicula.getGenero());
		peliculas.add(nodo);

		return pelicula;
	}

	// ACTUALIZAR: modifica titulo, año y genero de la película cuyo id coincide.
	// El id no se modifica nunca: solo se usa para localizar la película.
	public boolean actualizar(Pelicula pelicula) {
		for (JsonNode nodo : peliculas) {
			if (nodo.get("id").asInt() == pelicula.getId()) {
				ObjectNode objeto = (ObjectNode) nodo;
				objeto.put("titulo", pelicula.getTitulo());
				objeto.put("año", pelicula.getAño());
				objeto.put("genero", pelicula.getGenero());
				return true;
			}
		}
		return false;
	}

	// ELIMINAR: borra del array la película cuyo id coincide con el indicado
	public boolean eliminar(int id) {
		for (int i = 0; i < peliculas.size(); i++) {
			if (peliculas.get(i).get("id").asInt() == id) {
				peliculas.remove(i);
				return true;
			}
		}
		return false;
	}

	// Guarda el documento modificado en el fichero JSON
	public void guardar() throws IOException {
		ObjectNode root = mapper.createObjectNode();
		root.set("peliculas", peliculas);
		mapper.writerWithDefaultPrettyPrinter().writeValue(file, root);
	}
}
