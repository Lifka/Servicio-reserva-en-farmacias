package com.red.lifka.sisfarmaapp.Gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.red.lifka.sisfarmaapp.Cliente.ClienteFachada;
import com.red.lifka.sisfarmaapp.R;


public class IndicarUbicacion  extends Activity implements View.OnClickListener {

    TextView latitud_tv;
    TextView longitud_tv;
    Button aplicar_ubi_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indicarubicacion);

        latitud_tv = (TextView)findViewById(R.id.latitud_tv);
        longitud_tv = (TextView)findViewById(R.id.longitud_tv);
        aplicar_ubi_button = (Button)findViewById(R.id.aplicar_ubi_button);

        latitud_tv.setOnClickListener(this);
        longitud_tv.setOnClickListener(this);
        aplicar_ubi_button.setOnClickListener(this);

        latitud_tv.setText(Double.toString(ClienteFachada.getInstance().getUsuario().getLastKnownLocation().getLatitude()));
        longitud_tv.setText(Double.toString(ClienteFachada.getInstance().getUsuario().getLastKnownLocation().getLongitude()));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.aplicar_ubi_button) {
            ClienteFachada.getInstance().loadUbicacion(Float.valueOf(latitud_tv.getText().toString()),
                    Float.valueOf(longitud_tv.getText().toString()));
            Toast.makeText(this, R.string.actualizado, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, Menu.class));
        }

        if (v.getId() == R.id.longitud_tv) {
            longitud_tv.setText("");
        }

        if (v.getId() == R.id.latitud_tv) {
            latitud_tv.setText("");
        }

    }
}