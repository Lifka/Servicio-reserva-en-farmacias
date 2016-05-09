package com.red.lifka.sisfarmaapp.Gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.red.lifka.sisfarmaapp.Cliente.TipoPago;
import com.red.lifka.sisfarmaapp.Cliente.Usuario;
import com.red.lifka.sisfarmaapp.DB.DBQueries;
import com.red.lifka.sisfarmaapp.DB.JSONParser;
import com.red.lifka.sisfarmaapp.R;

public class login extends Activity implements View.OnClickListener{

    private TextView tv_email_login;
    private TextView tv_pass_login;
    private TextView tv_email_registro;
    private TextView tv_pass_registro;
    private TextView tv_dni;
    private TextView tv_nombre;
    private Button b_acceder;
    private Button b_registrar;

    Usuario usuario = null;
    DBQueries db_queries;
    JSONParser json_parser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Crear DB
        db_queries = new DBQueries(this);

        json_parser = new JSONParser(this);


        tv_email_login = (TextView)findViewById(R.id.email_login);
        tv_pass_login = (TextView)findViewById(R.id.pass_login);
        b_acceder = (Button)findViewById(R.id.acceder_boton);

        tv_email_registro = (TextView)findViewById(R.id.email_registro);
        tv_pass_registro = (TextView)findViewById(R.id.pass_registro);
        tv_dni = (TextView)findViewById(R.id.dni_registro);
        tv_nombre = (TextView)findViewById(R.id.name_registro);
        b_registrar = (Button)findViewById(R.id.registrar_boton);

        b_acceder.setOnClickListener(this);
        b_registrar.setOnClickListener(this);
    }


    @Override
    public void onClick (View v) {

        if (v.getId() == R.id.acceder_boton) {
            usuario = json_parser.login(tv_email_login.getText().toString(), tv_pass_login.getText().toString());
            login();
        }

        if (v.getId() == R.id.registrar_boton) {
           // usuario = json_parser.registro(tv_email_login.getText().toString(), tv_pass_login.getText().toString(),
            //        tv_dni.getText().toString(), tv_nombre.getText().toString(), TipoPago.SIN_ESTABLECER);
            login();
        }

    }

    public void login(){
        if (usuario != null){
          /*  Intent intent = new Intent(this, DisplayMessageActivity.class);
            startActivity(intent);*/
            Toast.makeText(login.this, "Correcto", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(login.this, "Usuario inválido", Toast.LENGTH_LONG).show();
        }
    }


}
