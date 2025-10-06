# InventarioPlus

Proyecto Java Spring Boot creado con Spring Initializr. Aplicación demo para gestionar un inventario simple usando Spring Boot, Thymeleaf y dependencias estándar.

## Qué contiene este repositorio

- Artefacto Maven: `InventarioPlus` (versión 0.0.1-SNAPSHOT)
- Java: 17 (configurado en `pom.xml`)
- Dependencias principales: `spring-boot-starter-web`, `spring-boot-starter-thymeleaf`, `spring-boot-devtools`, `lombok` (opcional)
- Código fuente en `src/main/java/com/example/InventarioPlus`
- Recursos (plantillas y estáticos) en `src/main/resources`

## Requisitos (prerrequisitos)

- Java 17 (JDK). Verifica con:

```powershell
java -version
```

- Maven (3.6+ recomendado). Verifica con:

```powershell
mvn -v
```

- (Opcional) IDE con soporte para Spring Boot y Lombok (ej. IntelliJ IDEA o Eclipse). Si usas Lombok, instala el plugin de Lombok en tu IDE y habilita el procesador de anotaciones.

## Estructura importante

- `src/main/java/com/example/InventarioPlus` — paquete principal y clases de la aplicación
- `src/main/java/com/example/InventarioPlus/controller` — controladores web
- `src/main/java/com/example/InventarioPlus/modelo` — modelos/dominios
- `src/main/java/com/example/InventarioPlus/repository` — repositorios
- `src/main/resources/templates` — plantillas Thymeleaf
- `src/main/resources/static` — archivos estáticos (CSS, JS, imágenes)
- `src/main/resources/application.properties` — configuración de la aplicación

## Comandos comunes (PowerShell)

- Ejecutar la aplicación en modo desarrollo (usa `spring-boot-devtools` para reinicio automático):

```powershell
mvn spring-boot:run
```

- Compilar y empaquetar en un JAR ejecutable:

```powershell
mvn clean package
```

- Ejecutar el JAR generado:

```powershell
java -jar target\InventarioPlus-0.0.1-SNAPSHOT.jar
```

- Ejecutar pruebas unitarias:

```powershell
mvn test
```

Nota: los comandos anteriores se deben ejecutar en la raíz del proyecto (donde está el `pom.xml`).

## Propiedades y configuración

La configuración principal está en `src/main/resources/application.properties`. Por defecto la aplicación se sirve en el puerto 8080 a menos que se cambie con la propiedad `server.port`.

Ejemplo para cambiar el puerto (añadir en `application.properties`):

```
server.port=8081
```

## Dependencias destacadas

- `spring-boot-starter-web` — soporte para aplicaciones web (REST, MVC)
- `spring-boot-starter-thymeleaf` — motor de plantillas Thymeleaf para vistas del lado servidor
- `spring-boot-devtools` — herramientas de desarrollo (reinicio automático). Esta dependencia está marcada como runtime/optional en el `pom.xml`.
- `lombok` — reduce código boilerplate (getters/setters). Requiere plugin en el IDE para evitar errores en tiempo de desarrollo.

## Desarrollo y buenas prácticas

- Si usas Lombok, instala y habilita el plugin en tu IDE y asegúrate de que el procesador de anotaciones esté activo.
- Usa `spring-boot-devtools` para acelerar el desarrollo (hot reload). Ten en cuenta que en producción no debe usarse.
- Añade control de versiones (git) si aún no lo tienes. Incluye `.gitignore` para JDK, target y archivos del IDE.

## Ejecutar desde el IDE

- Importa el proyecto como proyecto Maven en IntelliJ IDEA o Eclipse.
- Configura la JDK a la versión 17 del proyecto.
- Ejecuta `InventarioPlusApplication.java` (clase con `main`) como aplicación Java/Spring Boot.

## Posibles problemas y soluciones rápidas

- Error "Unsupported class file major version" o versión de Java: asegúrate de tener JDK 17 instalado y configurado en el IDE/MAVEN_OPTS.
- Errores con Lombok: instala el plugin Lombok del IDE y activa el Annotation Processing.
- Si la aplicación no inicia por puerto ocupado: cambia `server.port` o detén el proceso que lo ocupa.

## Próximos pasos sugeridos

- Implementar persistencia (por ejemplo, añadir `spring-boot-starter-data-jpa` y una base de datos H2/MySQL/Postgres).
- Añadir ejemplos de endpoints REST y pruebas de integración.
- Añadir documentación de la API (OpenAPI/Swagger).

## Contribuir

1. Crea un fork del repositorio.
2. Crea una rama con una descripción clara (`feature/`, `fix/`).
3. Asegúrate de que los tests pasen y añade pruebas cuando añadas funcionalidad.
4. Envía un pull request describiendo los cambios.

## Licencia

Este proyecto incluye un `pom.xml` con campos de licencia vacíos. Añade la licencia que prefieras (por ejemplo MIT) y actualiza el `pom.xml` si vas a publicarlo.

---

Si quieres, puedo:

- Añadir un `Dockerfile` básico para contenerizar la aplicación.
- Configurar H2 en memoria para empezar con persistencia mínima.
- Generar un `.gitignore` recomendado para proyectos Java/Maven.

Dime qué prefieres y lo creo por ti.
