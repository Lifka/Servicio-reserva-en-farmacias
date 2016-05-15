package com.red.lifka.sisfarmaapp.Cliente;

public class LineaFactura {
    private int id;
    private String nombre;
    private int cantidad;
    private float precio;
    private float precio_iva;

    LineaFactura(Producto pro, int cantidad){
        this.id = pro.getId();
        this.nombre = pro.getNombre();
        this.cantidad = cantidad;
        this.precio = pro.getPrecio() * cantidad;
        this.precio_iva = pro.getPrecio() * cantidad * pro.getPorcentajeIva();
    }

    LineaFactura(int id, String nombre, int cantidad, float precio, float precio_iva, float porcentaje_iva){
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio * cantidad;
        this.precio_iva = precio_iva * porcentaje_iva * cantidad;
    }

    public int getCantidad(){
        return cantidad;
    }

    public int getId(){
        return id;
    }

    public float getPrecio(){
        return precio;
    }

    public float getPrecioIva(){
        return precio_iva;
    }

    public String getNombre(){
        return nombre;
    }

    @Override
    public String toString(){
        return getNombre() + "," + getCantidad() + "," + getPrecioIva() + "," + getId();
    }

}
