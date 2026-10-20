package DataAccess;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadJSON {
	
	private JsonNode root;			// Nodo raíz del documento JSON
	private ObjectMapper mapper;	// Objeto ObjectMapper de Jackson

	public ReadJSON(String jsonFileName) throws IOException {
		
		// Crear el objeto ObjectMapper
		mapper = new ObjectMapper();
		
		// Cargar el fichero JSON y obtener el nodo raíz
		File file = new File(jsonFileName);
		root = mapper.readTree(file);
	}
	
	// Devuelve el nodo raíz del JSON
	public JsonNode getRoot() {
		return root;
	}
	
	// Devuelve el ObjectMapper
	public ObjectMapper getMapper() {
		return mapper;
	}
}