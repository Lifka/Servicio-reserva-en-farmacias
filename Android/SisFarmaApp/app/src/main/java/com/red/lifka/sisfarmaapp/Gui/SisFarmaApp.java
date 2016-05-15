package com.red.lifka.sisfarmaapp.Gui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.red.lifka.sisfarmaapp.Cliente.ClienteFachada;
import com.red.lifka.sisfarmaapp.R;

public class SisFarmaApp extends AppCompatActivity implements View.OnClickListener {

    TextView url;
    Button iniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sis_farma_app);

        url = (TextView)findViewById(R.id.url);
        iniciar = (Button)findViewById(R.id.iniciar);

        iniciar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        ClienteFachada.getInstance().setServer(url.getText().toString());
        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
}
