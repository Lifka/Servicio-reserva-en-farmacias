package com.red.lifka.sisfarmaapp.Gui;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.red.lifka.sisfarmaapp.R;

import java.util.ArrayList;

public class DepartamentosArrayAdapter<T> extends ArrayAdapter<T> {
    private Context context;
    private TextView departamento;
    private View.OnClickListener listener;

    public DepartamentosArrayAdapter(Context context, ArrayList<T> departamentos) {
        super(context, 0, departamentos);
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
            listItemView = inflater.inflate(R.layout.departamento, parent, false);
        }

        departamento = (TextView)listItemView.findViewById(R.id.departamento_nombre);

        T item = (T)getItem(position);

        String departamento_st = item.toString();

        departamento.setText(departamento_st);

        listItemView.setOnClickListener(listener);

        return listItemView;
    }

    public void setListener(View.OnClickListener l){
        listener = l;
    }

}
