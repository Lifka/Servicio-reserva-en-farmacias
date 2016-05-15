package com.red.lifka.sisfarmaapp.Cliente;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.red.lifka.sisfarmaapp.DB.DBQueries;
import com.red.lifka.sisfarmaapp.DB.JSONParser;

import java.util.ArrayList;

public class ClienteFachada {
    private static ClienteFachada ourInstance = new ClienteFachada();
    private Usuario usuario = null;
    private DBQueries db_queries;
    private JSONParser json_parser;
    private boolean identificado = false;
    private String farmacia_Seleccionada = null;
    private Departamentos departamento_seleccionado = null;
    private String server;

    public static ClienteFachada getInstance() {
        return ourInstance;
    }

    private ClienteFachada() {
    }

    public void setServer(String url){
        server = url;
    }

    public boolean loadUsuario(String email, String pass){
        usuario = json_parser.login(email, pass);
        identificado = (usuario != null);
        return identificado;
    }

    public boolean loadUsuario(String email, String pass, String dni, String nombre){
        usuario = json_parser.registro(email, pass, dni, nombre, TipoPago.SIN_ESTABLECER);
        identificado = (usuario != null);
        return identificado;
    }

    public void logout(){
        usuario = null;
        identificado = false;
    }


    public void startDB(Context c){
        db_queries = new DBQueries(c);
        json_parser = new JSONParser(c, server);
        json_parser.readAndParseJSON();
    }

    public void loadUbicacion(float lat, float lon){
        try{
            usuario.setLocation(lat, lon);
        } catch (Exception e){
            Log.e("ERROR_", "Error al cargar ubicación geográfica " + e.getMessage());
        }
    }

    public void loadUbicacion(Location location){
        try{
            usuario.setLocation(location);
        } catch (Exception e){
            Log.e("ERROR_", "Error al cargar ubicación geográfica " + e.getMessage());
        }
    }

    public Usuario getUsuario(){
        return usuario;
    }

    public ArrayList<Integer> buy(){
        ArrayList<Integer> sin_stock = usuario.buyBasket(server);
        return sin_stock;
    }

    public String  getServer(){
        return server;
    }

    public Factura getFactura(){
        return  usuario.getFactura();
    }

    public ArrayList<ProductoGenerico> getProductosByIds(ArrayList<Integer> lista_ids){
        ArrayList<ProductoGenerico> productos = new ArrayList();

        try{
            productos = db_queries.getProductosByIds(lista_ids);
        } catch (Exception e){
            Log.e("Error_ get by ids", e.getMessage());
        }
        return productos;
    }

    public ArrayList<Farmacia> getFarmaciasByCif(ArrayList<String> lista_cif){
        ArrayList<Farmacia> farmacias = new ArrayList();

        try{
            farmacias = db_queries.getFarmaciasByCif(lista_cif);
        } catch (Exception e){
            Log.e("Error_ get by ids", e.getMessage());
        }
        return farmacias;
    }

    public void addProduct(int id, int cantidad){
        getUsuario().addCesta(id, cantidad);
    }

    public ArrayList<LineaCesta> getCesta(){
        return getUsuario().getCesta().getListaLineas();
    }

    public ArrayList<ProductoGenerico> getHistorial(){
        return getProductosByIds(usuario.getHistorial());
    }

    public ArrayList<Farmacia> getContactos(){
        ArrayList<Farmacia> farmacias = new ArrayList();

        try{
            farmacias = db_queries.getCotactosFarmacia();
        } catch (Exception e){
            Log.e("Error_ get contactos ", e.getMessage());
        }
        return farmacias;
    }

    public void setFarmacia(String CIF){
        usuario.selectFarmacia(CIF);
        farmacia_Seleccionada = CIF;
    }

    public String getFarmaciaSeleccionada(){
        return farmacia_Seleccionada;
    }

    public ArrayList<Farmacia> getFarmacias(){
        ArrayList<Farmacia> farmacias = new ArrayList();

        try{
            farmacias = db_queries.getFarmacias();
        } catch (Exception e){
            Log.e("Error_ get farmacias ", e.getMessage());
        }

        return farmacias;
    }

    public ArrayList<Farmacia> getFarmaciasCercanas(){
        ArrayList<Farmacia> farmacias = getFarmacias();
        ArrayList<Farmacia> farmacias_ordenado = new ArrayList();
        Location location_usuario = usuario.getLastKnownLocation();
        Float menor;;
        Farmacia menor_far = null;

        for(int i = 0; i < farmacias.size(); i++){

            Location location_far = farmacias.get(i).getLocation();
            menor = location_usuario.distanceTo(location_far);
            menor_far = farmacias.get(i);

            for(int j = i+1; j < farmacias.size(); j++){

                location_far = farmacias.get(j).getLocation();

                if (location_usuario.distanceTo(location_far) < menor){
                    menor = location_usuario.distanceTo(location_far);
                    menor_far = farmacias.get(j);
                }
            }
            farmacias_ordenado.add(menor_far);
        }

        return farmacias;

    }

    public ArrayList<Departamentos> getDepartamentos(String CIF){
        Farmacia farmacia = null;
        ArrayList<Departamentos> departamentos = new ArrayList();

        try{
            farmacia = db_queries.getFarmaciaByCif(CIF);
        } catch (Exception e){
            Log.e("Error_ get farmacia ", e.getMessage());
        }

        if (farmacia != null){
            departamentos = farmacia.getDepartamentos();
        } else {
            Log.e("ERROR_ farmacia null ", "Imposible obtener farmacia " + CIF);
        }

        return departamentos;
    }

    public void seleccionarDepartamento(Departamentos de){
        departamento_seleccionado = de;
    }

    public Departamentos getDepartamentoSeleccionado(){
        return departamento_seleccionado;
    }

    public ArrayList<Producto> geTProductosPorDepartamento(String CIF, Departamentos departamento){
        ArrayList<Producto> productos = new ArrayList();
        Farmacia farmacia = null;

        try{
            farmacia = db_queries.getFarmaciaByCif(CIF);
        } catch (Exception e){
            Log.e("Error_ get farmacia " + CIF + " ", e.getMessage());
        }

        if (farmacia != null){
            productos = farmacia.getProductosDepartamento(departamento);
        } else {
            Log.e("Error_ farmacia null ", CIF + " => null");
        }

        return productos;
    }

}
