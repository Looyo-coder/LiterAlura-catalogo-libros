package com.literalura.service;

import com.literalura.dto.AutorDTO;
import com.literalura.dto.LibroDTO;
import com.literalura.model.Autor;
import com.literalura.model.Libro;
import com.literalura.repository.AutorRepository;
import com.literalura.repository.LibroRepository;
import com.literalura.client.GutendexClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class CatalogoService {

    @Autowired
    private GutendexClient gutendexClient;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public void buscarYGuardarLibroInteractivo(String titulo, Scanner scanner) {
        List<LibroDTO> resultados = gutendexClient.buscarLibros(titulo);

        if (resultados.isEmpty()) {
            System.out.println("❌ No se encontraron libros.");
            return;
        }

        System.out.println("🔎 Resultados encontrados:");
        for (int i = 0; i < resultados.size(); i++) {
            LibroDTO dto = resultados.get(i);
            System.out.printf("%d - %s (%s) - Descargas: %d\n", i + 1,
                    dto.getTitle(),
                    String.join(", ", dto.getLanguages()),
                    dto.getNumeroDeDescargas());
        }

        System.out.print("Seleccione el número del libro que desea guardar: ");
        try {
            int seleccion = Integer.parseInt(scanner.nextLine()) - 1;
            if (seleccion < 0 || seleccion >= resultados.size()) {
                System.out.println("❌ Selección inválida.");
                return;
            }

            LibroDTO dto = resultados.get(seleccion);

            if (libroRepository.existsByTitulo(dto.getTitle())) {
                System.out.println("⚠️ El libro ya está registrado.");
                return;
            }

            Libro libro = new Libro();
            libro.setTitulo(dto.getTitle());
            libro.setIdioma(String.join(", ", dto.getLanguages()));
            libro.setNumeroDeDescargas(dto.getNumeroDeDescargas());

            List<Autor> autores = dto.getAuthors().stream().map(autorDTO -> {
                Autor autor = new Autor();
                autor.setNombre(autorDTO.getNombre());
                autor.setAnoNacimiento(autorDTO.getAnoNacimiento());
                autor.setAnoFallecimiento(autorDTO.getAnoFallecimiento());
                return autor;
            }).toList();

            libro.setAutores(autores);
            autores.forEach(autorRepository::save);
            libroRepository.save(libro);

            System.out.println("✅ Libro guardado exitosamente.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida.");
        }
    }

    public List<Libro> listarLibros() {
        return libroRepository.findAll();
    }

    public List<Libro> listarLibrosPorIdioma(String idioma) {
        return libroRepository.findByIdiomaContainingIgnoreCase(idioma);
    }

    public List<Autor> listarAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> listarAutoresVivosEn(int año) {
        return autorRepository.findByAnoNacimientoLessThanEqualAndAnoFallecimientoGreaterThanEqual(año, año);
    }
}