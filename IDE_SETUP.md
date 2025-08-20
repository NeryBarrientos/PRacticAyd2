# Configuración del IDE

## Visual Studio Code

### Extensiones Recomendadas
1. **Java Development**
   - Extension Pack for Java
   - Spring Boot Extension Pack
   - Language Support for Java by Red Hat
   - Maven for Java
   - Project Manager for Java
   - Test Runner for Java

2. **Web Development**
   - JSF (Java Server Faces) Support
   - XML Tools
   - HTML CSS Support
   - PrimeFaces Helper

3. **Base de Datos**
   - MySQL (por mtheriault)
   - Database Client

4. **Utilidades**
   - GitLens
   - Git History
   - XML Tools
   - Prettier - Code formatter

### Exportar Configuración VS Code
1. Instalar la extensión "Settings Sync"
2. Presionar `Ctrl + Shift + P`
3. Escribir "Settings Sync: Export"
4. Se creará un archivo gist con tu configuración

### Importar Configuración VS Code
1. Instalar la extensión "Settings Sync"
2. Presionar `Ctrl + Shift + P`
3. Escribir "Settings Sync: Import"
4. Pegar el ID del gist

## Eclipse

### Extensiones Necesarias
1. **Spring Tools 4**
   - Spring Tool Suite 4
   - Spring Boot Dashboard

2. **Jakarta EE Development**
   - Jakarta EE Developer Tools
   - Jakarta Server Adapters

3. **Web Development**
   - Eclipse Enterprise Java and Web Developer Tools
   - JSF Tools
   - HTML, CSS, JavaScript Development Tools

4. **Base de Datos**
   - DBeaver (integrado o standalone)
   - MySQL Connector

### Exportar Configuración Eclipse
1. Ir a `File > Export`
2. Seleccionar `General > Preferences`
3. Marcar los elementos que deseas exportar
4. Elegir una ubicación para el archivo `.epf`

### Importar Configuración Eclipse
1. Ir a `File > Import`
2. Seleccionar `General > Preferences`
3. Seleccionar el archivo `.epf`
4. Clic en "Import"

### Workspace Preferences (.metadata)
La carpeta `.metadata` contiene:
- Configuración del workspace
- Historial
- Configuración de los proyectos
- Configuración de los servidores

Ubicación típica:
```
C:\Users\<usuario>\eclipse-workspace\.metadata
```

### Exportar Configuración del Servidor
1. Ir a la vista Servers
2. Clic derecho en el servidor Tomcat
3. Export > Server Configuration
4. Guardar el archivo `.server`

## settings.json para VS Code
```json
{
  "java.configuration.updateBuildConfiguration": "automatic",
  "java.server.launchMode": "Standard",
  "java.compile.nullAnalysis.mode": "automatic",
  "java.format.settings.url": "eclipse-formatter.xml",
  "editor.formatOnSave": true,
  "files.encoding": "utf8",
  "files.autoGuessEncoding": true,
  "terminal.integrated.defaultProfile.windows": "Command Prompt",
  "workbench.colorTheme": "Default Dark Modern"
}
```

## Configuración de Maven
```xml
<settings>
  <localRepository>${user.home}/.m2/repository</localRepository>
  <interactiveMode>true</interactiveMode>
  <offline>false</offline>
  <mirrors>
    <mirror>
      <id>maven-default-http-blocker</id>
      <mirrorOf>external:http:*</mirrorOf>
      <name>Pseudo repository to mirror external repositories initially using HTTP.</name>
      <url>http://0.0.0.0/</url>
      <blocked>true</blocked>
    </mirror>
  </mirrors>
</settings>
```

## JDK Configuration
```
JAVA_HOME=C:\Program Files\Java\jdk-23
PATH=%JAVA_HOME%\bin;%PATH%
```

Para mantener esta configuración entre diferentes equipos, se recomienda:
1. Guardar todos estos archivos en el repositorio
2. Crear un script de configuración inicial
3. Documentar cualquier cambio en la configuración
4. Mantener un registro de versiones de las extensiones
