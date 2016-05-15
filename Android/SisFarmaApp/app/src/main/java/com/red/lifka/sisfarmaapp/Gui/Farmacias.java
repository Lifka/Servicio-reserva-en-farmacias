package com.red.lifka.sisfarmaapp.Gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.red.lifka.sisfarmaapp.Cliente.ClienteFachada;
import com.red.lifka.sisfarmaapp.Cliente.Farmacia;
import com.red.lifka.sisfarmaapp.R;

import java.util.ArrayList;


public class Farmacias extends Activity implements View.OnClickListener{

    private ListView lista;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmacias);

        lista = (ListView)findViewById(R.id.listViewFarmacias);

        loadFarmacias();
    }


    @Override
    public void onClick(View v) {
        TextView cif_tv = (TextView)v.findViewById(R.id.cif_farmacia_lista);
        String farmacia = cif_tv.getText().toString();
        ClienteFachada.getInstance().setFarmacia(farmacia);
        startActivity(new Intent(this, Departamento.class));
    }

    private void loadFarmacias(){

        ArrayList<Farmacia> farmacias = ClienteFachada.getInstance().getFarmaciasCercanas();

        adapter = new FarmaciasArrayAdapter(this, farmacias);
        ((FarmaciasArrayAdapter)adapter).setListener(this);
        lista.setAdapter(adapter);
    }
}
