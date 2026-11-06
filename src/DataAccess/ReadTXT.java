package DataAccess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Lector del fichero de texto CSV de películas usando java.io y java.nio.
 */
public class ReadTXT {

	private File file;	// fichero de texto

	public ReadTXT(String txtFileName) {
		file = new File(txtFileName);
	}

	// Abre el fichero y muestra su contenido por consola
	public void mostrar() throws IOException {
		try (BufferedReader lector = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {

			String linea;
			while ((linea = lector.readLine()) != null) {
				System.out.println(linea);
			}
		}
	}

	public File getFile() {
		return file;
	}
}
