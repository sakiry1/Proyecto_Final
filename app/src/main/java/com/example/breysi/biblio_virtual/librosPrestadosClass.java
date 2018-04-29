package com.example.breysi.biblio_virtual;

import java.util.Date;

public class librosPrestadosClass {
    private Date fechaPrestamo;
    private Date  fechaDevolucion ;
    private Libro idlibro;
    private Usuario  idUsuario;

    public librosPrestadosClass() {
    }

    public librosPrestadosClass(Date fechaPrestamo, Date  fechaDevolucion, Libro idlibro, Usuario idUsuario) {
        this.fechaDevolucion = fechaDevolucion;
        this.fechaPrestamo = fechaPrestamo;
        this.idlibro = idlibro;
        this.idUsuario = idUsuario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
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
