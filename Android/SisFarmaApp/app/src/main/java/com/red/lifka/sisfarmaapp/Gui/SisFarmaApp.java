package com.red.lifka.sisfarmaapp.Gui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.red.lifka.sisfarmaapp.Cliente.Departamentos;
import com.red.lifka.sisfarmaapp.Cliente.Farmacia;
import com.red.lifka.sisfarmaapp.Cliente.Producto;
import com.red.lifka.sisfarmaapp.Cliente.ProductoFarmacia;
import com.red.lifka.sisfarmaapp.DB.DBQuerys;
import com.red.lifka.sisfarmaapp.DB.JSONParser;
import com.red.lifka.sisfarmaapp.R;

import org.json.JSONException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class SisFarmaApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sis_farma_app);

        DBQuerys DB = new DBQuerys(this);

        Producto pro = new Producto(0, "wsdfgh", "dfghjk", 2.2f, new Date(), new Date(), Departamentos.HOMEOPATÍA, 3.3f);

        ArrayList<Producto> productos = new ArrayList();
        productos.add(pro);

        DB.putProductos(productos);
        DB.putToHistory(pro);

        ArrayList<Producto> historial = new ArrayList();

        try {
            historial = DB.getHistorial();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Toast.makeText(this, historial.get(0).getF_creacion().toString(), Toast.LENGTH_LONG).show();

        JSONParser jsonparser = new JSONParser(this);
        try {
            jsonparser.readAndParseJSONProductos();
            Toast.makeText(this, "JSON Productos load", Toast.LENGTH_LONG).show();
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Fail", Toast.LENGTH_LONG).show();
        }


      /*  ArrayList<Farmacia> farmacias = DB.getCotactos();
        ArrayList<Farmacia> contactos = DB.getCotactos();
/*
        try {
            ArrayList<Producto> historial = DB.getHistorial();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            ArrayList<ProductoFarmacia> producto_Far = DB.getProductOf("sdfghj");
        } catch (ParseException e) {
            e.printStackTrace();
        }

*/



    }
}
