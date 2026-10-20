package DataAccess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.JDOMException;

import com.fasterxml.jackson.databind.JsonNode;

public class Main {

	public static void main(String[] args) {

        //System.out.println("Aplicación iniciada.");
        
		// Selección de fichero de texto tipo .xml 
        String nombre_fichero_xml = "peliculas.xml";
        
		// definimos una lista vacia que contendra las peliculas como instancias
		List<Pelicula> lista_pelicula_object = new ArrayList<>();         
        
        try {
        	
        	// Nuevo objeto de clase "Lector XML"
			ReadXML Lectura = new ReadXML(nombre_fichero_xml);		
						
			// Recorrer los elementos hijos e imprimirlos por pantalla
			for (Element pelicula : Lectura.getRoot().getChildren("pelicula")) {
				// leer datos de pelicula
				String id = pelicula.getChildText("id");
				String titulo = pelicula.getChildText("titulo");
				String año = pelicula.getChildText("año");
				String genero = pelicula.getChildText("genero");
				
				// imprimir por pantalla
				System.out.printf("Id: %s, Nombre: %s, Año: %s, Genero: %s %n", id, titulo, año, genero);
	        	    
	        	// parsear los datos a una instancia nueva de Pelicula 
				// convertir los datos de la etiqueta pelicula a objeto de la clase pelicula 
				Pelicula pelicula_object = new Pelicula(Integer.parseInt(id), titulo, Integer.parseInt(año), genero);
	        	
				// Añadir la instancia de pelicula a la lista
				lista_pelicula_object.add(pelicula_object);
				
			}		
			
            System.out.println(
            		"Número de películas leidas y guardadas en lista: "
            				+ lista_pelicula_object.size()
            );
						
            System.out.println(
            		"Número de películas desde la etiqueta raiz: "
            				+ Lectura.getRoot().getChildren().size()
            );  

			
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Selección de fichero de texto tipo .json
		String nombre_fichero_json = "peliculas.json";

		try {

			// Nuevo objeto de clase "Lector JSON"
			ReadJSON LecturaJSON = new ReadJSON(nombre_fichero_json);

			// Recorrer el array "peliculas" del nodo raíz e imprimirlos por pantalla
			for (JsonNode pelicula : LecturaJSON.getRoot().get("peliculas")) {
				String id = pelicula.get("id").asText();
				String titulo = pelicula.get("titulo").asText();
				String año = pelicula.get("año").asText();
				String genero = pelicula.get("genero").asText();				
				
				// imprimir por pantalla
				System.out.printf("Id: %s, Nombre: %s, Año: %s, Genero: %s %n", id, titulo, año, genero);
				
	        	// parsear los datos a una instancia nueva de Pelicula 
				// convertir los datos del nodo pelicula a objeto de la clase pelicula
				Pelicula pelicula_object = new Pelicula(Integer.parseInt(id), titulo, Integer.parseInt(año), genero);
	        	
				// Añadir la instancia de pelicula a la lista
				lista_pelicula_object.add(pelicula_object);				
				
			}

			System.out.println(
					"Número de películas desde el JSON: "
							+ LecturaJSON.getRoot().get("peliculas").size()
			);
			
            System.out.println(
            		"Número de películas leidas y guardadas en lista: "
            				+ lista_pelicula_object.size()
            );			
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
