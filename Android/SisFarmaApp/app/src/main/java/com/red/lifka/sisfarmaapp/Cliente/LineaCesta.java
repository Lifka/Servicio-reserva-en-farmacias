package com.red.lifka.sisfarmaapp.Cliente;

public class LineaCesta {

    private Producto producto;
    private int cantidad;

    LineaCesta(Producto pro, int i){
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

    public Producto getProduct(){
        return producto;
    }

    @Override
    public String toString(){
        return getCantidad() + "," + getProduct().toString();
    }


}
