package com.red.lifka.sisfarmaapp.Gui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.red.lifka.sisfarmaapp.R;

import java.util.ArrayList;

public class FarmaciasArrayAdapter<T> extends ArrayAdapter<T>{
    private Context context;
    private TextView nombre;
    private TextView cif;
    private View.OnClickListener listener;

    public FarmaciasArrayAdapter(Context context, ArrayList<T> farmacias) {
        super(context, 0, farmacias);
        this.context = context;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater)getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Guardar View fila
        View listItemView = view;

        //¿Existe?
        if (view == null) {
            listItemView = inflater.inflate(R.layout.farmacia, parent, false);
        }

        nombre = (TextView)listItemView.findViewById(R.id.nombre_farmacia_lista);
        cif = (TextView)listItemView.findViewById(R.id.cif_farmacia_lista);

        T item = (T)getItem(position);

        //Dividir la cadena
        String cadena;
        String subCadenas [];
        String delimitador = ",";

        cadena = item.toString();
        subCadenas = cadena.split(delimitador,2);

        nombre.setText(subCadenas[0]);
        cif.setText(subCadenas[1]);

        listItemView.setOnClickListener(listener);

        return listItemView;
    }

    public void setListener(View.OnClickListener l){
        listener = l;
    }

}
