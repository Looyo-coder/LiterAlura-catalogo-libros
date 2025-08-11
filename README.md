# LiterAlura

Proyecto Java con Spring Boot que permite buscar libros en la API pública de Gutendex, guardarlos en una base de datos PostgreSQL y consultarlos desde un menú interactivo en consola.

---

## Funcionalidades

-  Buscar libros por título (con selección entre los primeros resultados)
-  Guardar libros y autores en base de datos
-  Listar libros por idioma
-  Listar todos los autores
-  Consultar autores vivos en un año específico

---

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Maven
- PostgreSQL
- Hibernate (JPA)
- Gutendex API
- IntelliJ IDEA

---

## Estructura del proyecto
src/
└── main/
    └── java/
        └── com/
            └── literalura/
                ├── client/         # Cliente HTTP para consumir la API de Gutendex
                ├── dto/            # Clases DTO para mapear respuestas JSON
                ├── model/          # Entidades JPA (Libro, Autor)
                ├── repository/     # Interfaces de persistencia (Spring Data JPA)
                ├── service/        # Lógica de negocio y persistencia
                └── LiterAluraCatalogoLibrosApplication.java  # Clase principal con menú interactivo

---

## Requisitos

- Java 17+
- PostgreSQL corriendo localmente
- Maven instalado

---

## Ejecución

1. Clona el repositorio:

   ```bash
   git clone https://github.com/Looyo-coder/literalura-catalogo-libros.git
   cd literalura-catalogo-libros

2. Configura tu base de datos en application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update

3. Ejecuta el proyecto:
   
mvn clean install
mvn spring-boot:run


4. Interactúa con el menú en consola:

Menú LiterAlura
1 - Buscar libro por título
2 - Listar todos los libros

---

# Autor
Desarrollado por Looyo-coder como parte del programa Oracle ONE - Alura Latam.

