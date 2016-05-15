package com.red.lifka.sisfarmaapp.Cliente;

import java.util.Date;

public class FactoriaProductosFarmacias implements FactoriaProducto{

    public FactoriaProductosFarmacias(){}

    @Override
    public ProductoGenerico factoriaProducto() {
        return new Producto();
    }

    public ProductoGenerico factoriaProducto(int id, String nombre, String descripcion, float precio, Date f_creacion,
                                             Date f_caducidad, Departamentos departamento, float porcentaje_iva) {
        return new Producto(id, nombre, descripcion, precio, f_creacion,
                f_caducidad, departamento, porcentaje_iva);
    }
}
