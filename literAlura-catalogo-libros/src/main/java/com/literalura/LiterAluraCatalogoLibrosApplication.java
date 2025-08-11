package com.literalura;

import com.literalura.service.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiterAluraCatalogoLibrosApplication implements CommandLineRunner {

    @Autowired
    private CatalogoService catalogoService;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(LiterAluraCatalogoLibrosApplication.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n📚 Menú LiterAlura");
            System.out.println("1 - Buscar libro por título");
            System.out.println("2 - Listar todos los libros");
            System.out.println("3 - Listar libros por idioma");
            System.out.println("4 - Listar todos los autores");
            System.out.println("5 - Listar autores vivos en un año");
            System.out.println("0 - Salir");
            System.out.print("Seleccione una opción: ");

            String entrada = scanner.nextLine();

            if (!entrada.matches("\\d")) {
                System.out.println("❌ Entrada inválida.");
                continue;
            }

            opcion = Integer.parseInt(entrada);

            try {
                switch (opcion) {
                    case 1 -> {
                        System.out.print("Ingrese el título del libro: ");
                        String titulo = scanner.nextLine();
                        catalogoService.buscarYGuardarLibroInteractivo(titulo, scanner);
                    }
                    case 2 -> catalogoService.listarLibros().forEach(System.out::println);
                    case 3 -> {
                        System.out.print("Ingrese el idioma (ej: 'en', 'es'): ");
                        String idioma = scanner.nextLine();
                        catalogoService.listarLibrosPorIdioma(idioma).forEach(System.out::println);
                    }
                    case 4 -> catalogoService.listarAutores().forEach(System.out::println);
                    case 5 -> {
                        System.out.print("Ingrese el año: ");
                        int año = Integer.parseInt(scanner.nextLine());
                        catalogoService.listarAutoresVivosEn(año).forEach(System.out::println);
                    }
                    case 0 -> System.out.println("👋 ¡Hasta luego!");
                    default -> System.out.println("❌ Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error inesperado: " + e.getMessage());
            }
        }
    }
}