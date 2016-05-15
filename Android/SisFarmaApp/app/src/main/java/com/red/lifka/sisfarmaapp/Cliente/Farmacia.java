package com.red.lifka.sisfarmaapp.Cliente;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.red.lifka.sisfarmaapp.DB.DBQueries;
import com.red.lifka.sisfarmaapp.Gui.Departamento;

import java.util.ArrayList;
import java.util.HashMap;


public class Farmacia {
    private String CIF;
    private String nombre;
    private Location localizacion;
    HashMap<Integer, Producto> productos_farmacia;
    DBQueries db_queries;

    public Farmacia(String CIF, String nombre, Location localizacion, Context c){
        this.CIF = CIF;
        this.nombre = nombre;
        this.localizacion = localizacion;
        productos_farmacia = new HashMap();
        db_queries = new DBQueries(c);
        loadProductos();
    }

    private void loadProductos(){
        try{
            ArrayList<ProductoGenerico> productos = db_queries.getProductosDe(CIF);
            setProducto(productos);
        } catch (Exception e){
            Log.e("ERROR_ load productos", "Error obteniendo productos de " + getCIF() +" " + e.getMessage());
        }

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

    public void setProducto(ProductoGenerico producto){
        productos_farmacia.put(producto.getId(), (Producto)producto);
    }

    public void setProducto(ArrayList<ProductoGenerico> producto_farmacia_array){
        for(int i = 0; i < producto_farmacia_array.size(); i++)
            setProducto(producto_farmacia_array.get(i));
    }

    @Override
    public String toString(){
        return getNombre() + "," + getCIF();
    }

    public ArrayList<Producto> getProductosFarmacia(){
        ArrayList<Producto> productos = new ArrayList();

        for(Producto producto_farmacia : productos_farmacia.values()){
            productos.add(producto_farmacia);
        }

        return productos;
    }

    public ArrayList<Departamentos> getDepartamentos(){
        ArrayList<Departamentos> departamentos = new ArrayList();

        for(Producto producto_farmacia : productos_farmacia.values())
            if (!departamentos.contains(producto_farmacia.getDepartamento())) {
                departamentos.add(producto_farmacia.getDepartamento());
            }

        return departamentos;
    }



    public ArrayList<Producto> getProductosDepartamento(Departamentos departamento){
        ArrayList<Producto> productos = new ArrayList();

        for(Producto producto_farmacia : productos_farmacia.values())
            if (producto_farmacia.getDepartamento() == departamento) {
                productos.add(producto_farmacia);
            }

        return productos;
    }

}
