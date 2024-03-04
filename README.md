Gestor de Productos de Almacén
===
Este proyecto Java es un CRUD diseñado para gestionar productos en un almacén. Utiliza JDK 17, MySQL con JDBC para la conexión con la base de datos, sigue el patrón de diseño Modelo-Vista-Controlador (MVC) y se gestiona con Maven. Permite controlar el código, nombre, stock, categoría y precio de cada producto. Además, incluye funciones de búsqueda por nombre con filtrado en tiempo real y también permite filtrar por categoría.
## Requisitos
- JDK 17
- MySQL Server/WorkBench Client
- Netbeans IDE

## Configuración de la Base de Datos
- Asegúrate de tener MySQL Server instalado en tu sistema.
- Ejecuta el siguiente código en tu servidor MySQL para crear la base de datos y las tablas necesarias.

```
create database almacen;

create table almace.productos(
codigo int auto_increment not null primary key,
nombre varchar(50),
stock int,
categoria varchar(50),
precio decimal(10,2)
)ENGINE=InnoDB;

create table almacen.usuarios(
nombre varchar(50),
clave varchar(50)
)ENGINE=InnoDB;
```

## Configuración del Proyecto
- Clona este repositorio en tu máquina local.
- Asegúrate de tener configurada la conexión a tu base de datos en el archivo src\main\java\Model\Conexion.java
```
Configuración por defecto para la conexión a mysql

String url= "jdbc:mysql://localhost:3306/almacen?serverTimezone=UTC";
String user="root";
String password="";
```

## Compilación y Ejecución
- Desde NetBeans, abre el proyecto y asegúrate de que las dependencias de Maven estén configuradas correctamente en el archivo pom.xml.
- Compila y ejecuta el proyecto en NetBeans, la clase Main es el que se debe compilar.
  
- Esto iniciará la aplicación y podrás acceder al gestor de productos a través de la interfaz de usuario.
- Registrate , para eso genera un nuevo usuario con nombre y contraseña luego de eso ya puede iniciar sesion.


## Estructura del Proyecto
El proyecto sigue el patrón de diseño Modelo-Vista-Controlador (MVC):

- Modelo: Contiene las clases que representan los datos y la lógica de conexión y consultas a la BD.
- Vista: Contiene las interfaces gráficas de inicio Login , Registar y el Almacen CRUD.
- Controlador: Actúa como intermediario entre el modelo y la vista, maneja las interacciones del usuario.

## Dependencias

 El proyecto utiliza las siguientes dependencias de Maven:
```
- MySQL Connector/J: Para la conexión con la base de datos MySQL.
- FlatLaf: Para la apariencia y estilo de la interfaz gráfica de usuario.
```


### NOTA

Este es proyecto es de practica para afinzar mis conociemtos y mejorar mi lógica soy nivel Trainee, por lo que es posible que pueda tener algún error que no haya visto o que el código pueda escribirse de mejor manera.
Acepto toda clase de críticas contructuvias para mejorar.


## Contacto
- [LinkeIn](https://www.linkedin.com/in/marcos-silva-a33105209/)


