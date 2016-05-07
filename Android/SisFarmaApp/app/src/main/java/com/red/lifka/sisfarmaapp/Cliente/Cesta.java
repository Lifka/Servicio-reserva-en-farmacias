package com.red.lifka.sisfarmaapp.Cliente;

import java.util.ArrayList;
import java.util.HashMap;

public class Cesta {
    private static Cesta ourInstance = new Cesta();
    private HashMap<Integer, LineaCesta> cesta;

    public static Cesta getInstance() {
        return ourInstance;
    }

    private Cesta() {
        cesta = new HashMap();
    }

    public void clear(){
        ourInstance = new Cesta();
    }

    public void add(Producto pro, int cantidad){
        if (cesta.get(pro.getId()) != null)
            cesta.put(pro.getId(), new LineaCesta(pro, cantidad));
        else
            cesta.get(pro.getId()).add(cantidad);
    }

    public void remove(Producto pro, int cantidad) {
        if (cesta.get(pro.getId()) != null && cesta.get(pro.getId()).getCantidad() >= cantidad)
            cesta.get(pro.getId()).substract(cantidad);
        else
            cesta.remove(pro);
    }

    public void remove(Producto pro){
        cesta.remove(pro);
    }

    public Factura buy(){
        Factura factura = new Factura();

        for(int i = 0; i < cesta.size(); i++){
            factura.addProduct(cesta.get(i).getProduct(), cesta.get(i).getCantidad());
        }

        return factura;

    }

    public int size(){
        return cesta.size();
    }

    public Producto getProduct(int i){
        return cesta.get(i).getProduct();
    }

}
