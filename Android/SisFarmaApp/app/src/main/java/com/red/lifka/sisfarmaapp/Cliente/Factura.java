package com.red.lifka.sisfarmaapp.Cliente;

import java.util.HashMap;

public class Factura {

    float total_sin_iva;
    float total;
    private HashMap<String, LineaFactura> productos;
    TipoPago forma_de_pago;

    Factura(){
        total = 0f;
        productos = new HashMap();
    }

    public void addProduct(Producto pro, int cantidad){
        LineaFactura linea = new LineaFactura(pro, cantidad);
        productos.put(pro.getNombre(), linea);
        total = linea.getPrecioIva();
        total_sin_iva = linea.getPrecio();
    }

    public void deleteProduct(Producto pro){

        total -= productos.get(pro).getPrecioIva();
        total_sin_iva-= productos.get(pro).getPrecio();
        productos.remove(pro.getNombre());
    }

    public float getTotal(){
        return total;
    }

    public HashMap<String, LineaFactura> getProductos(){
        return productos;
    }

    public void setFormaPago(TipoPago pago){
        forma_de_pago = pago;
    }

    public TipoPago getFormaPago(){
        return forma_de_pago;
    }
}
