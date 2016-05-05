package com.red.lifka.sisfarmaapp.Cliente;

import java.util.ArrayList;

public class Cesta {
    private static Cesta ourInstance = new Cesta();
    private ArrayList<Producto> cesta;
    private Factura factura;

    public static Cesta getInstance() {
        return ourInstance;
    }

    private Cesta() {
        cesta = new ArrayList();
        factura = new Factura();
    }

    public void clear(){
        ourInstance = new Cesta();
    }

    public void add(Producto pro){
        cesta.add(pro);
        factura.addProduct(pro);
    }

    public void remove(Producto pro){
        cesta.remove(pro);
        factura.deleteProduct(pro);
    }

}
