package com.bibliotecas.model;
import java.util.*;


public class Usuario {
    private int idUsuario;
    private String nombre;
    private List<PublicacionPrestable> publicacionesUsuario;
    private static final int LIMITE_PRESTAMOS = 5;

    public Usuario(int idUsuario, String nombre) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.publicacionesUsuario = new ArrayList<>(); // Inicializar lista para evitar NullPointerException
    }

    public String getNombre() {
        return nombre;
    }

    public boolean puedePedirPrestamo() {
        return publicacionesUsuario.size() < LIMITE_PRESTAMOS;
    }

    public void prestarPublicacion(PublicacionPrestable publicacion) {
        if (publicacionesUsuario.size() >= LIMITE_PRESTAMOS) {
            System.out.println("El usuario ha alcanzado el límite de préstamos (5).");
            return;
        }
        if (publicacion.isPrestado()) {
            System.out.println("La publicación ya está prestada.");
            return;
        }
        publicacion.prestar();
        publicacionesUsuario.add(publicacion);
        System.out.println("Préstamo registrado para: " + nombre);
    }

    public void devolverPublicacion(PublicacionPrestable publicacion) {
        if (!publicacion.isPrestado()) {
            System.out.println("La publicación no está prestada.");
            return;
        }
        publicacionesUsuario.remove(publicacion);
        publicacion.devolver();
        System.out.println("Devolución registrada para: " + nombre);
    }
}
