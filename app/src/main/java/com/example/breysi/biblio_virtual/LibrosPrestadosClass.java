package com.example.breysi.biblio_virtual;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class LibrosPrestadosClass implements Parcelable {

    private String fecha_prestamo;
    private String fecha_devolución;
    private String id_libro;
    private String id_usuario;
    private int id;

    public LibrosPrestadosClass() {
    }

 /*   public LibrosPrestadosClass(Date fechaPrestamo, Date fechaDevolucion, Libro idlibro, Usuario idUsuario) {
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.idlibro = idlibro;
        this.idUsuario = idUsuario;
    }
*/
    private LibrosPrestadosClass(Parcel in) {
        this.fecha_prestamo = /*new Date*/(in.readString());
        this.fecha_devolución =/*new Date*/(in.readString());
        this.id_libro = (in.readString());
        this.id_usuario=(in.readString());
       // this.id_libro = in.readParcelable(Libro.class.getClassLoader());
        //this.id_usuario = in.readParcelable(Usuario.class.getClassLoader());

    }

    public String getFecha_prestamo() {
        return fecha_prestamo;
    }

    public void setFecha_prestamo(String fechaPrestamo) {
        this.fecha_prestamo = fechaPrestamo;
    }

    public String getFecha_devolución() {
        return fecha_devolución;
    }

    public void setFecha_devolución(String fecha_devolución) {
        this.fecha_devolución = fecha_devolución;
    }

    public String getId_libro() {
        return id_libro;
    }

    public void setId_libro(String id_libro) {
        this.id_libro = id_libro;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(String id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static final Creator<LibrosPrestadosClass> CREATOR = new Creator<LibrosPrestadosClass>() {
        @Override
        public LibrosPrestadosClass createFromParcel(Parcel in) {
            return new LibrosPrestadosClass(in);
        }

        @Override
        public LibrosPrestadosClass[] newArray(int size) {
            return new LibrosPrestadosClass[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
       // dest.writeLong(fechaPrestamo.getTime());
       // dest.writeLong(fechaDevolucion.getTime());
        dest.writeString(fecha_prestamo);
        dest.writeString(fecha_devolución);
        dest.writeString(id_libro);
        dest.writeString(id_usuario);
    }


}
