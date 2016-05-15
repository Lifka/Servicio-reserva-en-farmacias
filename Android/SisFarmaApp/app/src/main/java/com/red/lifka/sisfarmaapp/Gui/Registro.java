package com.red.lifka.sisfarmaapp.Gui;


import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.red.lifka.sisfarmaapp.Cliente.ClienteFachada;
import com.red.lifka.sisfarmaapp.R;

public class Registro extends Activity implements View.OnClickListener{

    private Button b_registrar;
    private TextView tv_email_registro;
    private TextView tv_pass_registro;
    private TextView tv_pass_registro2;
    private TextView tv_dni;
    private TextView tv_nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        ClienteFachada.getInstance().startDB(this);


        tv_email_registro = (TextView)findViewById(R.id.email_registro);
        tv_pass_registro = (TextView)findViewById(R.id.pass_registro);
        tv_pass_registro2 = (TextView)findViewById(R.id.pass_registro2);
        tv_dni = (TextView)findViewById(R.id.dni_registro);
        tv_nombre = (TextView)findViewById(R.id.name_registro);
        b_registrar = (Button)findViewById(R.id.registro_boton);

        b_registrar.setOnClickListener(this);

        tv_email_registro.setOnClickListener(this);
        tv_pass_registro.setOnClickListener(this);
        tv_pass_registro2.setOnClickListener(this);
        tv_dni.setOnClickListener(this);
        tv_nombre.setOnClickListener(this);
    }


    @Override
    public void onClick (View v) {


        if (v.getId() == R.id.registro_boton) {
            if (tv_pass_registro2.getText().toString().equals(tv_pass_registro.getText().toString())) {
                boolean result = ClienteFachada.getInstance().loadUsuario(tv_email_registro.getText().toString(), tv_pass_registro.getText().toString(),
                        tv_dni.getText().toString(), tv_nombre.getText().toString());
                login(result);
            } else {
                Toast.makeText(this, R.string.passnocoincide, Toast.LENGTH_LONG).show();
            }
        }

        if (v.getId() == R.id.email_registro) {
            tv_email_registro.setText("");
        }

        if (v.getId() == R.id.dni_registro) {
            tv_dni.setText("");
        }

        if (v.getId() == R.id.name_registro) {
            tv_nombre.setText("");
        }

        if (v.getId() == R.id.pass_registro) {
            tv_pass_registro.setText("");
        }

        if (v.getId() == R.id.pass_registro2) {
            tv_pass_registro2.setText("");
        }

    }

    public void login(boolean result){
        if (result){
            Toast.makeText(this, R.string.correcto, Toast.LENGTH_LONG).show();
            cargarUbicacion();
            startActivity(new Intent(this, Preferencias.class));
        } else {
            Toast.makeText(this, R.string.emailregistrado, Toast.LENGTH_LONG).show();
        }

    }


    protected void cargarUbicacion(){
        LocationLoad locationLoad = new LocationLoad(this);
        Location location = locationLoad.getLocation();

        if(location != null)
            ClienteFachada.getInstance().loadUbicacion(location);
        else
            Log.e("ERROR_ location", "Imposible obtener localización, recibido null");
    }

}
