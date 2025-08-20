# Guía de Instalación y Configuración del Proyecto

## Requisitos Previos

### Software Necesario
1. **Java Development Kit (JDK)**
   - Versión: JDK 23
   - [Descargar JDK](https://www.oracle.com/java/technologies/downloads/)
   - Configurar JAVA_HOME en variables de entorno:
     ```
     JAVA_HOME=C:\Program Files\Java\jdk-23
     Path=%JAVA_HOME%\bin
     ```

2. **Apache Tomcat**
   - Versión: 10.1.43
   - [Descargar Tomcat](https://tomcat.apache.org/download-10.cgi)
   - Instalar en: `C:\Program Files\Apache Software Foundation\Tomcat 10.1`

3. **MySQL**
   - Versión: 8.0.41 o superior
   - [Descargar MySQL](https://dev.mysql.com/downloads/mysql/)
   - Puerto: 3306
   - Crear una base de datos llamada `oraclesport`

4. **Maven**
   - El proyecto incluye Maven Wrapper, no es necesario instalar Maven

### Configuración de Base de Datos
1. Crear un usuario en MySQL:
   ```sql
   CREATE USER 'root'@'localhost' IDENTIFIED BY 'devgentrac';
   GRANT ALL PRIVILEGES ON oraclesport.* TO 'root'@'localhost';
   FLUSH PRIVILEGES;
   ```

## Configuración del Proyecto

### Clonar el Proyecto
1. Crear la carpeta: `C:\Users\<tu_usuario>\eclipse-workspace\`
2. Copiar el proyecto en esa ubicación

### Configuración de Tomcat
1. Copiar el archivo `setenv.bat` a la carpeta `bin` de Tomcat:
   ```batch
   copy "setenv.bat" "C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin\"
   ```

2. Configurar la codificación UTF-8:
   - Crear/editar el archivo `C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin\setenv.bat`:
   ```batch
   set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8"
   set "CATALINA_OPTS=%CATALINA_OPTS% -Dfile.encoding=UTF-8"
   ```

## Compilación y Despliegue

### Compilar el Proyecto
1. Abrir terminal en la carpeta del proyecto
2. Ejecutar:
   ```batch
   .\mvnw clean package
   ```
3. El archivo WAR se generará en: `target\Practica1AYD.war`

### Desplegar en Tomcat
1. Copiar el archivo WAR:
   ```batch
   copy "target\Practica1AYD.war" "C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps\"
   ```
2. Iniciar Tomcat:
   ```batch
   cd "C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin"
   startup.bat
   ```

## Acceder a la Aplicación
1. URL de acceso: [http://localhost:8080/Practica1AYD/](http://localhost:8080/Practica1AYD/)
2. Credenciales por defecto:
   - Usuario: admin
   - Contraseña: admin

## Estructura del Proyecto
```
Practica1AYD/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── demo/
│   │   ├── resources/
│   │   │   └── application.properties
│   │   └── webapp/
│   │       ├── WEB-INF/
│   │       │   ├── faces-config.xml
│   │       │   └── web.xml
│   │       └── pages/
│   │           ├── login.xhtml
│   │           └── ping.xhtml
│   └── test/
└── pom.xml
```

## Solución de Problemas Comunes

### Problemas de Codificación
Si ves caracteres extraños en la consola de Tomcat:
1. Asegúrate de que el archivo `setenv.bat` está en la carpeta correcta
2. Verifica que la consola está usando codificación UTF-8:
   ```batch
   chcp 65001
   ```

### Problemas de Conexión a Base de Datos
1. Verifica que MySQL está corriendo:
   ```batch
   netstat -an | findstr "3306"
   ```
2. Comprueba las credenciales en `application.properties`

### Problemas de Compilación
1. Verifica la versión de Java:
   ```batch
   java -version
   ```
2. Limpia la caché de Maven:
   ```batch
   .\mvnw clean
   ```

## Notas Adicionales
- El proyecto usa Spring Boot 3.5.3
- Jakarta Faces (JSF) 4.0.6
- PrimeFaces 14.0.6
- Weld (CDI) 5.1.2.Final

## Contacto y Soporte
- Para reportar problemas, usar la sección de Issues en GitHub
- Para contribuciones, crear un Pull Request
