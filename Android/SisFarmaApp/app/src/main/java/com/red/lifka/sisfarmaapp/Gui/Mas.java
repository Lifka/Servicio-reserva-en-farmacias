package com.red.lifka.sisfarmaapp.Gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.red.lifka.sisfarmaapp.R;

public class Mas extends Activity implements View.OnClickListener{


    Button contactos_button;
    Button faq_button;
    Button ayudaeinfo_button;
    Button indicarubi_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mas);

        contactos_button = (Button)findViewById(R.id.contactos_button);
        faq_button = (Button)findViewById(R.id.faq_button);
        ayudaeinfo_button = (Button)findViewById(R.id.ayudaeinfo_button);
        indicarubi_button = (Button)findViewById(R.id.indicarubi_button);

        contactos_button.setOnClickListener(this);
        faq_button.setOnClickListener(this);
        ayudaeinfo_button.setOnClickListener(this);
        indicarubi_button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.contactos_button) {
            startActivity(new Intent(this, Contactos.class));
        }

        if (v.getId() == R.id.faq_button) {
            Toast.makeText(this, "Funcionalidad no implementada", Toast.LENGTH_SHORT).show();
        }

        if (v.getId() == R.id.ayudaeinfo_button) {
            Toast.makeText(this, "Funcionalidad no implementada", Toast.LENGTH_SHORT).show();
        }

        if (v.getId() == R.id.indicarubi_button) {
            startActivity(new Intent(this, IndicarUbicacion.class));
        }
    }
}
