package com.red.lifka.sisfarmaapp.Gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.red.lifka.sisfarmaapp.Cliente.ClienteFachada;
import com.red.lifka.sisfarmaapp.Cliente.Producto;
import com.red.lifka.sisfarmaapp.Cliente.ProductoGenerico;
import com.red.lifka.sisfarmaapp.R;

import java.util.ArrayList;

public class Historial extends Activity implements View.OnClickListener{

    private Button vaciar_boton;
    private ListView lista;
    private ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historial);

        vaciar_boton = (Button)findViewById(R.id.vaciar_boton);
        lista = (ListView)findViewById(R.id.listView);

        vaciar_boton.setOnClickListener(this);//Estableciendo la escucha

        loadHistorial();
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.vaciar_boton) {
            if (adaptador.getCount() == 0){
                Toast.makeText(this, R.string.vaciaerror, Toast.LENGTH_SHORT).show();
            } else {
                ClienteFachada.getInstance().getUsuario().clearHistorial();
                Toast.makeText(this, R.string.vaciaok, Toast.LENGTH_SHORT).show();
                loadHistorial();
            }
        }

    }


    private void loadHistorial(){
        ArrayList<ProductoGenerico> historial = ClienteFachada.getInstance().getHistorial();

        adaptador = new ProductoHistorialArrayAdapter(this, historial);
        lista.setAdapter(adaptador);

    }

}
