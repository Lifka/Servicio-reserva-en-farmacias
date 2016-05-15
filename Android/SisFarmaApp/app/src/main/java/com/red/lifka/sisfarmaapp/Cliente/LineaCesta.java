package com.red.lifka.sisfarmaapp.Cliente;

public class LineaCesta {

    private ProductoGenerico producto;
    private int cantidad;

    LineaCesta(ProductoGenerico pro, int i){
        producto = pro;
        cantidad = i;
    }

    public int getCantidad(){
        return cantidad;
    }

    public void add(int i){
        cantidad += i;
    }

    public void substract(int i){
        cantidad -= i;
    }

    public ProductoGenerico getProduct(){
        return producto;
    }

    @Override
    public String toString(){
        return getCantidad() + "," + getProduct().toString();
    }


}
