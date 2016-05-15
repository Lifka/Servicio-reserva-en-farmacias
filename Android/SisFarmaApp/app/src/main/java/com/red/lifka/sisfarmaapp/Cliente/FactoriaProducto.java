package com.red.lifka.sisfarmaapp.Cliente;


import java.util.Date;

public interface FactoriaProducto {
    ProductoGenerico factoriaProducto();

    public ProductoGenerico factoriaProducto(int id, String nombre, String descripcion, float precio, Date f_creacion,
                                             Date f_caducidad, Departamentos departamento, float porcentaje_iva);
}
