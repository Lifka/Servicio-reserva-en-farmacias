package com.red.lifka.sisfarmaapp.Gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.red.lifka.sisfarmaapp.Cliente.ClienteFachada;
import com.red.lifka.sisfarmaapp.Cliente.Departamentos;
import com.red.lifka.sisfarmaapp.Cliente.Producto;
import com.red.lifka.sisfarmaapp.R;

import java.util.ArrayList;


public class Catalogo extends Activity implements View.OnClickListener{

    private ListView lista;
    private ArrayAdapter adapter;
    private Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogo);

        lista = (ListView)findViewById(R.id.listViewCatalogo);
        boton = (Button)findViewById(R.id.volver_catalogo);

        boton.setOnClickListener(this);

        loadProductos();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.volver_catalogo)
            startActivity(new Intent(this, Menu.class));

    }


    private void loadProductos(){

        String CIF = ClienteFachada.getInstance().getFarmaciaSeleccionada();
        Departamentos departamento = ClienteFachada.getInstance().getDepartamentoSeleccionado();

        ArrayList<Producto> productos = ClienteFachada.getInstance().geTProductosPorDepartamento(CIF, departamento);

        adapter = new ProductoArrayAdapter(this, productos);
        lista.setAdapter(adapter);
    }
}
