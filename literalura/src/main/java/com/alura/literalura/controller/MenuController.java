package com.alura.literalura.controller;

import com.alura.literalura.model.Autor;
import com.alura.literalura.model.Libro;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.repository.LibroRepository;
import com.alura.literalura.service.GutendexService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {
    private final AutorRepository autorRepository;
    private final LibroRepository libroRepository;
    private final GutendexService gutendexService;

    public MenuController(AutorRepository autorRepository, LibroRepository libroRepository, GutendexService gutendexService) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;
        this.gutendexService = gutendexService;
    }

    @GetMapping("/buscar-libro")
    public List<Map<String, Object>> buscarLibroPorTitulo(@RequestParam String titulo) {
        return gutendexService.buscarLibrosPorTitulo(titulo);
    }

    @GetMapping("/listar-libros")
    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    @GetMapping("/listar-autores")
    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    @GetMapping("/listar-autores-vivos")
    public List<Autor> listarAutoresVivos(@RequestParam int anio) {
        return autorRepository.findByAnioMuerteIsNullAndAnioNacimientoLessThanEqual(anio);
    }

    @GetMapping("/listar-libros-por-idioma")
    public List<Libro> listarLibrosPorIdioma(@RequestParam String idioma) {
        return libroRepository.findByIdioma(idioma);
    }
}

