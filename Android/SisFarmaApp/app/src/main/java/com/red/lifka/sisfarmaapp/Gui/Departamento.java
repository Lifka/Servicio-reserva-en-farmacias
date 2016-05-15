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
import com.red.lifka.sisfarmaapp.Cliente.Departamentos;
import com.red.lifka.sisfarmaapp.R;

import java.util.ArrayList;


public class Departamento extends Activity implements View.OnClickListener{

    private ListView lista;
    private ArrayAdapter adapter;
    private Button agregar_farmacia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.departamentos);

        lista = (ListView)findViewById(R.id.listViewDepartamentos);
        agregar_farmacia = (Button) findViewById(R.id.agregar_farmacia);

        agregar_farmacia.setOnClickListener(this);

        loadDepartamentos();
    }


    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.agregar_farmacia){

            ClienteFachada.getInstance().getUsuario().addToContactos(ClienteFachada.getInstance().getFarmaciaSeleccionada());
            Toast.makeText(this, R.string.agregada, Toast.LENGTH_SHORT).show();

        } else {

            TextView departamento_tv = (TextView) v.findViewById(R.id.departamento_nombre);
            Departamentos departamento = Departamentos.valueOf(departamento_tv.getText().toString());
            if (departamento != null) {
                ClienteFachada.getInstance().seleccionarDepartamento(departamento);
                startActivity(new Intent(this, Catalogo.class));
            }

        }

    }

    private void loadDepartamentos(){

        String CIF = ClienteFachada.getInstance().getFarmaciaSeleccionada();
        ArrayList<Departamentos> departamentos = ClienteFachada.getInstance().getDepartamentos(CIF);

        adapter = new DepartamentosArrayAdapter(this, departamentos);
        ((DepartamentosArrayAdapter)adapter).setListener(this);
        lista.setAdapter(adapter);
    }
}
