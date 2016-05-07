package com.red.lifka.sisfarmaapp.DB;


import android.app.Activity;
import android.app.ProgressDialog;

import com.red.lifka.sisfarmaapp.Cliente.Departamentos;
import com.red.lifka.sisfarmaapp.Cliente.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class JSONParser {

    private Activity activity;
    private JSONObject jObject;
    private ProgressDialog progressDialog = null;
    private Runnable runReadAndParseJSON;
    private DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
    private DBQuerys db_querys;

    public JSONParser(Activity a){
        activity = a;
        db_querys = new DBQuerys(a);
    }

    public void readAndParseJSONProductos() throws JSONException {

        runReadAndParseJSON = new Runnable() {

            @Override
            public void run() {
                try{
                    readJSONProductos();
                } catch(Exception e){}
            }

        };
        Thread thread = new Thread(null, runReadAndParseJSON,"bgReadJSONProductos");
        thread.start();
        progressDialog = ProgressDialog.show(activity, "Descargando información", "Por favor espere",true);
    }

    private void readJSONProductos() throws JSONException, ParseException {
        jObject = JSONManager.getJSONfromURL("");
        if(jObject != null)
            parseJSONProductos(jObject.getJSONArray("producto"));
        activity.runOnUiThread(returnRes);
    }

    private void parseJSONProductos(JSONArray productos_json) throws JSONException, ParseException {
        ArrayList<Producto> productos = new ArrayList();

        for(int i = 0; i < productos_json.length(); i++){


            int id = productos_json.getJSONObject(i).getInt("id");
            String nombre = productos_json.getJSONObject(i).getString("nombre");
            String descripcion = productos_json.getJSONObject(i).getString("descripcion");
            float precio = (float)productos_json.getJSONObject(i).getDouble("precio");
            String f_creacion = productos_json.getJSONObject(i).getString("f_creacion");;
            String f_caducidad = productos_json.getJSONObject(i).getString("f_caducidad");;
            Departamentos departamento = Departamentos.valueOf(productos_json.getJSONObject(i).getString("departamento"));
            float porcentaje_iva = (float)productos_json.getJSONObject(i).getDouble("porcentaje_iva");

            Date f_creacion_date = format.parse(f_creacion);
            Date f_caducidad_date = format.parse(f_caducidad);


            Producto producto = new Producto(id, nombre, descripcion, precio, f_creacion_date, f_caducidad_date,
                    departamento, porcentaje_iva);
            productos.add(producto);

        }

        db_querys.putProductos(productos);

    }

    private Runnable returnRes = new Runnable(){
        @Override
        public void run() {
            progressDialog.dismiss();
        }
    };
}
