package com.red.lifka.sisfarmaapp.Cliente;

import android.location.Location;

import java.util.ArrayList;


public class Farmacia {
    private String CIF;
    private String nombre;
    private Location localizacion;
    ArrayList<ProductoFarmacia> ProductoFarmacia;

    public Farmacia(String CIF, String nombre, Location localizacion){
        this.CIF = CIF;
        this.nombre = nombre;
        this.localizacion = localizacion;
    }

    public String getNombre(){
        return nombre;
    }

    public String getCIF() {
        return CIF;
    }

    public Location getLocation(){
        return localizacion;
    }
}
