@echo off
rem Configurar codificación UTF-8 para la consola
powershell -command "Set-ItemProperty HKCU:\Console VirtualTerminalLevel -Type DWORD 1"
chcp 65001

rem Configurar variables de entorno
set "JAVA_OPTS=-Dfile.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dspring.output.ansi.enabled=ALWAYS"
set "CATALINA_OPTS=-Dfile.encoding=UTF-8"

rem Cambiar al directorio de Tomcat
cd /d "C:\Program Files\Apache Software Foundation\Tomcat 10.1\bin"

rem Iniciar Tomcat con la configuración adecuada
call catalina.bat run
