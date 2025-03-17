package com.bibliotecas.app;

import com.bibliotecas.model.*;
import java.util.Scanner;

public class Main {

    private static final Biblioteca biblioteca = new Biblioteca();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.print("\n");


            switch (opcion) {
                case 1:
                    agregarPublicacion(scanner);
                    break;
                case 2:
                    registrarUsuario(scanner);
                    break;
                case 3:
                    biblioteca.listarPublicaciones();
                    break;
                case 4:
                    listarPrestamosActivos(scanner);
                    break;
                case 5:
                    biblioteca.ordenarPorAutor();
                    biblioteca.listarPublicaciones();
                    break;
                case 6:
                    biblioteca.ordenarPorTitulo();
                    biblioteca.listarPublicaciones();
                    break;
                case 7:
                    registrarPrestamo(scanner);
                    break;
                case 8:
                    registrarDevolucion(scanner);
                    break;
                case 9:
                    rellenarBibliotecaDummy(biblioteca);
                    break;
                case 10:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 10);
        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println(" -- MENÚ BIBLIOTECA -- ");
        System.out.println("1. Agregar publicación");
        System.out.println("2. Registrar usuario");
        System.out.println("3. Listar todas las publicaciones");
        System.out.println("4. Listar préstamos activos de un usuario");
        System.out.println("5. Ordenar por autor");
        System.out.println("6. Ordenar por título");
        System.out.println("7. Registrar préstamo");
        System.out.println("8. Registrar devolución");
        System.out.println("9. Rellenar biblioteca");
        System.out.println("10. Salir");
        System.out.print("Seleccione opción: ");
    }

    public static void agregarPublicacion(Scanner scanner) {
        System.out.println("Ingrese título: ");
        String titulo = scanner.nextLine();
        System.out.println("Ingrese autor: ");
        String autor = scanner.nextLine();
        System.out.println("Ingrese año de publicación: ");
        int anio = scanner.nextInt();
        scanner.nextLine();

        Publicacion libro = new Libro(titulo, autor, "1234567890123", anio, 200);
        biblioteca.agregarPublicacion(libro);
        System.out.println("Publicación agregada con éxito.");
    }

    public static void registrarUsuario(Scanner scanner) {
        System.out.println("Ingrese ID de usuario: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese nombre del usuario: ");
        String nombre = scanner.nextLine();

        biblioteca.registrarUsuario(id, nombre);
        System.out.println("\n");
    }


    public static void listarPrestamosActivos(Scanner scanner) {
        System.out.println("Ingrese ID de usuario: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (!biblioteca.usuarios.containsKey(id)){
            System.out.println("El usuario no está registrado.");
            System.out.print("\n");
            return;}
        biblioteca.mostrarPrestamosActivos(id);
        System.out.print("\n");
    }

    public static void registrarPrestamo(Scanner scanner) {
        System.out.println("Ingrese ID de usuario: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (!biblioteca.usuarios.containsKey(id)){
            System.out.println("El usuario no está registrado. Regístrelo antes de continuar.");
            return;
        }
        System.out.println("Ingrese título de la publicación: ");
        String titulo = scanner.nextLine();

        Publicacion pub = biblioteca.buscarPublicacionPorTitulo(titulo);
        if (pub instanceof PublicacionPrestable) {
            biblioteca.registrarPrestamo(id, (PublicacionPrestable) pub);
        } else {
            System.out.println("La publicación no es prestable o no existe.");
            System.out.print("\n");
        }
    }

    public static void registrarDevolucion(Scanner scanner) {
        System.out.println("Ingrese ID de usuario: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese título de la publicación: ");
        String titulo = scanner.nextLine();

        Publicacion pub = biblioteca.buscarPublicacionPorTitulo(titulo);
        if (pub instanceof PublicacionPrestable) {
            biblioteca.registrarDevolucion(id, (PublicacionPrestable) pub);
        } else {
            System.out.println("La publicación no es prestable o no existe.");
            System.out.print("\n");
        }
    }

    private static void rellenarBibliotecaDummy(Biblioteca biblioteca) {
        Libro libro =
                new Libro("Don quijote de la Mancha", "Miguel de Cervantes", "9788424117386",1490 , 863);
        biblioteca.agregarPublicacion(libro);
        libro = new Libro("El señor de los anillos", "J.R.R. Tolkien", "9788445000663", 1954,  1216);
        biblioteca.agregarPublicacion(libro);
        libro = new Libro("Cien años de soledad", "Gabriel García Márquez", "9788420471839",1967,  432);
        biblioteca.agregarPublicacion(libro);
        libro = new Libro("El principito", "Antoine de Saint-Exupéry", "9788493650016",1943,  96);
        biblioteca.agregarPublicacion(libro);
        // Agregamos también revistas o audiolibros
        Revista revista =
                new Revista(
                        "National Geographic", "National Geographic", 1888,"1211-1221", 1, Revista.Mes.ENERO, Revista.CategoriaRevista.CIENCIA);
        biblioteca.agregarPublicacion(revista);
        revista =
                new Revista("Muy Interesante", "Muy Interesante", 1981, "2112-5544", 1,  Revista.Mes.ENERO, Revista.CategoriaRevista.CIENCIA);
        biblioteca.agregarPublicacion(revista);
        Audiolibro audiolibro =
                new Audiolibro("El Quijote", "Miguel de Cervantes", 1605, "Varios",36.5,  "Español", Audiolibro.FormatoAudio.MP3);
        biblioteca.agregarPublicacion(audiolibro);
        audiolibro =
                new Audiolibro(
                        "El Señor de los Anillos", "J.R.R. Tolkien", 1954,"Varios", 60.5,  "Inglés", Audiolibro.FormatoAudio.MP3);
        biblioteca.agregarPublicacion(audiolibro);
        audiolibro =
                new Audiolibro(
                        "El problema de los tres cuerpos", "Liu Cixin", 2008,"Varios", 30.5,  "Chino", Audiolibro.FormatoAudio.MP3);
        biblioteca.agregarPublicacion(audiolibro);
    }
}
