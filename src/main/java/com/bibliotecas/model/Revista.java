package com.bibliotecas.model;

import com.bibliotecas.exceptions.InvalidCategoryException;
import com.bibliotecas.exceptions.InvalidEditionException;
import com.bibliotecas.exceptions.InvalidISSNException;
import com.bibliotecas.exceptions.InvalidMonthException;

import java.time.LocalDate;

/**
 * Clase que representa una Revista.
 * Hereda de la clase Publicacion e incluye atributos específicos de revistas.
 */
public class Revista extends PublicacionPrestable implements Prestable {

    // Atributos de la clase Revista
    private String ISSN; // Código ISSN de la revista (8 dígitos numéricos)
    private int numEdicion; // Número de edición de la revista


    /**
     * Enum para representar los meses del año.
     */
    public enum Mes {
        ENERO, FEBRERO, MARZO, ABRIL, MAYO, JUNIO,
        JULIO, AGOSTO, SEPTIEMBRE, OCTUBRE, NOVIEMBRE, DICIEMBRE
    }
    private Mes mesPublicacion; // Mes en que se publica la revista

    /**
     * Enum para representar las categorías de revistas.
     */
    public enum CategoriaRevista {
        DIARIO, PRENSA_ROSA, MOTOR, ECONOMIA,
        TECNOLOGIA, VIDEOJUEGOS, DEPORTES,CIENCIA
    }
    private CategoriaRevista categoria; // Categoría de la revista

    /**
     * Constructor de la clase Revista.
     * @param titulo Título de la revista
     * @param autor Autor de la revista
     * @param anioPublicacion Año de publicación de la revista
     * @param ISSN Código ISSN de la revista
     * @param numEdicion Número de edición de la revista
     * @param mesPublicacion Mes en que se publica la revista
     * @param categoria Categoría de la revista
     */
    public Revista(String titulo, String autor, int anioPublicacion, String ISSN, int numEdicion, Mes mesPublicacion, CategoriaRevista categoria) {
        super(titulo, autor, anioPublicacion);
        setISSN(ISSN);
        setNumEdicion(numEdicion);
        setMesPublicacion(mesPublicacion);
        setCategoria(categoria);
    }

    // Métodos getter para cada atributo
    public String getISSN() { return ISSN; }
    public int getNumEdicion() { return numEdicion; }
    public Mes getMesPublicacion() { return mesPublicacion; }
    public CategoriaRevista getCategoria() { return categoria; }

    /**
     * Establece el ISSN de la revista con validación.
     * @param ISSN Código ISSN
     * @throws InvalidISSNException si el ISSN no es válido
     */
    public void setISSN(String ISSN) {
        if (!validarISSN(ISSN)) {
            throw new InvalidISSNException("El ISSN no es válido. Debe tener 8 dígitos.");
        }
        this.ISSN = ISSN;
    }

    /**
     * Establece el número de edición con validación.
     * @param numEdicion Número de edición
     * @throws InvalidEditionException si el número de edición no es válido
     */
    public void setNumEdicion(int numEdicion) {
        if (!validarNumEdicion(numEdicion)) {
            throw new InvalidEditionException("El número de edición debe ser positivo.");
        }
        this.numEdicion = numEdicion;
    }

    /**
     * Establece el mes de publicación.
     * @param mesPublicacion Mes de publicación
     * @throws InvalidMonthException si el mes de publicación es nulo
     */
    public void setMesPublicacion(Mes mesPublicacion) {
        if (mesPublicacion == null) {
            throw new InvalidMonthException("El mes de publicación no puede ser nulo.");
        }
        this.mesPublicacion = mesPublicacion;
    }

    /**
     * Establece la categoría de la revista.
     * @param categoria Categoría de la revista
     * @throws InvalidCategoryException si la categoría es nula
     */
    public void setCategoria(CategoriaRevista categoria) {
        if (categoria == null) {
            throw new InvalidCategoryException("La categoría no puede ser nula.");
        }
        this.categoria = categoria;
    }

    /**
     * Muestra la información de la revista, sobrescribiendo el metodo de la clase padre.
     */
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("ISSN: " + ISSN);
        System.out.println("Número de edición: " + numEdicion);
        System.out.println("Mes de publicación: " + mesPublicacion);
        System.out.println("Categoría: " + categoria+"\n");
    }

    /**
     * Valida que el ISSN tenga exactamente 8 dígitos numéricos.
     * @param ISSN Código ISSN a validar
     * @return true si es válido, false en caso contrario
     */
    public static boolean validarISSN(String ISSN) {
        return ISSN != null && ISSN.matches("\\d{4}-\\d{4}");
    }

    /**
     * Valida que el número de edición sea mayor que 0.
     * @param numEdicion Número de edición a validar
     * @return true si es válido, false en caso contrario
     */
    public static boolean validarNumEdicion(int numEdicion) {
        return numEdicion > 0;
    }


    /**
     * Presta la revista si no está prestada y asigna una fecha de devolución de 7 días.
     */
    @Override
    public void prestar() {
        if (prestado) {
            System.out.println("La revista ya está prestado.");
        } else {
            prestado = true;
            fechaPrestamo = LocalDate.now();
            fechaDevolucion = fechaPrestamo.plusDays(7); // 7 días después
            System.out.println("Revista prestado. Fecha de devolución: " + fechaDevolucion);
        }
    }

    /**
     * Devuelve la revista si está prestada y restablece las fechas de préstamo.
     */
    @Override
    public void devolver() {
        if (!prestado) {
            System.out.println("La revista no está prestado.");
        } else {
            LocalDate hoy = LocalDate.now();
            if (hoy.isAfter(fechaDevolucion)){System.out.print("AVISO: Se ha sobrepasado la fecha de devolución.");};
            prestado = false;
            fechaPrestamo = null;
            fechaDevolucion = null;
            System.out.println("Revista devuelta.");
        }
    }
}