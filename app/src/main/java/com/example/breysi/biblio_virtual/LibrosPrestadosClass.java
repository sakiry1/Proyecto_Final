package com.example.breysi.biblio_virtual;

import java.util.Date;

public class LibrosPrestadosClass {
    private String fechaPrestamo;
    private String  fechaDevolucion ;
    private Libro idlibro;
    private Usuario  idUsuario;

    public LibrosPrestadosClass() {
    }

    public LibrosPrestadosClass(String fechaPrestamo, String fechaDevolucion, Libro idlibro, Usuario idUsuario) {
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.idlibro = idlibro;
        this.idUsuario = idUsuario;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(String fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Libro getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(Libro idlibro) {
        this.idlibro = idlibro;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }
}
