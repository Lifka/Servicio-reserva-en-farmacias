package com.red.lifka.sisfarmaapp.Cliente;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private float precio;
    private Date f_creacion;
    private Date f_caducidad;
    private Departamentos departamento;
    private float porcentaje_iva;

    public Producto(){}

    public Producto(int id, String nombre, String descripcion, float precio, Date f_creacion,
                    Date f_caducidad, Departamentos departamento, float porcentaje_iva){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.f_creacion =  f_creacion;
        this.f_caducidad = f_caducidad;
        this.porcentaje_iva = porcentaje_iva;
        this.departamento = departamento;
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

    public float getPorcentajeIva() {
        return porcentaje_iva;
    }

    public int getId() {
        return id;
    }

    public Date getF_creacion() {
        return f_creacion;
    }

    public Date getF_caducidad() {
        return f_caducidad;
    }

    public Departamentos getDepartamento(){
        return departamento;
    }

}
