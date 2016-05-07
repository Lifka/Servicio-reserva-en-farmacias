package com.red.lifka.sisfarmaapp.Cliente;

public class LineaFactura {
    private Producto producto;
    private int cantidad;
    private float precio;
    private float precio_iva;

    LineaFactura(Producto pro, int cantidad){
        producto = pro;
        this.cantidad = cantidad;
        precio = pro.getPrecio() * cantidad;
        precio_iva = pro.getPrecio() * cantidad * pro.getPorcentajeIva();
    }

    public int getCantidad(){
        return cantidad;
    }

    public Producto getProducto(){
        return producto;
    }

    public float getPrecio(){
        return precio;
    }

    public float getPrecioIva(){
        return precio_iva;
    }

}
