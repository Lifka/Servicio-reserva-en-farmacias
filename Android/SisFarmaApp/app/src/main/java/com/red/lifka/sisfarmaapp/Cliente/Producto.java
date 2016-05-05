package com.red.lifka.sisfarmaapp.Cliente;


import java.util.Calendar;
import java.util.GregorianCalendar;

public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private float precio;
    private Calendar f_creacion;
    private Calendar f_caducidad;

    public Producto(){}

    public Producto(int id, String nombre, String descripcion, float precio, Calendar f_caducidad){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.f_creacion =  new GregorianCalendar();
        this.f_caducidad = f_caducidad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public int getId() {
        return id;
    }

    public Calendar getF_creacion() {
        return f_creacion;
    }

    public Calendar getF_caducidad() {
        return f_caducidad;
    }

}
