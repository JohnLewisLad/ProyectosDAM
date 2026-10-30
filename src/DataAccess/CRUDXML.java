package DataAccess;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * Operaciones CRUD sobre el fichero XML de películas usando JDOM2.
 */
public class CRUDXML {

	private File file;			// fichero XML
	private Document document;	// documento cargado en memoria
	private Element root;		// etiqueta raíz <peliculas>

	public CRUDXML(String xmlFileName) throws JDOMException, IOException {
		// Cargar el fichero XML con JDOM2
		file = new File(xmlFileName);
		document = new SAXBuilder().build(file);
		root = document.getRootElement();
	}

	// Devuelve todas las películas del fichero como objetos Pelicula
	public List<Pelicula> listar() {
		List<Pelicula> peliculas = new ArrayList<>();
		for (Element pelicula : root.getChildren("pelicula")) {
			peliculas.add(new Pelicula(
					Integer.parseInt(pelicula.getChildText("id")),
					pelicula.getChildText("titulo"),
					Integer.parseInt(pelicula.getChildText("año")),
					pelicula.getChildText("genero")));
		}
		return peliculas;
	}

	// Calcula el id de la nueva película: el mayor id registrado + 1
	public int siguienteId() {
		int maximo = 0;
		for (Element elemento : root.getChildren("pelicula")) {
			int id = Integer.parseInt(elemento.getChildText("id"));
			if (id > maximo) {
				maximo = id;
			}
		}
		return maximo + 1;
	}

	// CREAR: calcula el id, añade la nueva película al documento y la devuelve
	// (aún no se guarda en disco)
	public Pelicula crear(String titulo, int año, String genero) {
		Pelicula pelicula = new Pelicula(siguienteId(), titulo, año, genero);

		Element nueva = new Element("pelicula");
		nueva.addContent(new Element("id").setText(String.valueOf(pelicula.getId())));
		nueva.addContent(new Element("titulo").setText(pelicula.getTitulo()));
		nueva.addContent(new Element("año").setText(String.valueOf(pelicula.getAño())));
		nueva.addContent(new Element("genero").setText(pelicula.getGenero()));
		root.addContent(nueva);

		return pelicula;
	}

	// ACTUALIZAR: modifica titulo, año y genero de la película cuyo id coincide.
	// El id no se modifica nunca: solo se usa para localizar la película.
	public boolean actualizar(Pelicula pelicula) {
		for (Element elemento : root.getChildren("pelicula")) {
			if (Integer.parseInt(elemento.getChildText("id")) == pelicula.getId()) {
				elemento.getChild("titulo").setText(pelicula.getTitulo());
				elemento.getChild("año").setText(String.valueOf(pelicula.getAño()));
				elemento.getChild("genero").setText(pelicula.getGenero());
				return true;
			}
		}
		return false;
	}

	// ELIMINAR: borra del documento la película cuyo id coincide con el indicado
	public boolean eliminar(int id) {
		for (Element elemento : root.getChildren("pelicula")) {
			if (Integer.parseInt(elemento.getChildText("id")) == id) {
				root.removeContent(elemento);
				return true;
			}
		}
		return false;
	}

	// Guarda el documento modificado en el fichero XML
	public void guardar() throws IOException {
		XMLOutputter salida = new XMLOutputter(Format.getPrettyFormat().setEncoding("UTF-8"));
		try (FileOutputStream flujo = new FileOutputStream(file)) {
			salida.output(document, flujo);
		}
	}
}
