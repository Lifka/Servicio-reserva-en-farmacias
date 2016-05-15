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

public class login extends Activity implements View.OnClickListener{

    private TextView tv_email_login;
    private TextView tv_pass_login;
    private TextView registar;
    private TextView recuperar;
    private Button b_acceder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ClienteFachada.getInstance().startDB(this);

        tv_email_login = (TextView)findViewById(R.id.email_login);
        tv_pass_login = (TextView)findViewById(R.id.pass_login);
        b_acceder = (Button)findViewById(R.id.acceder_boton);
        registar = (TextView)findViewById(R.id.registar);
        recuperar = (TextView)findViewById(R.id.recuperar);

        b_acceder.setOnClickListener(this);

        tv_email_login.setOnClickListener(this);
        tv_pass_login.setOnClickListener(this);
        registar.setOnClickListener(this);
        recuperar.setOnClickListener(this);

    }


    @Override
    public void onClick (View v) {

        if (v.getId() == R.id.acceder_boton) {
            boolean result = ClienteFachada.getInstance().loadUsuario(tv_email_login.getText().toString(), tv_pass_login.getText().toString());
            login(result);
        }

        if (v.getId() == R.id.email_login) {
            tv_email_login.setText("");
        }

        if (v.getId() == R.id.pass_login) {
            tv_pass_login.setText("");
        }

        if (v.getId() == R.id.registar) {
            startActivity(new Intent(this, Registro.class));
        }

        if (v.getId() == R.id.recuperar) {
           // startActivity(new Intent(this, Recuperar.class));
            Toast.makeText(login.this, "Funcionalidad no implementada", Toast.LENGTH_SHORT).show();
        }


    }

    public void login(boolean result){
        if (result){
            Toast.makeText(login.this, R.string.correcto, Toast.LENGTH_SHORT).show();
            cargarUbicacion();
            startActivity(new Intent(this, Menu.class));
        } else {
            Toast.makeText(login.this, R.string.loginerror, Toast.LENGTH_SHORT).show();
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
