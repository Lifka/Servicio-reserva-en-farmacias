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

    public void add(Producto pro, int cantidad, String cif){
      if (!cesta.containsValue(pro.getId()))
            cesta.put(pro.getId(), new LineaCesta(pro, cantidad, cif));
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

    public Factura buy(String CIF){
        Factura factura = new Factura(CIF);

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

    public ArrayList<Integer> getListaID(){
        ArrayList<Integer> ids = new ArrayList();


        for (int id: cesta.keySet())
            ids.add(id);


        return ids;
    }

}
