package DataAccess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.JDOMException;

import com.fasterxml.jackson.databind.JsonNode;

public class Main {

	public static void main(String[] args) {

		//System.out.println("Hello World");

        System.out.println("Aplicación iniciada.");
 
		// ==================== Lectura basica sobre el fichero XML ====================        
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

			// ==================== CRUD sobre el fichero XML ====================
			System.out.println("\n--- CRUD XML ---");

			// Objeto CRUD que carga el mismo fichero XML para modificarlo
			CRUDXML crudXML = new CRUDXML(nombre_fichero_xml);

			// CREAR: el id se calcula automáticamente (mayor id registrado + 1).
			// Añadimos la nueva película a la lista y guardamos el fichero.
			Pelicula nuevaXML = crudXML.crear("Dune", 2021, "Ciencia ficción");
			crudXML.guardar();
			System.out.println("XML - Película creada con id " + nuevaXML.getId() + ": " + nuevaXML.getTitulo());

			// ACTUALIZAR: modificamos titulo y año de la película recién creada
			// (el id no se modifica) y guardamos el fichero.
			nuevaXML.setTitulo("Dune: Parte Dos");
			nuevaXML.setAño(2024);
			if (crudXML.actualizar(nuevaXML)) {
				crudXML.guardar();
				System.out.println("XML - Película actualizada: " + nuevaXML.getTitulo());
			}

			// ELIMINAR: borramos la película por su id y guardamos el fichero
			/* */
			if (crudXML.eliminar(nuevaXML.getId())) {
				crudXML.guardar();
				System.out.println("XML - Película eliminada con id: " + nuevaXML.getId());
			}
			/* */

		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ==================== Lectura basica sobre el fichero JSON ====================         
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
			
			// ==================== CRUD sobre el fichero JSON ====================
			System.out.println("\n--- CRUD JSON ---");

			// Objeto CRUD que carga el mismo fichero JSON para modificarlo
			CRUDJSON crudJSON = new CRUDJSON(nombre_fichero_json);

			// CREAR: el id se calcula automáticamente (mayor id registrado + 1).
			// Añadimos la nueva película al array y guardamos el fichero.
			Pelicula nuevaJSON = crudJSON.crear("Dune", 2021, "Ciencia ficción");
			crudJSON.guardar();
			System.out.println("JSON - Película creada con id " + nuevaJSON.getId() + ": " + nuevaJSON.getTitulo());

			// ACTUALIZAR: modificamos titulo y año de la película recién creada
			// (el id no se modifica) y guardamos el fichero.
			nuevaJSON.setTitulo("Dune: Parte Dos");
			nuevaJSON.setAño(2024);
			if (crudJSON.actualizar(nuevaJSON)) {
				crudJSON.guardar();
				System.out.println("JSON - Película actualizada: " + nuevaJSON.getTitulo());
			}

			// ELIMINAR: borramos la película por su id y guardamos el fichero
			/* */
			if (crudJSON.eliminar(nuevaJSON.getId())) {
				crudJSON.guardar();
				System.out.println("JSON - Película eliminada con id: " + nuevaJSON.getId());
			}
			/* */

		} catch (IOException e) {
			e.printStackTrace();
		}

		// Selección de fichero de texto tipo .txt (formato CSV)
		String nombre_fichero_txt = "peliculas.txt";

		try {

			// Nuevo objeto de clase "Lector TXT": muestra el contenido por consola
			ReadTXT LecturaTXT = new ReadTXT(nombre_fichero_txt);
			LecturaTXT.mostrar();

			// ==================== CRUD sobre el fichero TXT ====================
			System.out.println("\n--- CRUD TXT ---");

			// Objeto CRUD que carga el mismo fichero TXT para modificarlo
			CRUDTXT crudTXT = new CRUDTXT(nombre_fichero_txt);

			// CREAR: el id se calcula automáticamente (mayor id registrado + 1).
			// Añadimos la nueva película a la lista y guardamos el fichero.
			Pelicula nuevaTXT = crudTXT.crear("Dune", 2021, "Ciencia ficción");
			crudTXT.guardar();
			System.out.println("TXT - Película creada con id " + nuevaTXT.getId() + ": " + nuevaTXT.getTitulo());

			// ACTUALIZAR: modificamos titulo y año de la película recién creada
			// (el id no se modifica) y guardamos el fichero.
			nuevaTXT.setTitulo("Dune: Parte Dos");
			nuevaTXT.setAño(2024);
			if (crudTXT.actualizar(nuevaTXT)) {
				crudTXT.guardar();
				System.out.println("TXT - Película actualizada: " + nuevaTXT.getTitulo());
			}

			// ELIMINAR: borramos la película por su id y guardamos el fichero
			if (crudTXT.eliminar(nuevaTXT.getId())) {
				crudTXT.guardar();
				System.out.println("TXT - Película eliminada con id: " + nuevaTXT.getId());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
