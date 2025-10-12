# InventarioPlus (subproyecto)

Este README contiene instrucciones rápidas para ejecutar la aplicación y cómo conectarla a una base de datos MySQL externa.

## Ejecutar usando H2 (por defecto)

La aplicación por defecto usa H2 en modo archivo (persistente) cuando no se proporcionan variables de entorno. Para arrancar:

```powershell
cd InitializrSpringbootProject
mvn -DskipTests spring-boot:run
```

La consola H2 estará disponible en: `http://localhost:8080/h2-console` (JDBC URL por defecto: `jdbc:h2:file:./data/inventarioplus`, usuario: `SA`).

## Conectar a MySQL (recomendado en desarrollo/producción)

Define las siguientes variables de entorno antes de arrancar la app (ejemplo PowerShell):

```powershell
$env:SPRING_DATASOURCE_URL = 'jdbc:mysql://dbhost:3306/inventarioplus?useSSL=false&serverTimezone=UTC'
$env:SPRING_DATASOURCE_USERNAME = 'miusuario'
$env:SPRING_DATASOURCE_PASSWORD = 'mipassword'
$mvnCmd = 'mvn -DskipTests spring-boot:run'
Invoke-Expression $mvnCmd
```

Alternativamente, en Linux/macOS por bash:

```bash
export SPRING_DATASOURCE_URL='jdbc:mysql://dbhost:3306/inventarioplus?useSSL=false&serverTimezone=UTC'
export SPRING_DATASOURCE_USERNAME='miusuario'
export SPRING_DATASOURCE_PASSWORD='mipassword'
./mvnw -DskipTests spring-boot:run
```

### Notas
- La configuración JPA por defecto usa `spring.jpa.hibernate.ddl-auto=update`. En producción se recomienda gestionarlo con migraciones (Flyway/Liquibase).
- Si quieres que aplique las credenciales directamente al archivo `application.properties` dímelas y yo las escribiré (evitar exponerlas en commits públicos).

