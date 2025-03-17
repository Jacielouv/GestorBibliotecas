package com.bibliotecas.model;

import java.time.LocalDate;
import com.bibliotecas.exceptions.InvalidIsbnException;
import com.bibliotecas.exceptions.InvalidNumPagException;

/**
 * Clase que representa un libro en la biblioteca.
 * Hereda de la clase Publicacion e implementa la interfaz Prestable.
 */
public class Libro extends PublicacionPrestable implements Prestable {

    // Atributos de la clase Libro
    private String ISBN;            // Número ISBN que identifica el libro
    private int numPaginas;         // Número de páginas del libro
    private boolean prestado;       // Indica si el libro está prestado o no
    private LocalDate fechaPrestamo; // Fecha en la que el libro fue prestado
    private LocalDate fechaDevolucion; // Fecha de devolución del libro

    /**
     * Constructor de la clase Libro
     *
     * @param titulo          Título del libro
     * @param autor           Autor del libro
     * @param ISBN            Número ISBN del libro
     * @param anioPublicacion Año de publicación del libro
     * @param numPaginas      Número de páginas del libro
     */
    public Libro(String titulo, String autor, String ISBN, int anioPublicacion, int numPaginas) {
        super(titulo, autor, anioPublicacion);
        setISBN(ISBN);
        setNumPaginas(numPaginas);
        this.prestado = false;
        this.fechaPrestamo = null;
        this.fechaDevolucion = null;
    }

    /**
     * Obtiene el ISBN del libro
     *
     * @return ISBN del libro
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * Obtiene el número de páginas del libro
     *
     * @return Número de páginas
     */
    public int getNumPaginas() {
        return numPaginas;
    }

    /**
     * Obtiene la fecha de préstamo del libro
     *
     * @return Fecha de préstamo
     */
    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    /**
     * Obtiene la fecha de devolución del libro
     *
     * @return Fecha de devolución
     */
    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * Establece el ISBN del libro
     *
     * @param ISBN Número ISBN
     * @throws InvalidIsbnException si el ISBN es inválido
     */
    public void setISBN(String ISBN) throws InvalidIsbnException {
        if (!validarISBN(ISBN)) {
            throw new InvalidIsbnException("El ISBN no puede estar vacío.");
        }
        this.ISBN = ISBN;
    }

    /**
     * Establece el número de páginas del libro
     *
     * @param numPaginas Número de páginas
     * @throws InvalidNumPagException si el número de páginas es menor o igual a 0
     */
    public void setNumPaginas(int numPaginas) throws InvalidNumPagException {
        if (!validarNumPaginas(numPaginas)) {
            throw new InvalidNumPagException("El número de páginas debe ser mayor que 0.");
        }
        this.numPaginas = numPaginas;
    }

    /**
     * Valida que el ISBN no sea nulo o vacío
     *
     * @param ISBN Número ISBN a validar
     * @return true si el ISBN es válido, false en caso contrario
     */
    public static boolean validarISBN(String ISBN) {
        return ISBN != null && !ISBN.trim().isEmpty();
    }

    /**
     * Valida que el número de páginas sea mayor que 0
     *
     * @param numPaginas Número de páginas a validar
     * @return true si es mayor que 0, false en caso contrario
     */
    public static boolean validarNumPaginas(int numPaginas) {
        return numPaginas > 0;
    }

    /**
     * Muestra la información del libro
     * Sobreescribe el metodo de la clase padre
     */
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("ISBN: " + ISBN);
        System.out.println("Número de páginas: " + numPaginas+"\n");
    }

    /**
     * Presta el libro si no está prestado y asigna una fecha de devolución de 21 días.
     */
    @Override
    public void prestar() {
        if (prestado) {
            System.out.println("El libro ya está prestado.");
        } else {
            prestado = true;
            fechaPrestamo = LocalDate.now();
            fechaDevolucion = fechaPrestamo.plusDays(21); // 21 días después
            System.out.println("Libro prestado. Fecha de devolución: " + fechaDevolucion);
        }
    }

    /**
     * Devuelve el libro si está prestado y restablece las fechas de préstamo.
     */
    @Override
    public void devolver() {
        if (!prestado) {
            System.out.println("El libro no está prestado.");
        } else {
            LocalDate hoy = LocalDate.now();
            if (hoy.isAfter(fechaDevolucion)){System.out.print("AVISO: Se ha sobrepasado la fecha de devolución.");};
            prestado = false;
            fechaPrestamo = null;
            fechaDevolucion = null;
            System.out.println("Libro devuelto.");
        }
    }
}

