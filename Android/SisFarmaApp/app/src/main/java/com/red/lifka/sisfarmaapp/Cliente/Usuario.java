package com.red.lifka.sisfarmaapp.Cliente;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.red.lifka.sisfarmaapp.DB.DBQueries;
import com.red.lifka.sisfarmaapp.DB.JSONParser;

import java.util.ArrayList;
import java.util.HashMap;

public class Usuario {
    private String email;
    private String nombre_completo;
    private String dni;
    private Cesta cesta;
    private ArrayList<Integer> historial;
    private TipoPago pagoPreferido;
    private DBQueries dbquerys;
    private ArrayList<String> contactos;
    private Context context;

    private Location lastKnownLocation;

    public Usuario (String email, String nombre_completo, String dni, TipoPago pago, Context c) {
        context = c;
        dbquerys = new DBQueries(c);
        this.email = email;
        this.nombre_completo = nombre_completo;
        this.dni = dni;
        cesta = Cesta.getInstance();
        pagoPreferido = pago;
        loadHistorial();
        loadContactos();
    }

    public void setLocation(float lat, float lon){
        lastKnownLocation = new Location(new String());
        lastKnownLocation.setLatitude(lat);
        lastKnownLocation.setLongitude(lon);
    }

    public Location getLastKnownLocation(){
        return lastKnownLocation;
    }

    public void loadHistorial() {
        try {
            historial = dbquerys.getHistorial();
        } catch (Exception e){
            Log.e("Error historial", "Imposible recuperar el historial " + e.getMessage());
            historial = new ArrayList();
        }
    }

    public void loadContactos() {
        try {
            contactos = dbquerys.getCotactos();
        } catch (Exception e){
            Log.e("Error contactos", "Imposible recuperar los contactos " + e.getMessage());
            contactos = new ArrayList();
        }
    }

    public void addToContactos(ArrayList<Farmacia> contact_array){
        for(int i = 0; i < contact_array.size(); i++){
            addToContactos(contact_array.get(i).getCIF());
        }
    }

    public void addToContactos(Farmacia farmacia){
        String cif = farmacia.getCIF();
        addToContactos(cif);
    }

    public void addToContactos(String cif){
        try {
            dbquerys.putToContactos(cif);
        } catch (Exception e){
            Log.e("Error historial", "Imposible almacenar producto " + cif + " --> " + e.getMessage());
        }
    }

    public ArrayList<String> getContactos(){
        return  contactos;
    }


    public boolean setPagoPreferencia(TipoPago pago_nuevo, String pass){
        JSONParser jsonParser = new JSONParser(context);
        boolean correcto = false;

        try {
            correcto = jsonParser.updateUser(email, pass, this.nombre_completo, pago_nuevo);
        } catch (Exception e){
            Log.e("Error updateUser", e.getMessage());
        }

        if (correcto)
            pagoPreferido = pago_nuevo;

        return correcto;
    }

    public boolean setNombreCompleto(String nombre_completo_nuevo, String pass){
        JSONParser jsonParser = new JSONParser(context);
        boolean correcto = false;

        try {
            correcto = jsonParser.updateUser(email, pass, nombre_completo_nuevo, pagoPreferido);
        } catch (Exception e){
            Log.e("Error updateUser", e.getMessage());
        }

        if (correcto)
            nombre_completo = nombre_completo_nuevo;

        return correcto;
    }

    public boolean setNombreCompleto(String pass){
        JSONParser jsonParser = new JSONParser(context);
        boolean correcto = false;

        try {
            correcto = jsonParser.updateUser(email, pass, nombre_completo, pagoPreferido);
        } catch (Exception e){
            Log.e("Error updateUser", e.getMessage());
        }

        return correcto;
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

    public Factura buyBasket(String CIF){

        addToHistory(cesta);
        Factura factura = cesta.buy(CIF);
        cesta.clear();

        return factura;
    }


    public void cestaAdd(Producto pro, int cantidad, String cif){
        cesta.add(pro, cantidad, cif);
    }


    public void addToHistory(Cesta cesta1){
        addToHistory(cesta1.getListaID());
    }

    public void addToHistory(ArrayList<Integer> productos){
        for(int i = 0; i < productos.size(); i++){
            addToHistory(productos.get(i));
        }
    }

    public void addToHistory(Producto producto){
        addToHistory(producto.getId());
    }

    public void addToHistory(int id){
        historial.add(id);

        try {
            dbquerys.putToHistory(id);
        } catch (Exception e){
            Log.e("Error historial", "Imposible almacenar producto " + id + " --> " + e.getMessage());
        }
    }

    public ArrayList<Integer> getHistorial(){
        return historial;
    }


}
