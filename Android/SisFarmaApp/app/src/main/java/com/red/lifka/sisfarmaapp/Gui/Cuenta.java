package com.red.lifka.sisfarmaapp.Gui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.red.lifka.sisfarmaapp.R;

public class Cuenta extends Activity implements View.OnClickListener{

    private Button configurarperfil_button;
    private Button configurarpreferencias_button;
    private Button listapendientes_button;
    private Button consultarhistorial_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuenta);


        configurarperfil_button = (Button)findViewById(R.id.configurarperfil_button);
        configurarpreferencias_button = (Button)findViewById(R.id.configurarpreferencias_button);
        listapendientes_button = (Button)findViewById(R.id.listapendientes_button);
        consultarhistorial_button = (Button)findViewById(R.id.consultarhistorial_button);

        configurarperfil_button.setOnClickListener(this);
        configurarpreferencias_button.setOnClickListener(this);
        listapendientes_button.setOnClickListener(this);
        consultarhistorial_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.configurarperfil_button) {
            startActivity(new Intent(this, Perfil.class));
        }

        if (v.getId() == R.id.configurarpreferencias_button) {
            startActivity(new Intent(this, Preferencias.class));
        }

        if (v.getId() == R.id.listapendientes_button) {
            //startActivity(new Intent(this, Pendientes.class));
            Toast.makeText(this, "Funcionalidad no implementada", Toast.LENGTH_SHORT).show();
        }

        if (v.getId() == R.id.consultarhistorial_button) {
            startActivity(new Intent(this, Historial.class));
        }

    }
}
