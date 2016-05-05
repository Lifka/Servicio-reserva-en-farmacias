package com.red.lifka.sisfarmaapp.Cliente;

import java.util.ArrayList;

public class Factura {

    float total;
    private ArrayList<String> productos;

    Factura(){
        total = 0f;
        productos = new ArrayList();
    }

    public void addProduct(Producto pro){
        total += pro.getPrecio();
        productos.add(pro.getNombre());
    }

    public void deleteProduct(Producto pro){
        total -= pro.getPrecio();
        productos.remove(pro.getNombre());
    }

    public float getTotal(){
        return total;
    }

    public ArrayList<String> getProductos(){
        return productos;
    }
}
