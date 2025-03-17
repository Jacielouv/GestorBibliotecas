package com.bibliotecas.model;

import java.time.LocalDate;
import com.bibliotecas.exceptions.InvalidTitleException;
import com.bibliotecas.exceptions.InvalidNameException;
import com.bibliotecas.exceptions.InvalidYearException;


public abstract class PublicacionPrestable extends Publicacion implements Prestable {
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

    boolean prestado;
    LocalDate fechaPrestamo;
    LocalDate fechaDevolucion;

    public PublicacionPrestable(String titulo, String autor, int anioPublicacion) {
        super(titulo, autor, anioPublicacion);
    }


    /**
     * Verifica si la publicación está prestada.
     * @return true si la publicación está prestada, false en caso contrario.
     */
    @Override
    public boolean isPrestado() {
        return this.prestado;
    }
}
