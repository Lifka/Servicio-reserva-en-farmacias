package com.red.lifka.sisfarmaapp.Cliente;

import java.util.Date;

public abstract class ProductoGenerico {

    int id;
    String nombre;
    String descripcion;
    float precio;
    Long f_creacion;
    Long f_caducidad;
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

    public Long getF_creacion() {
        return f_creacion;
    }

    public Long getF_caducidad() {
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
