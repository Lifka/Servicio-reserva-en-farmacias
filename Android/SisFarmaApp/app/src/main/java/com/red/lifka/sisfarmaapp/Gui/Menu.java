package com.red.lifka.sisfarmaapp.Gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.red.lifka.sisfarmaapp.Cliente.ClienteFachada;
import com.red.lifka.sisfarmaapp.R;

public class Menu extends Activity implements View.OnClickListener{

    Button administrarcuenta_button;
    Button catalogo_button;
    Button cesta_button;
    Button mas_button;
    Button logout_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        administrarcuenta_button = (Button)findViewById(R.id.administrarcuenta_button);
        catalogo_button = (Button)findViewById(R.id.catalogo_button);
        cesta_button = (Button)findViewById(R.id.cesta_button);
        mas_button = (Button)findViewById(R.id.mas_button);
        logout_button = (Button)findViewById(R.id.logout_button);


        administrarcuenta_button.setOnClickListener(this);
        catalogo_button.setOnClickListener(this);
        cesta_button.setOnClickListener(this);
        logout_button.setOnClickListener(this);
        mas_button.setOnClickListener(this);

    }


    @Override
    public void onClick (View v) {
        if (v.getId() == R.id.administrarcuenta_button) {
            startActivity(new Intent(this, Cuenta.class));
        }

        if (v.getId() == R.id.catalogo_button) {
            startActivity(new Intent(this, Farmacias.class));
        }

        if (v.getId() == R.id.cesta_button) {
            startActivity(new Intent(this, Cesta.class));
        }

        if (v.getId() == R.id.mas_button) {
            startActivity(new Intent(this, Mas.class));
        }

        if (v.getId() == R.id.logout_button) {
            ClienteFachada.getInstance().logout();
            startActivity(new Intent(this, login.class));
        }
    }
}