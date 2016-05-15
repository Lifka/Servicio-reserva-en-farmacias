package com.red.lifka.sisfarmaapp.Cliente;

import java.util.Date;

public abstract class ProductoGenerico {

    int id;
    String nombre;
    String descripcion;
    float precio;
    Date f_creacion;
    Date f_caducidad;
    Departamentos departamento;
    float porcentaje_iva;

    public int getId(){
        return id;
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

    public Date getF_creacion() {
        return f_creacion;
    }

    public Date getF_caducidad() {
        return f_caducidad;
    }

    public Departamentos getDepartamento(){
        return departamento;
    }

    @Override
    public String toString(){
        return getNombre() + ","
                + getPrecio() + ","
                + getPrecio() * getPorcentajeIva() + ","
                + getDescripcion() + ","
                + getId();
    }



}
