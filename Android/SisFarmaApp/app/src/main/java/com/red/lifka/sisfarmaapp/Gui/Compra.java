package com.red.lifka.sisfarmaapp.Gui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.red.lifka.sisfarmaapp.Cliente.ClienteFachada;
import com.red.lifka.sisfarmaapp.Cliente.Factura;
import com.red.lifka.sisfarmaapp.Cliente.LineaFactura;
import com.red.lifka.sisfarmaapp.R;

import java.util.ArrayList;


public class Compra extends Activity implements View.OnClickListener{

    private ListView listViewFactura;
    private TextView precio_final_sin;
    private TextView precio_final;
    private TextView forma_pago;
    private Button volver_factura;
    private ArrayAdapter adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compra);

        listViewFactura = (ListView)findViewById(R.id.listViewFactura);
        precio_final_sin = (TextView) findViewById(R.id.precio_final_sin);
        precio_final = (TextView)findViewById(R.id.precio_final);
        forma_pago = (TextView)findViewById(R.id.forma_pago);
        volver_factura = (Button)findViewById(R.id.volver_factura);

        volver_factura.setOnClickListener(this);
        loadFactura();


    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.volver_factura){
            startActivity(new Intent(this, Menu.class));
        }

    }

    private void loadFactura(){
        Factura factura = ClienteFachada.getInstance().getFactura();

        try{
            precio_final_sin.setText(String.valueOf(factura.getTotalSin()));
            precio_final.setText(String.valueOf(factura.getTotal()));
            forma_pago.setText(factura.getFormaPago().toString());

            ArrayList<LineaFactura> lineas = factura.getListaLineas();
            adaptador = new FacturaProductoArrayAdapter(this, lineas);
            listViewFactura.setAdapter(adaptador);

        } catch (Exception e){
            Log.e("ERROR_ factura", "Imposible obtener factura " + e.getMessage());
            startActivity(new Intent(this, Compra.class));
        }

    }
}
