package com.red.lifka.sisfarmaapp.Gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.red.lifka.sisfarmaapp.R;

import java.util.ArrayList;

public class ContactoArrayAdapter<T> extends ArrayAdapter<T>{
    private Context context;
    private TextView nombre;
    private TextView cif;
    private ImageView borrar;
    private View.OnClickListener listener;

    public ContactoArrayAdapter(Context context, ArrayList<T> contactos) {
        super(context, 0, contactos);
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
            listItemView = inflater.inflate(R.layout.contacto, parent, false);
        }

        nombre = (TextView)listItemView.findViewById(R.id.nombre_producto_historial);
        cif = (TextView)listItemView.findViewById(R.id.precio_uni_producto_cesta);
        borrar = (ImageView) listItemView.findViewById(R.id.borrar_contacto);

        borrar.setOnClickListener(listener);

        T item = (T)getItem(position);

        //Dividir la cadena
        String cadena;
        String subCadenas [];
        String delimitador = ",";

        cadena = item.toString();
        subCadenas = cadena.split(delimitador,2);

        nombre.setText(subCadenas[0]);
        cif.setText(subCadenas[1]);

        return listItemView;

    }


    public void setListener(View.OnClickListener l){
        listener = l;
    }


}