package com.bibliotecas.model;

import java.util.*;

public class Biblioteca {
    private final List<Publicacion> publicaciones;
    public Map<Integer, Usuario> usuarios; // Almacena los usuarios
    public Map<Integer, List<Publicacion>> prestamosActivos; // Registra préstamos activos
    private final Queue<PublicacionPrestable> colaPrestamos; // Cola de espera para publicaciones prestadas

    public Biblioteca() {
        this.publicaciones = new ArrayList<>();
        this.usuarios = new HashMap<>();
        this.prestamosActivos = new HashMap<>();
        this.colaPrestamos = new LinkedList<>();
    }

    /// Metodo para registrar un nuevo usuario
    public void registrarUsuario(int id, String nombre) {
        if (usuarios.containsKey(id)) {
            System.out.println("El usuario con ID " + id + " ya está registrado.");
            return;
        }
        Usuario nuevoUsuario = new Usuario(id, nombre);
        usuarios.put(id, nuevoUsuario);
        System.out.println("Usuario registrado con éxito.");
    }

    /// Metodo para obtener un usuario por ID
    public Usuario obtenerUsuario(int id) {
        return usuarios.get(id);
    }

    /// Metodo para agregar una publicación a la biblioteca
    public void agregarPublicacion(Publicacion publicacion) {
        publicaciones.add(publicacion);
    }

    /// Metodo para listar publicaciones en la biblioteca
    public void listarPublicaciones() {
        System.out.println("---- LISTADO DE PUBLICACIONES ----");

        System.out.println("\n-- LIBROS --");
        for (Publicacion pub : publicaciones) {
            if (pub instanceof Libro) {
                pub.mostrarInfo();
            }
        }

        System.out.println("\n-- REVISTAS --");
        for (Publicacion pub : publicaciones) {
            if (pub instanceof Revista) {
                pub.mostrarInfo();
            }
        }

        System.out.println("\n-- AUDIOLIBROS --");
        for (Publicacion pub : publicaciones) {
            if (pub instanceof Audiolibro) {
                pub.mostrarInfo();
            }
        }
    }

    /// Metodo para buscar una publicación por título
    public Publicacion buscarPublicacionPorTitulo(String titulo) {
        for (Publicacion p : publicaciones) {
            if (p.getTitulo().equalsIgnoreCase(titulo)) {
                return p;
            }
        }
        return null;
    }

    /// Metodo para registrar un préstamo
    public void registrarPrestamo(int idUsuario, PublicacionPrestable publicacion) {
        if (!usuarios.containsKey(idUsuario)) {
            System.out.println("El usuario no está registrado. Regístrelo antes de continuar.");
            System.out.print("\n");
            return;
        }
        Usuario usuario = usuarios.get(idUsuario);

        if (publicacion.isPrestado()) {
            System.out.println("La publicación ya está prestada. Se añadirá a la cola de espera.");
            System.out.print("\n");
            colaPrestamos.add(publicacion);
            return;
        }

        if (usuario.puedePedirPrestamo()) {
            usuario.prestarPublicacion(publicacion);
            prestamosActivos.putIfAbsent(idUsuario, new ArrayList<>());
            prestamosActivos.get(idUsuario).add(publicacion);
            System.out.println("Préstamo registrado con éxito.");
            System.out.print("\n");
        } else {
            System.out.println("El usuario ya tiene el máximo de préstamos permitidos.");
            System.out.print("\n");
        }
    }

    /// Metodo para registrar una devolución y gestionar la cola de espera
    public void registrarDevolucion(int idUsuario, PublicacionPrestable publicacion) {
        if (!usuarios.containsKey(idUsuario) || !prestamosActivos.containsKey(idUsuario)) {
            System.out.println("El usuario no tiene préstamos activos.");
            System.out.print("\n");
            return;
        }
        Usuario usuario = usuarios.get(idUsuario);
        usuario.devolverPublicacion(publicacion);
        prestamosActivos.get(idUsuario).remove(publicacion);

        // Verificar la cola de espera
        if (!colaPrestamos.isEmpty()) {
            System.out.println("La publicación estaba en espera para otro usuario.");
            PublicacionPrestable siguiente = colaPrestamos.poll();
            registrarPrestamo(idUsuario, siguiente);
        }

        System.out.println("Devolución registrada con éxito.");
        System.out.print("\n");

    }

    /// Metodo para mostrar los préstamos activos de un usuario
    public void mostrarPrestamosActivos(int idUsuario) {
        if (!prestamosActivos.containsKey(idUsuario)) {
            System.out.println("El usuario no tiene préstamos activos.");
            System.out.println("\n");
            return;
        }
        System.out.println("Préstamos de " + usuarios.get(idUsuario).getNombre() + ":");
        for (Publicacion p : prestamosActivos.get(idUsuario)) {
            System.out.println("- " + p);
        }
        System.out.println("\n");

    }

    /// Metodo para ordenar las publicaciones por autor
    public void ordenarPorAutor() {
        publicaciones.sort(new Publicacion.ComparadorPorAutor());
        System.out.println("Se han ordenado las publicaciones según su autor.");
        System.out.print("\n");

    }

    /// Metodo para ordenar las publicaciones por título
    public void ordenarPorTitulo() {
        publicaciones.sort(new Publicacion.ComparadorPorTitulo());
        System.out.println("Se han ordenado las publicaciones según su título.");
        System.out.print("\n");

    }
}