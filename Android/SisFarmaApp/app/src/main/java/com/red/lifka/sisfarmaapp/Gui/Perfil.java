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

public class Perfil extends Activity implements View.OnClickListener{

    TextView nombre_tv;
    TextView pass_tv;
    TextView pass2_tv;
    Button nombre_boton;
    Button pass_boton;
    Button atras_boton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        nombre_tv = (TextView)findViewById(R.id.nombre_tv);
        pass_tv = (TextView)findViewById(R.id.pass_tv);
        pass2_tv = (TextView)findViewById(R.id.pass2_tv);
        nombre_boton = (Button)findViewById(R.id.nombre_boton);
        pass_boton = (Button)findViewById(R.id.pass_boton);
        atras_boton = (Button)findViewById(R.id.atras_boton);


        nombre_tv.setOnClickListener(this);
        pass_tv.setOnClickListener(this);
        pass2_tv.setOnClickListener(this);
        nombre_boton.setOnClickListener(this);
        pass_boton.setOnClickListener(this);
        atras_boton.setOnClickListener(this);

        nombre_tv.setText(ClienteFachada.getInstance().getUsuario().getNombre());

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.nombre_boton) {
            boolean correcto = ClienteFachada.getInstance().getUsuario().setNombreCompleto(nombre_tv.getText().toString(), ClienteFachada.getInstance().getServer());
            if (correcto)
                Toast.makeText(this, R.string.correctonombre, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, R.string.errornombre, Toast.LENGTH_SHORT).show();
        }

        if (v.getId() == R.id.pass_boton) {
            ClienteFachada.getInstance().getUsuario().setNombreCompleto(nombre_tv.getText().toString(), ClienteFachada.getInstance().getServer());
            if (pass_tv.getText().toString().equals(pass2_tv.getText().toString())) {
                boolean correcto = ClienteFachada.getInstance().getUsuario().setPass(pass_tv.getText().toString(), ClienteFachada.getInstance().getServer());

                if (correcto)
                    Toast.makeText(this, R.string.correctopass, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(this, R.string.errorpass, Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, R.string.passnocoincide, Toast.LENGTH_SHORT).show();
            }
        }

        if (v.getId() == R.id.atras_boton) {
            startActivity(new Intent(this, Menu.class));
        }


        if (v.getId() == R.id.pass_tv) {
            pass_tv.setText("");
        }

        if (v.getId() == R.id.pass2_tv) {
            pass2_tv.setText("");
        }

    }
}
