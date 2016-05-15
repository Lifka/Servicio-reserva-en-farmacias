package com.red.lifka.sisfarmaapp.Gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.red.lifka.sisfarmaapp.R;

import java.util.ArrayList;


public class ProductoHistorialArrayAdapter<T> extends ArrayAdapter<T> {
    private Context context;
    private TextView nombre;
    private TextView precio;
    private TextView precio_iva;
    private TextView descripcion;

    public ProductoHistorialArrayAdapter(Context context, ArrayList<T> productos) {
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
            listItemView = inflater.inflate(R.layout.producto_historial, parent, false);
        }

        nombre = (TextView)listItemView.findViewById(R.id.nombre_producto_historial);
        precio = (TextView)listItemView.findViewById(R.id.precio_uni_producto_historial);
        precio_iva = (TextView)listItemView.findViewById(R.id.precio_total_producto_historial);
        descripcion = (TextView)listItemView.findViewById(R.id.descripcion_producto_historial);

        T item = (T)getItem(position);

        //Dividir la cadena
        String cadena;
        String subCadenas [];
        String delimitador = ",";

        cadena = item.toString();
        subCadenas = cadena.split(delimitador,4);

        nombre.setText(subCadenas[0]);
        precio.setText(subCadenas[1] + context.getResources().getString((R.string.moneda)));
        precio_iva.setText(subCadenas[2] + context.getResources().getString((R.string.moneda)));
        descripcion.setText(subCadenas[3]);

        return listItemView;
    }

}
