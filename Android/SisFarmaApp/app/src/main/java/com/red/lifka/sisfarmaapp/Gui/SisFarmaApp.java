package com.red.lifka.sisfarmaapp.Gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.red.lifka.sisfarmaapp.Cliente.TipoPago;
import com.red.lifka.sisfarmaapp.Cliente.Usuario;
import com.red.lifka.sisfarmaapp.DB.DBQueries;
import com.red.lifka.sisfarmaapp.DB.JSONParser;
import com.red.lifka.sisfarmaapp.R;

import org.json.JSONException;

public class SisFarmaApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sis_farma_app);
/*
        Intent intent = new Intent(this, login.class);
        startActivity(intent);*/

        JSONParser json_parser = new JSONParser(this);

        Usuario usuario;
        usuario = json_parser.login("sdfdsf", "sdfsdf");
       // usuario = json_parser.registro("dfghj", "dfghj", "fghj", "fghj",TipoPago.CONTRARREMBOLSO);

    }
}
