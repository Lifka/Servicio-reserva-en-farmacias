package com.red.lifka.sisfarmaapp.Cliente;

import android.location.Location;

import java.util.ArrayList;

public class Usuario {
    private String email;
    private String nombre_completo;
    private String dni;
    Cesta cesta;
    Historial historial;
    Location lastKnownLocation;
    TipoPago pagoPreferido;
    ArrayList<Farmacia> contactos;

    Usuario (String email, String nombre_completo, String dni){
        this.email = email;
        this.nombre_completo = nombre_completo;
        this.dni = dni;
        cesta = Cesta.getInstance();
        historial = Historial.getInstance();
        contactos = new ArrayList();
    }

    public String getEmail(){
        return email;
    }

    public String getNombre(){
        return nombre_completo;
    }

    public String getDni(){
        return dni;
    }

    public TipoPago getPagoPreferencia(){
        return pagoPreferido;
    }

    public void buyBasket(){
        historial.addToHistory(cesta);
        cesta.buy();
        cesta.clear();
    }

    public void setPagoPreferencia(TipoPago pago){
        pagoPreferido = pago;
    }





}
