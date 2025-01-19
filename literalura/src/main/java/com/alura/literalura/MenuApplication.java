package com.alura.literalura;

import com.alura.literalura.principal.LiteraturaApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;


import java.util.Scanner;

@SpringBootApplication
public class MenuApplication implements CommandLineRunner {

    private final LiteraturaApplication literaturaApplication;

    public MenuApplication(LiteraturaApplication literaturaApplication) {
        this.literaturaApplication = literaturaApplication;
    }

    public static void main(String[] args) {
        SpringApplication.run(MenuApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese parte del título del libro: ");
                    String titulo = scanner.nextLine();
                    literaturaApplication.buscarLibroPorTitulo(titulo);
                }
                case 2 -> literaturaApplication.listarLibros();
                case 3 -> literaturaApplication.listarAutores();
                case 4 -> {
                    System.out.print("Ingrese el año para filtrar autores vivos: ");
                    int anio = scanner.nextInt();
                    literaturaApplication.listarAutoresVivos(anio);
                }
                case 5 -> {
                    System.out.print("Ingrese el idioma para filtrar libros: ");
                    String idioma = scanner.nextLine();
                    literaturaApplication.listarLibrosPorIdioma(idioma);
                }
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    private void mostrarMenu() {
        System.out.println("\nMenú:");
        System.out.println("1 - Buscar libro por título");
        System.out.println("2 - Listar libros registrados");
        System.out.println("3 - Listar autores registrados");
        System.out.println("4 - Listar autores vivos en un determinado año");
        System.out.println("5 - Listar libros por idioma");
        System.out.println("0 - Salir");
    }
}
