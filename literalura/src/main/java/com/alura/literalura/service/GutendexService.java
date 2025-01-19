package com.alura.literalura.service;

import com.alura.literalura.model.Autor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class GutendexService {
    private final RestTemplate restTemplate = new RestTemplate();

    public List<Map<String, Object>> buscarLibrosPorTitulo(String titulo) {
        String url = "https://gutendex.com/books?search=" + titulo;
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        return (List<Map<String, Object>>) response.getBody().get("results");
    }

    public List<Map<String, Object>> buscarLibrosPorIdioma(String idioma) {
        String url = "https://gutendex.com/books/?languages=" + idioma;
        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, null, Map.class);
        Map<String, Object> body = response.getBody();
        return (List<Map<String, Object>>) body.get("results");
    }

}
