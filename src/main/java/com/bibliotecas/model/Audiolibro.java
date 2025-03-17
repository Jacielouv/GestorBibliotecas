package com.bibliotecas.model;

import com.bibliotecas.exceptions.InvalidDurationException;
import com.bibliotecas.exceptions.InvalidFormatException;
import com.bibliotecas.exceptions.InvalidLanguageException;
import com.bibliotecas.exceptions.InvalidNameException;

/**
 * Clase que representa un Audiolibro, una subclase de Publicacion que implementa la interfaz Multimedia.
 */
public class Audiolibro extends Publicacion implements Multimedia {

    // Atributos de la clase Audiolibro
    private String narrador; // Nombre del narrador del audiolibro
    private double duracion; // Duración del audiolibro en horas
    private String idioma; // Idioma en el que está narrado el audiolibro

    /**
     * Enum que define los formatos de audio disponibles.
     */
    public enum FormatoAudio {MP3, WAV, AAC, AA};
    private FormatoAudio formatoAudio; // Formato del audiolibro

    /**
     * Constructor de la clase Audiolibro.
     * @param titulo Título del audiolibro.
     * @param autor Autor del audiolibro.
     * @param anioPublicacion Año de publicación del audiolibro.
     * @param narrador Nombre del narrador del audiolibro.
     * @param duracion Duración del audiolibro en horas.
     * @param idioma Idioma del audiolibro.
     * @param formatoAudio Formato del audiolibro.
     */
    public Audiolibro(String titulo, String autor, int anioPublicacion, String narrador, double duracion, String idioma, FormatoAudio formatoAudio) {
        super(titulo,autor,anioPublicacion);
        setNarrador(narrador);
        setDuracion(duracion);
        setIdioma(idioma);
        setFormatoAudio(formatoAudio);
    }

    // Métodos getter para cada atributo
    public String getNarrador() {return narrador;}
    public double getDuracion() {return duracion;}
    public String getIdioma() {return idioma;}
    public FormatoAudio getFormatoAudio() {return formatoAudio;}

    // Métodos setter para cada atributo con validaciones
    public void setNarrador(String narrador) throws InvalidNameException {
        if (!validarNarrador(narrador)){throw new InvalidNameException("El nombre del narrador está vacío");}
        this.narrador = narrador;
    }

    public void setDuracion(double duracion) throws InvalidDurationException {
        if (!validarDuracion(duracion)) {throw new InvalidDurationException("La duración debe ser mayor que 0.");}
        this.duracion = duracion;
    }

    public void setIdioma(String idioma) throws InvalidLanguageException {
        if (!validarIdioma(idioma)) {throw new InvalidLanguageException("El idioma está vacío.");}
        this.idioma = idioma;
    }

    public void setFormatoAudio(FormatoAudio formatoAudio) throws InvalidFormatException {
        if (!validarFormato(formatoAudio)) {throw new InvalidFormatException("El formato no es válido.");}
        this.formatoAudio = formatoAudio;
    }

    /**
     * Valida que el nombre del narrador no esté vacío.
     * @param narrador Nombre del narrador.
     * @return true si el nombre es válido, false en caso contrario.
     */
    public static boolean validarNarrador(String narrador) {
        return narrador != null && !narrador.trim().isEmpty();
    }

    /**
     * Valida que la duración del audiolibro sea mayor que 0.
     * @param duracion Duración en horas.
     * @return true si la duración es válida, false en caso contrario.
     */
    public static boolean validarDuracion(double duracion) {
        return duracion > 0;
    }

    /**
     * Valida que el idioma no esté vacío.
     * @param idioma Idioma del audiolibro.
     * @return true si el idioma es válido, false en caso contrario.
     */
    public static boolean validarIdioma(String idioma) {
        return idioma != null && !idioma.trim().isEmpty();
    }

    /**
     * Valida que el formato de audio no sea nulo.
     * @param formatoAudio Formato del audiolibro.
     * @return true si el formato es válido, false en caso contrario.
     */
    public static boolean validarFormato(FormatoAudio formatoAudio) {
        return formatoAudio != null;
    }

    /**
     * Metodo para mostrar la información del audiolibro, sobreescribiendo el de la clase padre.
     */
    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Narrador: " + narrador);
        System.out.println("Duración: " + duracion);
        System.out.println("Idioma: " + idioma);
        System.out.println("Formato: " + formatoAudio+"\n");
    }

    /**
     * Implementación del metodo descargar de la interfaz Multimedia.
     */
    @Override
    public void descargar() {
        System.out.println("Descargando el audiolibro en formato " + formatoAudio + "...");
    }

    /**
     * Implementación del metodo obtenerFormato de la interfaz Multimedia.
     * @return El formato del audiolibro en forma de cadena de texto.
     */
    @Override
    public String obtenerFormato() {
        return formatoAudio.name();
    }
}
