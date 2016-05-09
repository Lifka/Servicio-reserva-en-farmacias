package com.red.lifka.sisfarmaapp.Cliente;

public class LineaCesta {

    private Producto producto;
    private int cantidad;
    String cif;

    LineaCesta(Producto pro, int i, String cif){
        producto = pro;
        cantidad = i;
        this.cif = cif;
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

    public String getCif(){
        return cif;
    }

}
