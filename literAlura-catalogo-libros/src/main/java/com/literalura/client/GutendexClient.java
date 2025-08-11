package com.literalura.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.literalura.dto.LibroDTO;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class GutendexClient {

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public List<LibroDTO> buscarLibros(String titulo) {
        try {
            String encodedTitulo = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
            String url = "https://gutendex.com/books/?search=" + encodedTitulo;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonNode root = mapper.readTree(response.body()).get("results");

            if (root.isArray() && root.size() > 0) {
                List<LibroDTO> resultados = new ArrayList<>();
                for (int i = 0; i < Math.min(5, root.size()); i++) {
                    resultados.add(mapper.treeToValue(root.get(i), LibroDTO.class));
                }
                return resultados;
            }
        } catch (Exception e) {
            System.out.println("❌ Error al consultar Gutendex: " + e.getMessage());
        }

        return List.of();
    }
}