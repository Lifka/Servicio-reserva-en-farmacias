package com.red.lifka.sisfarmaapp.Cliente;

import java.util.ArrayList;


public class Historial {
    private static Historial ourInstance = new Historial();
    private ArrayList<Producto> historial;

    public static Historial getInstance() {
        return ourInstance;
    }

    private Historial() {
        historial = new ArrayList();
    }

    public void clear(){
        ourInstance = new Historial();
    }

    public void addToHistory(Cesta cesta){
        for(int i = 0; i < cesta.size(); i++){
            historial.add(cesta.getProduct(i));
        }
    }
}
