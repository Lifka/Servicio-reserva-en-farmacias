package com.red.lifka.sisfarmaapp.Cliente;

import android.renderscript.Sampler;

import java.util.ArrayList;
import java.util.HashMap;

public class Cesta {
    private static Cesta ourInstance = new Cesta();
    private HashMap<Integer, LineaCesta> cesta;
    private String CIF;

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
      if (!contain(pro.getId())) {
          cesta.put(pro.getId(), new LineaCesta(pro, cantidad));
      } else
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

    public Factura buy(TipoPago pago){
        Factura factura = new Factura(CIF,pago);

        for(LineaCesta linea : cesta.values()){
            factura.addProduct(linea.getProduct(),linea.getCantidad());
        }

        return factura;

    }

    public int size(){
        return cesta.size();
    }

    public void setFarmaciaCompra(String CIF){
        this.CIF = CIF;
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


    public ArrayList<LineaCesta> getListaLineas(){
        ArrayList<LineaCesta> lineas = new ArrayList();

        for (LineaCesta linea : cesta.values())
            lineas.add(linea);

        return lineas;
    }

    public String getCIF(){
        return CIF;
    }

    public boolean contain(int id){
        return cesta.containsKey(id);
    }

}
