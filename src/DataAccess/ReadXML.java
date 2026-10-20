package DataAccess;

import java.io.File;
import java.io.IOException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ReadXML {
	
    private Document document;	// el documento
    private Element root;	// etiqueta raiz del documento

	public ReadXML(String XML_file_name) throws JDOMException, IOException {
		
		// Cargar el fichero XML
		File file = new File(XML_file_name);
		SAXBuilder saxBuilder = new SAXBuilder();
		document = saxBuilder.build(file);

		// Obtener el elemento raíz
		root = document.getRootElement();	
	}
		
	// Devuelve la etiqueta raiz
    public Element getRoot() {
    	//System.out.println("Elemento raíz: " + root.getName());
        return root;        
    }			

	public Document getDocument() {
		return document;
	}

}