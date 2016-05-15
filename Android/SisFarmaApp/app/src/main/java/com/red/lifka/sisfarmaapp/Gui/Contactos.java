package com.red.lifka.sisfarmaapp.Gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.red.lifka.sisfarmaapp.Cliente.ClienteFachada;
import com.red.lifka.sisfarmaapp.Cliente.Farmacia;
import com.red.lifka.sisfarmaapp.R;

import java.util.ArrayList;

public class Contactos extends Activity implements View.OnClickListener{

    ListView lista;
    private ArrayAdapter adaptador;
    Button volver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactos);

        lista = (ListView)findViewById(R.id.listViewContactos);
        volver = (Button)findViewById(R.id.atras_contactos);

        volver.setOnClickListener(this);

        loadContactos();
    }



    public void loadContactos(){
        ArrayList<Farmacia> contactos = ClienteFachada.getInstance().getContactos();

        if (contactos == null || contactos.size() == 0) {
            Toast.makeText(this, R.string.errorcontactos, Toast.LENGTH_SHORT).show();
        }

        adaptador = new ContactoArrayAdapter(this, contactos);
        ((ContactoArrayAdapter)adaptador).setListener(this);
        lista.setAdapter(adaptador);
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.atras_contactos){
            startActivity(new Intent(this, Menu.class));
        }

        if (v.getId() == R.id.borrar_contacto){
            View parent = (View)v.getParent();


            TextView cif_tv = (TextView)parent.findViewById(R.id.precio_uni_producto_cesta);
            String CIF = cif_tv.getText().toString();

            ClienteFachada.getInstance().getUsuario().removeFromContactos(CIF);

            loadContactos();
        }


    }
}
