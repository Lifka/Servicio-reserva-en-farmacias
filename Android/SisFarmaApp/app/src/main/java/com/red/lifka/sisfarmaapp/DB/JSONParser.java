package com.red.lifka.sisfarmaapp.DB;


import android.app.Activity;
import android.app.ProgressDialog;
import android.location.Location;
import android.util.Log;
import android.widget.Toast;

import com.red.lifka.sisfarmaapp.Cliente.Departamentos;
import com.red.lifka.sisfarmaapp.Cliente.Farmacia;
import com.red.lifka.sisfarmaapp.Cliente.Producto;
import com.red.lifka.sisfarmaapp.Cliente.ProductoFarmacia;
import com.red.lifka.sisfarmaapp.R;

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
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private DBQuerys db_querys;
    JSONManager json_manager = new JSONManager();
    public static final String URL_PRODUCTOS = "http://10.0.2.2:8080/Farmacia/api/productos";

    public JSONParser(Activity a){
        activity = a;
        db_querys = new DBQuerys(a);
    }

    public void readAndParseJSONProductos() throws JSONException {

                try{
                    String json_productos = json_manager.getJSON(URL_PRODUCTOS);

                    JSONArray json_array = null;
                    try {
                        json_array = new JSONArray(json_productos);
                    } catch (JSONException e) {
                        Log.e("JSONobject", "Malformed JSON " + e.getMessage());
                    }

                    if(json_array != null) {
                        parseJSONProductos(json_array);
                    } else {
                        Log.e("Failed to get productos","There aren't productos");;
                    }
                } catch(Exception e){
                    Log.e("Failed to get productos",e.getMessage());
                }

    }


    private void parseJSONProductos(JSONArray productos_json) throws JSONException, ParseException {
        ArrayList<Producto> productos = new ArrayList();

        for(int i = 0; i < productos_json.length(); i++){

            int id = productos_json.getJSONObject(i).getInt("id");
            String nombre = productos_json.getJSONObject(i).getString("nombre");
            String descripcion = productos_json.getJSONObject(i).getString("descripcion");
            float precio = (float)productos_json.getJSONObject(i).getDouble("precio");
            String f_creacion = productos_json.getJSONObject(i).getString("f_creacion");
            String f_caducidad = productos_json.getJSONObject(i).getString("f_caducidad");
            Departamentos departamento = Departamentos.valueOf(productos_json.getJSONObject(i).getString("departamento"));
            float porcentaje_iva = (float)productos_json.getJSONObject(i).getDouble("porcentaje_iva");

            Date f_creacion_date = new Date();
            Date f_caducidad_date = new Date();

            try {
                f_creacion_date = format.parse(f_creacion);
                f_caducidad_date = format.parse(f_caducidad);
            } catch (Exception e){
                /***/Log.e("Date error", e.getMessage());

            }

            /***/Log.d("Leído producto --> ",Integer.toString(i) + " " + nombre);

            Producto producto = new Producto(id, nombre, descripcion, precio, f_creacion_date, f_caducidad_date,
                    departamento, porcentaje_iva);
            productos.add(producto);

        }

        /***/Log.d("Total productos leídos ",Integer.toString(productos.size()));;

        db_querys.putProductos(productos);
    }



    private void parseJSONFarmacias(JSONArray farmacias_json) throws JSONException, ParseException {
        /*

        [{"cif":"1111111",
        "nombre":"1111111",
        "latitud":37.26,
        "longitud":-4.12,

        "listaStocks":
        [{"id_producto":1,"stock":20},
        {"id_producto":2,"stock":100},
        {"id_producto":3,"stock":200},
        {"id_producto":4,"stock":50}]
        },

         */

        for(int i = 0; i < farmacias_json.length(); i++){

            String cif = farmacias_json.getJSONObject(i).getString("cif");
            String nombre = farmacias_json.getJSONObject(i).getString("nombre");
            float latitud = (float)farmacias_json.getJSONObject(i).getDouble("latitud");
            float longitud = (float)farmacias_json.getJSONObject(i).getDouble("longitud");

            Location location = new Location(new String());
            location.setLatitude(latitud);
            location.setLongitude(longitud);

            Farmacia farmacia = new Farmacia(cif, nombre, location);


        }

        /***/Log.d("Total productos leídos ",Integer.toString(productos.size()));;

        db_querys.putProductos(productos);
    }



}
