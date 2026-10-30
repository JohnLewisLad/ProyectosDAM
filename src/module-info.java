/**
 * 
 */
/**
 * En un proyecto de Java en Eclipse (sin maven, sin gradle) pero con un module-info.java
 * Añadimos los paquetes externos en este fichero
 */
module DataAccessDAM {
	requires org.jdom2;
	requires com.fasterxml.jackson.core;
	requires com.fasterxml.jackson.databind;

	// Jackson necesita acceso por reflexión a los campos de Pelicula
	opens DataAccess to com.fasterxml.jackson.databind;
}
