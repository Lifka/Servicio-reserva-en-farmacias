package com.red.lifka.sisfarmaapp.Cliente;


import java.util.Date;

public interface FactoriaProducto {
    ProductoGenerico factoriaProducto();

    public ProductoGenerico factoriaProducto(int id, String nombre, String descripcion, float precio, Long f_creacion,
                                             Long f_caducidad, Departamentos departamento, float porcentaje_iva);
}
