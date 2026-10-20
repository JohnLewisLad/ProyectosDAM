# DataAccessDAM

Proyecto de Java del módulo **Acceso a Datos** (ciclo DAM). Demuestra cómo leer datos de un fichero XML y convertirlos en objetos Java.

## Descripción

El programa lee `peliculas.xml` con la librería **JDOM2**, parsea cada elemento `<pelicula>` y construye una lista de objetos `Pelicula`. También incluye un lector JSON con **Jackson** (`ReadJSON`), pendiente de completar.

## Estructura

```
src/
├── module-info.java          # Módulo DataAccessDAM
└── DataAccess/
    ├── Main.java             # Punto de entrada
    ├── ReadXML.java          # Lector XML (JDOM2)
    ├── ReadJSON.java         # Lector JSON (Jackson)
    └── Pelicula.java         # Clave de dominio (POJO)
lib/                          # JARs: jdom2, jackson-core
peliculas.xml                 # Datos de ejemplo (10 películas)
```

## Requisitos

- JDK 25
- Eclipse (el proyecto no usa Maven ni Gradle; las dependencias están en `lib/`)

## Compilación y ejecución

Importa el proyecto en Eclipse (*File > Import > Existing Projects*) y ejecuta `DataAccess.Main`.

Por línea de comandos, desde la raíz del proyecto:

```bash
javac -d bin --module-path lib -m DataAccessDAM src/module-info.java src/DataAccess/*.java
java --module-path lib -m DataAccessDAM/DataAccess.Main
```

> El programa busca `peliculas.xml` en el directorio de trabajo actual: ejecútalo desde la raíz del proyecto.

## Licencia

MIT — ver [LICENSE](LICENSE).
