package com.bibliotecas.model;

import com.bibliotecas.exceptions.InvalidNameException;
import com.bibliotecas.exceptions.InvalidTitleException;
import com.bibliotecas.exceptions.InvalidYearException;
import java.time.LocalDate;

import java.util.*;

public abstract class Publicacion implements Comparable<Publicacion> {
    public static Comparator<? super Publicacion> ComparadorPorAutor;
    // Atributos de la clase
    private String titulo;
    private String autor;
    private int anioPublicacion;

    // Identificador de publicaciones
    private static int contador = 1;
    private int uuid;

    /**
     * Constructor de la clase Publicacion.
     * Inicializa los atributos de la publicación validando los valores proporcionados.
     *
     * @param titulo          El título de la publicación.
     * @param autor           El autor de la publicación.
     * @param anioPublicacion El año de publicación.
     * @throws InvalidTitleException Si el título no es válido.
     * @throws InvalidNameException  Si el nombre del autor no es válido.
     * @throws InvalidYearException  Si el año de publicación no es válido.
     */
    public Publicacion(String titulo, String autor, int anioPublicacion) {
        setTitulo(titulo);
        setAutor(autor);
        setAnioPublicacion(anioPublicacion);

        this.uuid = contador++;
    }

    /**
     * Metodo mostrarInfo
     * Muestra la información de la publicación (título, autor y año de publicación).
     */
    public void mostrarInfo() {
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + autor);
        System.out.println("Año de publicación: " + anioPublicacion);
    }

    /**
     * Metodo validarTitulo
     * Verifica que un título no sea nulo o vacío.
     *
     * @param titulo El título que se quiere validar.
     * @return Verdadero si el título es válido, falso en caso contrario.
     */
    public static boolean validarTitulo(String titulo) {
        return titulo != null && !titulo.trim().isEmpty();
    }

    /**
     * Metodo validarAutor
     * Verifica que un autor no sea nulo o vacío.
     *
     * @param autor El autor que se quiere validar.
     * @return Verdadero si el autor es válido, falso en caso contrario.
     * @throws InvalidNameException Si el nombre del autor no es válido.
     */
    public static boolean validarAutor(String autor) throws InvalidNameException {
        return autor != null && !autor.trim().isEmpty();
    }

    /**
     * Metodo validarAnio
     * Verifica que el año sea mayor a 0 y no supere el año actual.
     *
     * @param anio El año que se quiere validar.
     * @return Verdadero si el año es válido, falso en caso contrario.
     */
    public static boolean validarAnio(int anio) {
        int anioActual = LocalDate.now().getYear();
        return anio > 0 && anio <= anioActual;
    }

    // Getters y Setters

    /**
     * Obtiene el título de la publicación.
     *
     * @return El título de la publicación.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtiene el autor de la publicación.
     *
     * @return El autor de la publicación.
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Obtiene el año de publicación.
     *
     * @return El año de publicación.
     */
    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    /**
     * Establece el título de la publicación.
     *
     * @param titulo El título de la publicación.
     * @throws InvalidTitleException Si el título no es válido.
     */
    public void setTitulo(String titulo) throws InvalidTitleException {
        if (!validarTitulo(titulo)) {
            throw new InvalidTitleException("El título está vacío");
        }
        this.titulo = titulo;
    }

    /**
     * Establece el autor de la publicación.
     *
     * @param autor El autor de la publicación.
     * @throws InvalidNameException Si el nombre del autor no es válido.
     */
    public void setAutor(String autor) throws InvalidNameException {
        if (!validarAutor(autor)) {
            throw new InvalidNameException("El nombre del autor está vacío");
        }
        this.autor = autor;
    }

    /**
     * Establece el año de publicación.
     *
     * @param anioPublicacion El año de publicación.
     * @throws InvalidYearException Si el año de publicación no es válido.
     */
    public void setAnioPublicacion(int anioPublicacion) throws InvalidYearException {
        if (!validarAnio(anioPublicacion)) {
            throw new InvalidYearException("El año de publicación no puede ser mayor al año actual.");
        }
        this.anioPublicacion = anioPublicacion;
    }


    @Override
    public int compareTo(Publicacion o) {
        return 0;
    }

    public static class ComparadorPorAutor implements Comparator<Publicacion> {
        @Override
        public int compare(Publicacion p1, Publicacion p2) {
            return p1.autor.compareTo(p2.autor);
        }
    }

    public static class ComparadorPorTitulo implements Comparator<Publicacion> {
        @Override
        public int compare(Publicacion p1, Publicacion p2) {
            return p1.titulo.compareTo(p2.titulo);
        }
    }

}