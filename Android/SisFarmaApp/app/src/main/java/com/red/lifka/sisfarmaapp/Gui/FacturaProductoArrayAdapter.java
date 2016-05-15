package com.red.lifka.sisfarmaapp.Gui;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.red.lifka.sisfarmaapp.R;

import java.util.ArrayList;

public class FacturaProductoArrayAdapter<T> extends ArrayAdapter<T> {
    private Context context;
    private TextView nombre_factura;
    private TextView cantidad_factura;
    private TextView precio_factura;

    public FacturaProductoArrayAdapter(Context context, ArrayList<T> lineas) {
        super(context, 0, lineas);
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
            listItemView = inflater.inflate(R.layout.producto_factura, parent, false);
        }


        nombre_factura = (TextView)listItemView.findViewById(R.id.nombre_factura);
        cantidad_factura = (TextView)listItemView.findViewById(R.id.cantidad_factura);
        precio_factura = (TextView)listItemView.findViewById(R.id.precio_factura);


        // listItemView.setOnClickListener(listener);

        T item = (T)getItem(position);

        //Dividir la cadena
        String cadena;
        String subCadenas [];
        String delimitador = ",";

        cadena = item.toString();
        subCadenas = cadena.split(delimitador,5);

        nombre_factura.setText(subCadenas[0]);
        cantidad_factura.setText(subCadenas[1]);
        precio_factura.setText(subCadenas[2]);

        return listItemView;
    }
}