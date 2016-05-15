package com.red.lifka.sisfarmaapp.Gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.red.lifka.sisfarmaapp.Cliente.ClienteFachada;
import com.red.lifka.sisfarmaapp.Cliente.TipoPago;
import com.red.lifka.sisfarmaapp.R;


public class Preferencias extends Activity implements View.OnClickListener{

    private RadioButton contrareemboldo_radio;
    private RadioButton tarjeta_radio;
    private RadioButton paypal_radio;
    private Button continuar_boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preferencias);

        contrareemboldo_radio = (RadioButton)findViewById(R.id.radio_contrareembolso);
        paypal_radio = (RadioButton)findViewById(R.id.radio_paypal);
        tarjeta_radio = (RadioButton)findViewById(R.id.radio_tarjeta);

        continuar_boton = (Button)findViewById(R.id.button_continuar);

        contrareemboldo_radio.setOnClickListener(this);
        paypal_radio.setOnClickListener(this);
        tarjeta_radio.setOnClickListener(this);
        continuar_boton.setOnClickListener(this);

        if (ClienteFachada.getInstance().getUsuario().getPagoPreferencia() == TipoPago.CONTRARREMBOLSO){
            contrareemboldo_radio.setChecked(true);
            paypal_radio.setChecked(false);
            tarjeta_radio.setChecked(false);
        }

        if (ClienteFachada.getInstance().getUsuario().getPagoPreferencia() == TipoPago.PAYPAL){
            contrareemboldo_radio.setChecked(false);
            paypal_radio.setChecked(true);
            tarjeta_radio.setChecked(false);
        }

        if (ClienteFachada.getInstance().getUsuario().getPagoPreferencia() == TipoPago.TARJETA){
            contrareemboldo_radio.setChecked(false);
            paypal_radio.setChecked(false);
            tarjeta_radio.setChecked(true);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_continuar) {
            TipoPago tipo_pago = TipoPago.SIN_ESTABLECER;
            if (paypal_radio.isChecked())
                tipo_pago = TipoPago.PAYPAL;
            if (tarjeta_radio.isChecked())
                tipo_pago = TipoPago.TARJETA;
            if (contrareemboldo_radio.isChecked())
                tipo_pago = TipoPago.CONTRARREMBOLSO;

            boolean correcto = ClienteFachada.getInstance().getUsuario().setPagoPreferencia(tipo_pago,  ClienteFachada.getInstance().getServer());

            if (correcto)
                startActivity(new Intent(this, Menu.class));
            else
                Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
        }


        if (v.getId() == R.id.radio_contrareembolso) {
            contrareemboldo_radio.setChecked(true);
            paypal_radio.setChecked(false);
            tarjeta_radio.setChecked(false);
        }
        if (v.getId() == R.id.radio_paypal) {
            contrareemboldo_radio.setChecked(false);
            paypal_radio.setChecked(true);
            tarjeta_radio.setChecked(false);
        }
        if (v.getId() == R.id.radio_tarjeta) {
            contrareemboldo_radio.setChecked(false);
            paypal_radio.setChecked(false);
            tarjeta_radio.setChecked(true);
        }

    }
}
