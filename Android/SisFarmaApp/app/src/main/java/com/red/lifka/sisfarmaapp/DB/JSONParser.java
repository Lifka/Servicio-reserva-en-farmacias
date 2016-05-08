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
    public static final String URL_FARMACIAS = "http://10.0.2.2:8080/Farmacia/api/farmacias";

    public JSONParser(Activity a){
        activity = a;
        db_querys = new DBQuerys(a);
    }

    public void readAndParseJSON() throws JSONException {

                try{
                    String json_productos = json_manager.getJSON(URL_PRODUCTOS);
                    String json_farmacias = json_manager.getJSON(URL_FARMACIAS);

                    JSONArray json_array_productos = null;
                    JSONArray json_array_farmacias = null;

                    try {
                        json_array_productos = new JSONArray(json_productos);
                    } catch (JSONException e) {
                        Log.e("JSONobject", "Malformed JSON " + e.getMessage());
                    }

                    if(json_array_productos != null) {
                        parseJSONProductos(json_array_productos);
                    } else {
                        Log.e("Failed to get productos","There aren't productos");;
                    }

                    try {
                        json_array_farmacias = new JSONArray(json_farmacias);
                    } catch (JSONException e) {
                        Log.e("JSONobject", "Malformed JSON " + e.getMessage());
                    }

                    if(json_array_farmacias != null) {
                        parseJSONFarmacias(json_array_farmacias);
                    } else {
                        Log.e("Failed to get farmacias","There aren't farmacias");;
                    }
                } catch(Exception e){
                    Log.e("Failed",e.getMessage());
                }

    }


    private void parseJSONProductos(JSONArray productos_json) throws JSONException, ParseException {
        ArrayList<Producto> productos = new ArrayList();

        for(int i = 0; i < productos_json.length(); i++){

            int id = productos_json.getJSONObject(i).getInt("id");
            String nombre = productos_json.getJSONObject(i).getString("nombre");
            String descripcion = productos_json.getJSONObject(i).getString("descripcion");
            float precio = (float)productos_json.getJSONObject(i).getDouble("precio_sin_iva");
            String f_creacion = productos_json.getJSONObject(i).getString("f_creacion");
            String f_caducidad = productos_json.getJSONObject(i).getString("f_caducidad");
            Departamentos departamento = Departamentos.valueOf(productos_json.getJSONObject(i).getString("departamento"));
            float porcentaje_iva = (float)productos_json.getJSONObject(i).getDouble("iva");

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



    private void parseJSONFarmacias(JSONArray farmacias_json) throws JSONException {

        // Objetos Farmacia
        ArrayList<Farmacia> farmacias = new ArrayList();
        for(int i = 0; i < farmacias_json.length(); i++){

            // Atributos de una farmacia
            String cif = farmacias_json.getJSONObject(i).getString("cif");
            String nombre = farmacias_json.getJSONObject(i).getString("nombre");
            float latitud = (float)farmacias_json.getJSONObject(i).getDouble("latitud");
            float longitud = (float)farmacias_json.getJSONObject(i).getDouble("longitud");

            Location location = new Location(new String());
            location.setLatitude(latitud);
            location.setLongitude(longitud);

            // Creamos una farmacia
            Farmacia farmacia = new Farmacia(cif, nombre, location);

            // Productos de esa farmacia
            ArrayList<ProductoFarmacia> productos_farmacia = new ArrayList();
            JSONArray stock_json = farmacias_json.getJSONObject(i).getJSONArray("listaStocks");


            for (int j = 0; j < stock_json.length(); j++) {

                int id = farmacias_json.getJSONObject(i).getInt("id_producto");
                int stock = farmacias_json.getJSONObject(i).getInt("stock");

                ProductoFarmacia producto_farmacia = new ProductoFarmacia(id, stock);
                productos_farmacia.add(producto_farmacia);
            }

            // Le metemos los productos a esa farmacia
            farmacia.setProducto(productos_farmacia);

            // Añadimos la farmacia a la lista de farmacias
            farmacias.add(farmacia);

            // Almacenamos los productos de esa farmacia en la db
            db_querys.putProductosFarmacia(productos_farmacia, cif);


        }
        // Almacenamos todas las farmacias leídas
        db_querys.putFarmacias(farmacias);
    }



}
