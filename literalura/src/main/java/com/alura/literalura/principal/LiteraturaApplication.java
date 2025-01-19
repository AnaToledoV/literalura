package com.alura.literalura.principal;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.GutendexService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LiteraturaApplication {

	private final AutorRepository autorRepository;
	private final LibroRepository libroRepository;
	private final GutendexService gutendexService;

	public LiteraturaApplication(AutorRepository autorRepository, LibroRepository libroRepository, GutendexService gutendexService) {
		this.autorRepository = autorRepository;
		this.libroRepository = libroRepository;
		this.gutendexService = gutendexService;
	}

	public void buscarLibroPorTitulo(String titulo) {
		List<Map<String, Object>> resultados = gutendexService.buscarLibrosPorTitulo(titulo);

		if (resultados.isEmpty()) {
			System.out.println("No se encontraron libros con ese título.");
		} else {
			// Obtener el primer libro
			Map<String, Object> libro = resultados.get(0);

			// Extraer los datos específicos
			String tituloLibro = (String) libro.get("title");

			// Extraer el autor (tomando el primer autor de la lista de autores)
			List<Map<String, Object>> autores = (List<Map<String, Object>>) libro.get("authors");
			String autor = autores.isEmpty() ? "Desconocido" : (String) autores.get(0).get("name");

			// Obtener el idioma (en este caso solo toma el primer idioma)
			List<String> idiomas = (List<String>) libro.get("languages");
			String idioma = idiomas.isEmpty() ? "Desconocido" : idiomas.get(0);

			// Obtener el número de descargas
			Integer descargas = (Integer) libro.get("download_count");

			// Mostrar la información del primer libro encontrado
			System.out.println("\nPrimer resultado encontrado:");
			System.out.println("Título: " + tituloLibro);
			System.out.println("Autor: " + autor);
			System.out.println("Idioma: " + idioma);
			System.out.println("Número de descargas: " + descargas);
		}
	}

	// Metodo para guardar un libro
	@Transactional
	public void guardarLibro(Libro libro) {
		libroRepository.save(libro);
		System.out.println("Libro guardado: " + libro.getTitulo());
	}

	// Opción 2: Listar todos los libros registrados
	public void listarLibros() {
		List<Libro> libros = libroRepository.findAll();
		if (libros.isEmpty()) {
			System.out.println("No hay libros registrados.");
		} else {
			System.out.println("\nLista de libros registrados:");
			for (Libro libro : libros) {
				System.out.println("Título: " + libro.getTitulo());
			}
		}
	}

	// Opción 3: Listar todos los autores registrados con sus libros
	public void listarAutores() {
		List<Autor> autores = autorRepository.findAll();
		if (autores.isEmpty()) {
			System.out.println("No hay autores registrados.");
		} else {
			System.out.println("\nLista de autores registrados:");
			for (Autor autor : autores) {
				System.out.println("Nombre: " + autor.getNombre());
				System.out.println("Año de nacimiento: " + autor.getAnioNacimiento());
				System.out.println("Año de muerte: " + (autor.getAnioMuerte() != null ? autor.getAnioMuerte() : "N/A"));
				System.out.println("-----------------------------");
			}
		}
	}


	// Opción 4: Listar autores vivos en un determinado año
	public void listarAutoresVivos(int anio) {
		List<Autor> autores = autorRepository.findAll();
		boolean encontrados = false;

		for (Autor autor : autores) {
			// Si el autor no tiene fecha de muerte o murió después o en el año especificado
			if (autor.getAnioMuerte() == null || autor.getAnioMuerte() >= anio) {
				System.out.println("Autor: " + autor.getNombre() + " (Año de nacimiento: " + autor.getAnioNacimiento() + ")");
				encontrados = true;
			}
		}

		if (!encontrados) {
			System.out.println("No se encontraron autores vivos en el año " + anio + ".");
		}
	}


	// Opción 5: Listar libros por idioma
	public void listarLibrosPorIdioma(String idioma) {
		List<Map<String, Object>> resultados = gutendexService.buscarLibrosPorIdioma(idioma);

		if (resultados.isEmpty()) {
			System.out.println("No se encontraron libros en el idioma " + idioma + ".");
		} else {
			System.out.println("\nLibros en el idioma " + idioma + ":");
			for (Map<String, Object> libro : resultados) {
				String tituloLibro = (String) libro.get("title");
				System.out.println("Título: " + tituloLibro);
			}
		}
	}

	// Opción 0: Salir
	public void salir() {
		System.out.println("¡Hasta pronto!");
	}
}
