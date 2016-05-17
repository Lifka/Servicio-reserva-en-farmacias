package com.red.lifka.sisfarmaapp.Cliente;

import java.util.Date;

public class Producto extends ProductoGenerico {

    public Producto(){}

    public Producto(int id, String nombre, String descripcion, float precio, Long f_creacion,
                    Long f_caducidad, Departamentos departamento, float porcentaje_iva){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.f_creacion =  f_creacion;
        this.f_caducidad = f_caducidad;
        this.porcentaje_iva = porcentaje_iva;
        this.departamento = departamento;
    }


}
