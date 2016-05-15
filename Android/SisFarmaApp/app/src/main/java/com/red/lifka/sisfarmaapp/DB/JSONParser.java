package com.red.lifka.sisfarmaapp.DB;


import android.content.Context;
import android.location.Location;
import android.util.Log;

import com.red.lifka.sisfarmaapp.Cliente.Departamentos;
import com.red.lifka.sisfarmaapp.Cliente.FactoriaProducto;
import com.red.lifka.sisfarmaapp.Cliente.FactoriaProductosFarmacias;
import com.red.lifka.sisfarmaapp.Cliente.Factura;
import com.red.lifka.sisfarmaapp.Cliente.Farmacia;
import com.red.lifka.sisfarmaapp.Cliente.LineaFactura;
import com.red.lifka.sisfarmaapp.Cliente.Producto;
import com.red.lifka.sisfarmaapp.Cliente.ProductoGenerico;
import com.red.lifka.sisfarmaapp.Cliente.TipoPago;
import com.red.lifka.sisfarmaapp.Cliente.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class JSONParser {

    private Context context;
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private DBQueries db_querys;
    JSONManager json_manager = new JSONManager();
    public static String URL_PRODUCTOS = "http://10.0.2.2:8080/Farmacia/api/productos";
    public static String URL_FARMACIAS = "http://10.0.2.2:8080/Farmacia/api/farmacias";
    public static String URL_FACTURAS = "http://10.0.2.2:8080/Farmacia/api/pedidos";
    public static String URL_LOGIN = "http://10.0.2.2:8080/Farmacia/api/login";
    public static String URL_REGISTER = "http://10.0.2.2:8080/Farmacia/api/register";
    public static String URL_UPDATE_USER = "http://10.0.2.2:8080/Farmacia/api/updateUser";
    private FactoriaProducto factoria_productos;

    public JSONParser(Context a, String server){

        URL_PRODUCTOS = "http://" + server + "/Farmacia/api/productos";
        URL_FARMACIAS = "http://" + server + "/Farmacia/api/farmacias";
        URL_FACTURAS = "http://" + server + "/Farmacia/api/pedidos";
        URL_LOGIN = "http://" + server + "/Farmacia/api/login";
        URL_REGISTER = "http://" + server + "/Farmacia/api/register";
        URL_UPDATE_USER = "http://" + server + "/Farmacia/api/updateUser";

        context = a;
        db_querys = new DBQueries(a);
    }

    public void readAndParseJSON() {

                try{
                    String json_productos = json_manager.getJSON(URL_PRODUCTOS);
                    String json_farmacias = json_manager.getJSON(URL_FARMACIAS);

                    JSONArray json_array_productos = null;
                    JSONArray json_array_farmacias = null;

                    try {
                        json_array_productos = new JSONArray(json_productos);
                    } catch (JSONException e) {
                        Log.e("ERROR_ JSONobject", "Malformed JSON " + e.getMessage());
                    }

                    if(json_array_productos != null) {
                        parseJSONProductos(json_array_productos);
                    } else {
                        Log.e("ERROR_ Failed to get","There aren't productos");;
                    }

                    try {
                        json_array_farmacias = new JSONArray(json_farmacias);
                    } catch (JSONException e) {
                        Log.e("ERROR_ JSONobject", "Malformed JSON " + e.getMessage());
                    }

                    if(json_array_farmacias != null) {
                        parseJSONFarmacias(json_array_farmacias);
                    } else {
                        Log.e("ERROR_ Failed to get","There aren't farmacias");
                    }
                } catch(Exception e){
                    Log.e("Failed",e.getMessage());
                }

    }


    private void parseJSONProductos(JSONArray productos_json) throws JSONException, ParseException {
        ArrayList<ProductoGenerico> productos = new ArrayList();
        factoria_productos = new FactoriaProductosFarmacias();

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
                /***/Log.e("ERROR_ Date error", e.getMessage());

            }

            /***/Log.d("Leído producto --> ",Integer.toString(i) + " " + nombre);

            ProductoGenerico producto = factoria_productos.factoriaProducto(id, nombre, descripcion, precio, f_creacion_date, f_caducidad_date,
                    departamento, porcentaje_iva);

            productos.add(producto);

        }

        /***/Log.d("Total productos leídos ",Integer.toString(productos.size()));;

        db_querys.putProductos(productos);
    }



    private void parseJSONFarmacias(JSONArray farmacias_json) throws JSONException, ParseException {

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
            Farmacia farmacia = new Farmacia(cif, nombre, location, context);

            // Productos de esa farmacia
            ArrayList<Integer> productos_ids = new ArrayList();
            ArrayList<ProductoGenerico> productos_farmacia = new ArrayList();
            JSONArray stock_json = farmacias_json.getJSONObject(i).getJSONArray("listaStocks");


            for (int j = 0; j < stock_json.length(); j++) {

                int id = stock_json.getJSONObject(j).getInt("id_producto");

                productos_ids.add(id);
            }

            productos_farmacia = db_querys.getProductosByIds(productos_ids);

            // Le metemos los productos a esa farmacia
            farmacia.setProducto(productos_farmacia);

            // Añadimos la farmacia a la lista de farmacias
            farmacias.add(farmacia);

            // Almacenamos los productos de esa farmacia en la db
            db_querys.addProductoFarmacia(productos_farmacia, cif);


        }
        // Almacenamos todas las farmacias leídas
        db_querys.putFarmacias(farmacias);
    }


    public ArrayList<Integer> sendFactura(Factura factura, String usuario){

        JSONObject factura_json = new JSONObject();
        JSONArray lista = new JSONArray();
        JSONObject linea_json;

        ArrayList<Integer> ids = factura.getListaID();
        HashMap<Integer, LineaFactura> lineas = factura.getProductos();


        try {

            for (int i = 0; i < factura.getProductos().size(); i++){
                linea_json = new JSONObject();
                linea_json.put("id", ids.get(i));
                linea_json.put("cantidad", lineas.get(ids.get(i)).getCantidad());

                lista.put(linea_json);
            }

            factura_json.put("id_cantidad", lista);
            factura_json.put("cif", factura.getCif());
            factura_json.put("forma_pago", factura.getFormaPago().toString());
            factura_json.put("email", usuario);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        String response = json_manager.postJSON(factura_json, URL_FACTURAS);

        JSONObject json_object = null;
        JSONArray json_array = null;
        ArrayList<Integer> productos_sin_stock = new ArrayList();

        try {
            json_object = new JSONObject(response);
            json_array = json_object.getJSONArray("productos_sin_stock");
        } catch (JSONException e) {
            Log.e("ERROR_ JSONobject", "Malformed JSON " + e.getMessage());
        }

        if (json_array != null && json_array.length() > 0){
            for(int i = 0; i < json_array.length(); i++)
                try {
                    productos_sin_stock.add(json_array.getInt(i));
                } catch (Exception e){
                    Log.e("ERROR_ JSONobject", "Malformed JSON " + e.getMessage());
                }
        }

        return productos_sin_stock;

    }


    private JSONObject loginToJSON(String email, String pass){

        JSONObject login = new JSONObject();
        Crypt crypt = new Crypt();
        pass = crypt.encrypt(pass);

        try {

            login.put("email", email);
            login.put("pass", pass);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return login;
    }

    private JSONObject registroToJSON(String email, String pass, String dni, String nombre_completo, TipoPago pago){

        JSONObject login = new JSONObject();
        Crypt crypt = new Crypt();
        pass = crypt.encrypt(pass);

        try {

            login.put("email", email);
            login.put("pass", pass);
            login.put("dni", dni);
            login.put("nombre_completo", nombre_completo);
            login.put("pago", pago.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return login;
    }

    private JSONObject updateUserToJSON(String email, String nombre_completo, TipoPago pago){

        JSONObject login = new JSONObject();

        try {

            login.put("email", email);
            login.put("nombre_completo", nombre_completo);
            login.put("pago", pago.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return login;
    }

    private JSONObject updatePassToJSON(String email, String pass){

        JSONObject login = new JSONObject();

        try {

            login.put("email", email);
            login.put("pass", pass);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return login;
    }


    private Usuario parseLogin(JSONObject json) {

        Usuario usuario = null;

        try {

            if (json != null && json.getBoolean("logeado")) {
                JSONObject user_json = json.getJSONObject("user");

                usuario = new Usuario(
                        user_json.getString("email"),
                        user_json.getString("nombre_completo"),
                        user_json.getString("dni"),
                        TipoPago.valueOf(user_json.getString("pago")),
                        context
                        );


            } else {
                Log.e("ERROR_ Failed to get", "login data");
            }

        } catch (Exception e){
            Log.e("ERROR_: JSON register", e.getMessage() + "\n JSONObject -> " + json.toString());
        }

        return usuario;
    }


    private Boolean parseBoolean(JSONObject json) {

        boolean bool = false;

        try {

            if (json != null) {
                bool = json.getBoolean("bool");
            } else {
                Log.e("ERROR_ Failed to get", "login data");
            }

        } catch (Exception e){
            Log.e("ERROR: JSON", e.getMessage());
        }

        return bool;
    }

    public Usuario login(String email, String pass) {
        JSONObject json_send = loginToJSON(email, pass);

        String response = json_manager.postJSON(json_send, URL_LOGIN);
        JSONObject json_object = null;

        try {
            json_object = new JSONObject(response);
        } catch (JSONException e) {
            Log.e("ERROR_ JSONobject", "Malformed JSON " + e.getMessage());
        }

        return parseLogin(json_object);

    }

    public Usuario registro(String email, String pass, String dni, String nombre_completo, TipoPago pago) {
        JSONObject json_send = registroToJSON(email, pass, dni, nombre_completo, pago);

        String response = json_manager.postJSON(json_send, URL_REGISTER);
        JSONObject json_object = null;

        try {
            json_object = new JSONObject(response);
        } catch (JSONException e) {
            Log.e("ERROR_ JSONobject", "Malformed JSON " + e.getMessage());
        }

        return parseLogin(json_object);
    }



    public boolean updateUser(String email, String nombre_completo, TipoPago pago) {
        JSONObject json_send = updateUserToJSON(email, nombre_completo, pago);

        String response = json_manager.postJSON(json_send, URL_UPDATE_USER);
        JSONObject json_object = null;

        try {
            json_object = new JSONObject(response);
        } catch (JSONException e) {
            Log.e("ERROR_ JSONobject", "Malformed JSON " + e.getMessage());
        }

        return parseBoolean(json_object);
    }



    public boolean updatePass(String email, String pass) {
        JSONObject json_send = updatePassToJSON(email, pass);

        String response = json_manager.postJSON(json_send, URL_UPDATE_USER);
        JSONObject json_object = null;

        try {
            json_object = new JSONObject(response);
        } catch (JSONException e) {
            Log.e("ERROR_ JSONobject", "Malformed JSON " + e.getMessage());
        }

        return parseBoolean(json_object);
    }
}


