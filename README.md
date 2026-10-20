# DataAccessDAM

Proyecto de Java del módulo **Acceso a Datos** (ciclo DAM). Demuestra cómo leer datos de ficheros XML y JSON y convertirlos en objetos Java.

## Descripción

El programa lee `peliculas.xml` con la librería **JDOM2** y `peliculas.json` con **Jackson** (`ReadJSON`), parsea cada película y construye la lista correspondiente (los datos se imprimen por pantalla).

## Estructura

```
src/
├── module-info.java          # Módulo DataAccessDAM
└── DataAccess/
    ├── Main.java             # Punto de entrada
    ├── ReadXML.java          # Lector XML (JDOM2)
    ├── ReadJSON.java         # Lector JSON (Jackson)
    └── Pelicula.java         # Clase de dominio (POJO)
lib/                          # JARs: jdom2, jackson-core, jackson-databind, jackson-annotations
peliculas.xml                 # Datos de ejemplo XML (10 películas)
peliculas.json                # Datos de ejemplo JSON (10 películas)
```

## Requisitos

- JDK 25
- Eclipse (el proyecto no usa Maven ni Gradle; las dependencias están en `lib/`)

## Compilación y ejecución

Importa el proyecto en Eclipse (*File > Import > Existing Projects*) y ejecuta `DataAccess.Main`.

Por línea de comandos, desde la raíz del proyecto:

```bash
javac -d bin --module-path lib src/module-info.java src/DataAccess/*.java
java --module-path bin:lib -m DataAccessDAM/DataAccess.Main
```

> El programa busca `peliculas.xml` y `peliculas.json` en el directorio de trabajo actual: ejecútalo desde la raíz del proyecto.

## Licencia

MIT — ver [LICENSE](LICENSE).
