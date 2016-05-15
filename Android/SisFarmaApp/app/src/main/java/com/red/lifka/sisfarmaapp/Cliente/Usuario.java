package com.red.lifka.sisfarmaapp.Cliente;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.red.lifka.sisfarmaapp.DB.DBQueries;
import com.red.lifka.sisfarmaapp.DB.JSONParser;

import java.text.ParseException;
import java.util.ArrayList;

public class Usuario {
    private String email;
    private String nombre_completo;
    private String dni;
    private Cesta cesta;
    private ArrayList<Integer> historial;
    private TipoPago pagoPreferido;
    private DBQueries queries;
    private ArrayList<String> contactos;
    private Context context;
    private Factura facturaPendiente = null;

    private Location lastKnownLocation;

    public Usuario (String email, String nombre_completo, String dni, TipoPago pago, Context c) {
        context = c;
        queries = new DBQueries(c);
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

    public void setLocation(Location location){
        lastKnownLocation = location;
    }

    public Location getLastKnownLocation(){
        return lastKnownLocation;
    }

    private void loadHistorial() {
        try {
            historial = queries.getHistorial();
        } catch (Exception e){
            Log.e("Error historial", "Imposible recuperar el historial " + e.getMessage());
            historial = new ArrayList();
        }
    }

    private void loadContactos() {
        try {
            contactos = queries.getCotactos();
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
            queries.putToContactos(cif);
        } catch (Exception e){
            Log.e("Error contactos", "Imposible cargar contactos " + cif + " --> " + e.getMessage());
        }
        loadContactos();
    }

    public void removeFromContactos(String cif){
        try {
            queries.removeFromContactos(cif);
        } catch (Exception e){
            Log.e("Error_ contactos", "Imposible borrar contactos " + cif + " --> " + e.getMessage());
        }
        loadContactos();
    }

    public ArrayList<String> getContactos(){
        return  contactos;
    }


    public boolean setPagoPreferencia(TipoPago pago_nuevo, String server){
        JSONParser jsonParser = new JSONParser(context, server);
        boolean correcto = false;

        try {
            correcto = jsonParser.updateUser(email, nombre_completo, pago_nuevo);
        } catch (Exception e){
            Log.e("Error updateUser", e.getMessage());
        }

        if (correcto)
            pagoPreferido = pago_nuevo;

        return correcto;
    }

    public boolean setNombreCompleto(String nombre_completo_nuevo, String server){
        JSONParser jsonParser = new JSONParser(context, server);
        boolean correcto = false;

        try {
            correcto = jsonParser.updateUser(email, nombre_completo_nuevo, pagoPreferido);
        } catch (Exception e){
            Log.e("Error updateUser", e.getMessage());
        }

        if (correcto)
            nombre_completo = nombre_completo_nuevo;

        return correcto;
    }

    public boolean setPass(String pass, String server){
        JSONParser jsonParser = new JSONParser(context, server);
        boolean correcto = false;

        try {
            correcto = jsonParser.updatePass(email, pass);
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

    public ArrayList<Integer> buyBasket(String server){
        ArrayList<Integer> sin_stock = new ArrayList();

        if (cesta.size() > 0) {
            facturaPendiente = cesta.buy(getPagoPreferencia());

            try{
                JSONParser json_parser = new JSONParser(context, server);
                sin_stock = json_parser.sendFactura(facturaPendiente, cesta.getCIF());
            } catch (Exception e){
                Log.e("Error_ sendFactura", e.getMessage());
            }

            if (sin_stock.size() == 0) {
                addToHistory(cesta);
                clearCesta();
            }
        }

        return sin_stock;
    }

    public int totalCesta(){
        return cesta.size();
    }

    public Factura getFactura(){
        Factura f = facturaPendiente;
        facturaPendiente = null;
        return f;
    }

    public void clearCesta(){
        cesta.clear();
        cesta = Cesta.getInstance();
    }


    public void clearHistorial(){
        historial.clear();
        try {
            queries.clearHistorial();
        } catch (Exception e){
            Log.e("Error historial", "Imposible borrar el historial " + e.getMessage());
        }
    }

    void selectFarmacia(String CIF){
        cesta.setFarmaciaCompra(CIF);
    }


    public void addCesta(Producto pro, int cantidad){
        cesta.add(pro, cantidad);
    }


    public void addCesta(int pro_id, int cantidad){
        try{
            Producto producto = queries.getProductoById(pro_id);
            cesta.add(producto, cantidad);
        } catch (ParseException e) {
            Log.e("ERROR_ recuperar id ", "Error recuperando producto " + e.getMessage());
        }
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
            queries.putToHistory(id);
        } catch (Exception e){
            Log.e("Error historial", "Imposible almacenar producto " + id + " --> " + e.getMessage());
        }
    }

    public ArrayList<Integer> getHistorial(){
        return historial;
    }

    public Cesta getCesta(){
        return cesta;
    }


}
