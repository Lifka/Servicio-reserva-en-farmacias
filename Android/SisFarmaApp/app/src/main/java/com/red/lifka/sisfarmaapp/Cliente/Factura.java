package com.red.lifka.sisfarmaapp.Cliente;

import java.util.ArrayList;
import java.util.HashMap;

public class Factura {

    private float total_sin_iva;
    private float total;
    private HashMap<Integer, LineaFactura> productos;
    private TipoPago forma_de_pago;
    private String cif;

    Factura(String CIF, TipoPago pago){
        total = 0f;
        total_sin_iva = 0f;
        productos = new HashMap();
        cif = CIF;
        forma_de_pago = pago;
    }

    public void addProduct(Producto pro, int cantidad){
        LineaFactura linea = new LineaFactura(pro, cantidad);
        productos.put(pro.getId(), linea);
        total += linea.getPrecioIva();
        total_sin_iva += linea.getPrecio();
    }

    public void addProduct(int id,String nombre, int cantidad, float precio, float precio_iva, float porcentaje_iva){
        LineaFactura linea = new LineaFactura(id, nombre, cantidad, precio, precio_iva, porcentaje_iva);
        productos.put(id, linea);
        total += linea.getPrecioIva();
        total_sin_iva += linea.getPrecio();
    }

    public void deleteProduct(Producto pro){

        total -= productos.get(pro).getPrecioIva();
        total_sin_iva-= productos.get(pro).getPrecio();
        productos.remove(pro.getNombre());
    }

    public float getTotal(){
        return total;
    }

    public float getTotalSin(){
        return total_sin_iva;
    }

    public HashMap<Integer, LineaFactura> getProductos(){
        return productos;
    }

    public void setFormaPago(TipoPago pago){
        forma_de_pago = pago;
    }

    public TipoPago getFormaPago(){
        return forma_de_pago;
    }

    public ArrayList<Integer> getListaID(){
        ArrayList<Integer> ids = new ArrayList();


        for (int id: productos.keySet())
            ids.add(id);

        return ids;
    }

    public String getCif(){
        return cif;
    }

    public ArrayList<LineaFactura> getListaLineas(){
        ArrayList<LineaFactura> lineas = new ArrayList();

        for (LineaFactura linea: productos.values())
            lineas.add(linea);

        return lineas;
    }
}
