# DataAccessDAM

Proyecto de Java del módulo **Acceso a Datos** (ciclo DAM). Demuestra cómo leer datos de ficheros XML y JSON y convertirlos en objetos Java.

## Descripción

El programa lee `peliculas.xml` con la librería **JDOM2** y `peliculas.json` con **Jackson** (`ReadJSON`), parsea cada película y construye la lista correspondiente (los datos se imprimen por pantalla). Además incluye operaciones **CRUD** sobre ambos ficheros mediante `CRUDXML` y `CRUDJSON`.

## Estructura

```
src/
├── module-info.java          # Módulo DataAccessDAM
└── DataAccess/
    ├── Main.java             # Punto de entrada
    ├── ReadXML.java          # Lector XML (JDOM2)
    ├── ReadJSON.java         # Lector JSON (Jackson)
    ├── CRUDXML.java          # CRUD sobre el XML (JDOM2)
    ├── CRUDJSON.java         # CRUD sobre el JSON (Jackson)
    └── Pelicula.java         # Clase de dominio (POJO)
lib/                          # JARs: jdom2, jackson-core, jackson-databind, jackson-annotations
peliculas.xml                 # Datos de ejemplo XML (10 películas)
peliculas.json                # Datos de ejemplo JSON (10 películas)
```

## Operaciones CRUD

`CRUDXML` y `CRUDJSON` ofrecen las mismas operaciones sobre sus respectivos ficheros:

- **Crear** (`crear`): el **id se calcula automáticamente** como el mayor id registrado + 1 (o 1 si la lista está vacía). No se indica a mano.
- **Actualizar** (`actualizar`): localiza la película por su id y modifica `titulo`, `año` y `genero`. El **id nunca se modifica** (es `final` en `Pelicula`).
- **Eliminar** (`eliminar`): borra la película cuyo id coincide con el indicado.
- **Listar** (`listar`): devuelve todas las películas como `List<Pelicula>`.
- **Guardar** (`guardar`): escribe los cambios en el fichero.

> Los cambios se hacen en memoria y solo se escriben en disco al llamar a `guardar()`.

## Requisitos

- JDK 25
- Eclipse (el proyecto no usa Maven ni Gradle; las dependencias están en `lib/`)

## Compilación y ejecución

Importa el proyecto en Eclipse (*File > Import > Existing Projects*) y ejecuta `DataAccess.Main`.

Por línea de comandos, desde la raíz del proyecto:

```bash
javac -encoding UTF-8 -d bin --module-path lib src/module-info.java src/DataAccess/*.java
java --module-path bin:lib -m DataAccessDAM/DataAccess.Main
```

> El programa busca `peliculas.xml` y `peliculas.json` en el directorio de trabajo actual: ejecútalo desde la raíz del proyecto.

## Licencia

MIT — ver [LICENSE](LICENSE).
