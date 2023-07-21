# Aplicación CRUD con Angular y Spring Boot

![Node](https://img.shields.io/badge/Node-18.13.0-green)
![npm](https://img.shields.io/badge/npm-9.6.5-orange)
![Angular](https://img.shields.io/badge/Angular-15.2.4-red)
![SpringBoot](https://img.shields.io/badge/SpringBoot-3.1.1-green)
![JAVA](https://img.shields.io/badge/JAVA-17-blue)
![MySQL](https://img.shields.io/badge/MySQL-yellow)
![JPA](https://img.shields.io/badge/JPA-yellow)

Es una aplicación web simple, que permite listar, agregar, editar y eliminar clientes. El FrontEnd se desarrollo con Angular y el Backend con Spring Boot. 

## Instalación

- **Clonar el proyecto.** 
- **Base de Datos MySQL:** Se debe crear una base de datos y un usuario con permisos completos.
- **Variables de entorno:** Definir las variables de entorno o reemplazarlas en el archivo `application.properties` para 
    conectarse a la base de datos Ej.:
  - MYSQL_USER=root
  - MYSQL_PASSWORD=12345
  - MYSQL_DB=db_springboot
  - MYSQL_PORT=3306
- Ejecutar en la terminal el comando `npm install` en el directorio de clients-frontend/.

## Ejecutar la aplicación

- **Backend:** Se debe ejecutar el comando `./mvnw spring-boot:run` en el directorio de la api/.

    -> La API estará disponible en http://localhost:8080 Ej. http://localhost:8080/api/clients

- **Frontend:** Se debe ejecutar en la terminal el comando `npm run start` Y la aplicación estará disponible en http://localhost:4200/clients.