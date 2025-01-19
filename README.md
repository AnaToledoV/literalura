# Literalura Application

Este proyecto es una aplicación para gestionar información de libros y autores utilizando datos de la API pública **Gutendex**. Permite buscar libros, listar autores, filtrar por idiomas y más. El proyecto está construido en **Java** con **Spring Boot** y utiliza **PostgreSQL** como base de datos.

## Funcionalidades principales

1. **Buscar libros por título**: Utiliza la API de Gutendex para buscar libros por su título.
2. **Guardar libros**: Permite guardar libros en la base de datos local.
3. **Listar todos los libros registrados**: Recupera y muestra los libros almacenados.
4. **Listar todos los autores registrados**: Muestra una lista de todos los autores almacenados en la base de datos.
5. **Listar autores vivos en un año específico**: Filtra autores que estaban vivos en un año dado.
6. **Listar libros por idioma**: Busca libros en un idioma específico utilizando la API de Gutendex.
7. **Interfaz RESTful**: Se pueden realizar operaciones mediante endpoints expuestos por la API.

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot**
  - Spring Web
  - Spring Data JPA
- **PostgreSQL** como base de datos
- **Gutendex API** para datos de libros
- **Maven** para la gestión de dependencias

## Configuración e instalación

1. **Clonar el repositorio**:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   cd literatura-application
   ```

2. **Configurar la base de datos PostgreSQL**:
   - Crea una base de datos llamada `literalura`.
   - Actualiza el archivo `application.properties` con tus credenciales:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
     spring.datasource.username=tu_usuario
     spring.datasource.password=tu_contraseña
     spring.jpa.hibernate.ddl-auto=update
     spring.jpa.show-sql=true
     spring.jpa.properties.hibernate.format_sql=true
     ```

3. **Ejecutar la aplicación**:
   ```bash
   mvn spring-boot:run
   ```

4. **Probar los endpoints**:
   Puedes usar herramientas como **Postman** o **Insomnia** para interactuar con la API REST.

## Endpoints disponibles

### Autor
- **Listar autores**: `GET /menu/listar-autores`
- **Listar autores vivos en un año específico**: `GET /menu/listar-autores-vivos?anio={anio}`

### Libro
- **Buscar libro por título**: `GET /menu/buscar-libro?titulo={titulo}`
- **Listar libros registrados**: `GET /menu/listar-libros`
- **Listar libros por idioma**: `GET /menu/listar-libros-por-idioma?idioma={idioma}`

## Estructura del proyecto

```
├── src/main/java
│   ├── com.example.literatura
│   │   ├── controller
│   │   │   └── MenuController.java
│   │   ├── model
│   │   │   ├── Autor.java
│   │   │   └── Libro.java
│   │   ├── repository
│   │   │   ├── AutorRepository.java
│   │   │   └── LibroRepository.java
│   │   ├── service
│   │   │   ├── GutendexService.java
│   │   │   └── LiteraturaApplication.java
├── src/main/resources
│   ├── application.properties
├── pom.xml
```

## Próximos pasos

- **Autenticación**: Implementar seguridad mediante autenticación y autorización.
- **Migraciones**: Utilizar herramientas como Flyway para el manejo de esquemas en la base de datos.
- **Mejorar la persistencia**: Añadir validaciones y control de errores en las operaciones con la base de datos.

---

Este proyecto fue desarrollado como parte de un desafío de programación y está en continuo desarrollo. ¡Cualquier contribución es bienvenida!
