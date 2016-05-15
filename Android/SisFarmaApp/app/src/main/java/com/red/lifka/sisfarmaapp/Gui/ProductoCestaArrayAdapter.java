package com.red.lifka.sisfarmaapp.Gui;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.red.lifka.sisfarmaapp.R;

import java.util.ArrayList;

public class ProductoCestaArrayAdapter<T> extends ArrayAdapter<T>{
    private Context context;
    private TextView nombre;
    private TextView precio;
    private TextView precio_iva;
    private TextView cantidad_et;

    public ProductoCestaArrayAdapter(Context context, ArrayList<T> productos) {
        super(context, 0, productos);
        this.context = context;
    }


    @Override
    public View getView(int position, View producto_view, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater)getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Guardar View fila
        View listItemView = producto_view;

        //¿Existe?
        if (producto_view == null) {
            //Si no existe, entonces inflarlo con two_line_list_item.xml
            listItemView = inflater.inflate(R.layout.producto_cesta, parent, false);
        }

        nombre = (TextView)listItemView.findViewById(R.id.nombre_producto_cesta);
        precio = (TextView)listItemView.findViewById(R.id.precio_uni_producto_cesta);
        precio_iva = (TextView)listItemView.findViewById(R.id.precio_total_producto_cesta);
        cantidad_et = (TextView)listItemView.findViewById(R.id.cantidad_producto_cesta);


        T item = (T)getItem(position);

        //Dividir la cadena
        String cadena;
        String subCadenas [];
        String delimitador = ",";

        cadena = item.toString();
        subCadenas = cadena.split(delimitador,6);

        cantidad_et.setText(subCadenas[0]);
        nombre.setText(subCadenas[1]);
        precio.setText(subCadenas[2] + context.getResources().getString((R.string.moneda)));
        float precio_total = Float.valueOf(subCadenas[3]) * Integer.valueOf(subCadenas[0]);
        precio_iva.setText(precio_total + context.getResources().getString((R.string.moneda)));

        return listItemView;
    }

}
