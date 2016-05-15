package com.red.lifka.sisfarmaapp.Gui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.red.lifka.sisfarmaapp.Cliente.ClienteFachada;
import com.red.lifka.sisfarmaapp.R;

import java.util.ArrayList;

public class ProductoArrayAdapter<T> extends ArrayAdapter<T> implements View.OnClickListener {
    private Context context;
    private TextView nombre;
    private TextView precio;
    private TextView precio_iva;
    private TextView descripcion;
    private TextView id;
    private ImageView buy;
    private EditText cantidad_et;

    public ProductoArrayAdapter(Context context, ArrayList<T> productos) {
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
            listItemView = inflater.inflate(R.layout.producto, parent, false);
        }


        nombre = (TextView)listItemView.findViewById(R.id.nombre_producto);
        precio = (TextView)listItemView.findViewById(R.id.precio_producto);
        precio_iva = (TextView)listItemView.findViewById(R.id.precio_iva_producto);
        descripcion = (TextView)listItemView.findViewById(R.id.descripcion_producto);
        buy = (ImageView)listItemView.findViewById(R.id.comprar_producto);
        cantidad_et = (EditText)listItemView.findViewById(R.id.cantidad_producto);
        id = (TextView)listItemView.findViewById(R.id.id_producto);

        buy.setOnClickListener(this);
        cantidad_et.setOnClickListener(this);

       // listItemView.setOnClickListener(listener);

        T item = (T)getItem(position);

        //Dividir la cadena
        String cadena;
        String subCadenas [];
        String delimitador = ",";

        cadena = item.toString();
        subCadenas = cadena.split(delimitador,5);

        nombre.setText(subCadenas[0]);
        precio.setText(subCadenas[1] + context.getResources().getString((R.string.moneda)));
        precio_iva.setText(subCadenas[2] + context.getResources().getString((R.string.moneda)));
        descripcion.setText(subCadenas[3]);
        id.setText(subCadenas[4]);


        return listItemView;

    }

    private int setCantidad(int c){
        if (c < 1){
            c = 1;
        } else if (c > 10) {
            c = 10;
        }

        cantidad_et.setText(String.valueOf(c));
        return c;
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.comprar_producto){
            View parent = (View)v.getParent();

            id = (TextView)parent.findViewById(R.id.id_producto);
            cantidad_et = (EditText)parent.findViewById(R.id.cantidad_producto);

            int cantidad = setCantidad(Integer.valueOf(cantidad_et.getText().toString()));
            int id_pro = Integer.valueOf(id.getText().toString());

            Log.d("Cesta", "Añadido producto " + id_pro + " cantidad " + cantidad );

            ClienteFachada.getInstance().addProduct(id_pro, cantidad);
            Toast.makeText(context, R.string.aniadir_cesta, Toast.LENGTH_SHORT).show();
        }

        if (v.getId() == R.id.cantidad_producto){
            View parent = (View)v.getParent();

            cantidad_et = (EditText)parent.findViewById(R.id.cantidad_producto);

            cantidad_et.setText("");
        }


    }

}