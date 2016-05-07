package com.red.lifka.sisfarmaapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.widget.Toast;

import com.red.lifka.sisfarmaapp.Cliente.Departamentos;
import com.red.lifka.sisfarmaapp.Cliente.Farmacia;
import com.red.lifka.sisfarmaapp.Cliente.Historial;
import com.red.lifka.sisfarmaapp.Cliente.Producto;
import com.red.lifka.sisfarmaapp.Cliente.ProductoFarmacia;
import com.red.lifka.sisfarmaapp.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.StringTokenizer;

public class DBQuerys {
    private Context context;
    private DbContract dbContract;
    private SQLiteDatabase db;
    FeedReaderDbHelper helper;
    private DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);

    public DBQuerys(Context context){
        helper = new FeedReaderDbHelper(context);
        db = helper.getWritableDatabase();
        this.context = context;
        dbContract = new DbContract(context);
    }

    public ArrayList<ProductoFarmacia> getProductOf(String cif) throws ParseException {
        ArrayList<ProductoFarmacia> productos_farmacia = new ArrayList();



        String query = "select * from " + context.getResources().getString(R.string.table_productos)
                + "," + context.getResources().getString(R.string.table_producto_farmacia)
                + " WHERE " + context.getResources().getString(R.string.table_productos)
                + "." + context.getResources().getString(R.string.column_prod_id) + " = " +
                context.getResources().getString(R.string.table_producto_farmacia)
                + "." + context.getResources().getString(R.string.column_prod_id) + " and "
                + context.getResources().getString(R.string.table_producto_farmacia)
                + "." +  context.getResources().getString(R.string.column_farmacias_cif) +  "=" + '"' + cif + '"';

        Cursor productos_query = db.rawQuery(query, null);

        while(productos_query.moveToNext()) {
            int id = productos_query.getInt(productos_query.getColumnIndex(context.getResources().getString(R.string.column_prod_id)));
            String nombre = productos_query.getString(productos_query.getColumnIndex(context.getResources().getString(R.string.column_prod_nombre)));
            String descripcion = productos_query.getString(productos_query.getColumnIndex(context.getResources().getString(R.string.column_prod_descripcion)));
            float precio = productos_query.getFloat(productos_query.getColumnIndex(context.getResources().getString(R.string.column_prod_precio)));
            String f_creacion = productos_query.getString(productos_query.getColumnIndex(context.getResources().getString(R.string.column_prod_f_creacion)));
            String f_caducidad = productos_query.getString(productos_query.getColumnIndex(context.getResources().getString(R.string.column_prod_f_caducidad)));
            String departamento = productos_query.getString(productos_query.getColumnIndex(context.getResources().getString(R.string.column_prod_departamento)));
            float porcentaje_iva = productos_query.getFloat(productos_query.getColumnIndex(context.getResources().getString(R.string.column_prod_porcentaje_iva)));
            int cantidad = productos_query.getInt(productos_query.getColumnIndex(context.getResources().getString(R.string.column_producto_farmacia_stock)));


            Date f_creacion_date = format.parse(f_creacion);
            Date f_caducidad_date = format.parse(f_caducidad);

            Departamentos dep_enum = Departamentos.valueOf(departamento);

            Producto nuevo_producto = new Producto(id, nombre, descripcion, precio,
                    f_creacion_date, f_caducidad_date, dep_enum, porcentaje_iva);

            ProductoFarmacia producto_farmacia = new ProductoFarmacia(nuevo_producto, cantidad);

            productos_farmacia.add(producto_farmacia);
        }

        return productos_farmacia;
    }

    public ArrayList<Farmacia> getFarmacias(){
        ArrayList<Farmacia> farmacias = new ArrayList();
        Cursor farmacias_query = db.query("Farmacias",null,null,null,null,null,null);

        while(farmacias_query.moveToNext()) {
            String CIF = farmacias_query.getString(farmacias_query.getColumnIndex(context.getResources().getString(R.string.column_farmacias_cif)));
            String nombre = farmacias_query.getString(farmacias_query.getColumnIndex(context.getResources().getString(R.string.column_farmacias_nombre)));
            int latitud = farmacias_query.getInt(farmacias_query.getColumnIndex(context.getResources().getString(R.string.column_farmacias_localizacion_lat)));
            int longitud = farmacias_query.getInt(farmacias_query.getColumnIndex(context.getResources().getString(R.string.column_farmacias_localizacion_long)));

            Location location = new Location(new String());
            location.setLatitude(latitud);
            location.setLongitude(longitud);

            Farmacia farmacia = new Farmacia(CIF, nombre, location);
            farmacias.add(farmacia);
        }

        return farmacias;
    }

    public ArrayList<Producto> getHistorial() throws ParseException {
        ArrayList<Producto> productos = new ArrayList();

        String query = "select * from " + context.getResources().getString(R.string.table_productos)+ "," +
                context.getResources().getString(R.string.table_historial)
                + " WHERE " + context.getResources().getString(R.string.table_productos)
                + "." + context.getResources().getString(R.string.column_prod_id) + " = " +
                context.getResources().getString(R.string.table_historial)
                + "." + context.getResources().getString(R.string.column_prod_id);

        Cursor productos_query = db.rawQuery(query, null);

        while(productos_query.moveToNext()) {
            int id = productos_query.getInt(productos_query.getColumnIndex(context.getResources().getString(R.string.column_prod_id)));
            String nombre = productos_query.getString(productos_query.getColumnIndex(context.getResources().getString(R.string.column_prod_nombre)));
            String descripcion = productos_query.getString(productos_query.getColumnIndex(context.getResources().getString(R.string.column_prod_descripcion)));
            float precio = productos_query.getFloat(productos_query.getColumnIndex(context.getResources().getString(R.string.column_prod_precio)));
            String f_creacion = productos_query.getString(productos_query.getColumnIndex(context.getResources().getString(R.string.column_prod_f_creacion)));
            String f_caducidad = productos_query.getString(productos_query.getColumnIndex(context.getResources().getString(R.string.column_prod_f_caducidad)));
            String departamento = productos_query.getString(productos_query.getColumnIndex(context.getResources().getString(R.string.column_prod_departamento)));
            float porcentaje_iva = productos_query.getFloat(productos_query.getColumnIndex(context.getResources().getString(R.string.column_prod_porcentaje_iva)));

            Date f_creacion_date = format.parse(f_creacion);
            Date f_caducidad_date = format.parse(f_caducidad);

            Departamentos dep_enum = Departamentos.valueOf(departamento);

            Producto nuevo_producto = new Producto(id, nombre, descripcion, precio,
                    f_creacion_date, f_caducidad_date, dep_enum, porcentaje_iva);

            productos.add(nuevo_producto);
        }

        return productos;
    }

    public ArrayList<Farmacia> getCotactos(){
        ArrayList<Farmacia> contactos = new ArrayList();

        String query = "select * from " + context.getResources().getString(R.string.table_contactos)
                + "," + context.getResources().getString(R.string.table_farmacias)
                + " WHERE " + context.getResources().getString(R.string.table_contactos)
                + "." + context.getResources().getString(R.string.column_farmacias_cif) + " = " +
                context.getResources().getString(R.string.table_farmacias) + "." +
                context.getResources().getString(R.string.column_farmacias_cif);

        Cursor farmacias_query = db.rawQuery(query, null);

        while(farmacias_query.moveToNext()) {

            String CIF = farmacias_query.getString(farmacias_query.getColumnIndex(context.getResources().getString(R.string.column_farmacias_cif)));
            String nombre = farmacias_query.getString(farmacias_query.getColumnIndex(context.getResources().getString(R.string.column_farmacias_nombre)));
            int latitud = farmacias_query.getInt(farmacias_query.getColumnIndex(context.getResources().getString(R.string.column_farmacias_localizacion_lat)));
            int longitud = farmacias_query.getInt(farmacias_query.getColumnIndex(context.getResources().getString(R.string.column_farmacias_localizacion_long)));

            Location location = new Location(new String());
            location.setLatitude(latitud);
            location.setLongitude(longitud);

            Farmacia farmacia = new Farmacia(CIF, nombre, location);
            contactos.add(farmacia);
        }

        return contactos;

    }

    public void putToHistory(Producto pro){

        ContentValues pro_values = new ContentValues();

        String f_creacion_str = format.format(pro.getF_creacion());
        String f_caducidad_str = format.format(pro.getF_caducidad());

        pro_values.put(context.getResources().getString(R.string.column_prod_id),pro.getId());

        db.insert(context.getResources().getString(R.string.table_historial), null, pro_values);
    }


    public void putProductosFarmacia(ArrayList<ProductoFarmacia> productos_farmacia, String cif){

        for(int i = 0; i < productos_farmacia.size(); i++) {
            ProductoFarmacia pro = productos_farmacia.get(i);

            ContentValues pro_values = new ContentValues();


            pro_values.put(context.getResources().getString(R.string.column_prod_id), pro.getProduct().getId());
            pro_values.put(context.getResources().getString(R.string.column_producto_farmacia_stock), pro.getStock());
            pro_values.put(context.getResources().getString(R.string.column_farmacias_cif), cif);


            db.insert(context.getResources().getString(R.string.table_farmacias), null, pro_values);
        }
    }

    public void putProductos(ArrayList<Producto> productos){

        for(int i = 0; i < productos.size(); i++) {

            Producto pro = productos.get(i);

            ContentValues pro_values = new ContentValues();

            String f_creacion_str = format.format(pro.getF_creacion());
            String f_caducidad_str = format.format(pro.getF_caducidad());

            pro_values.put(context.getResources().getString(R.string.column_prod_id), pro.getId());
            pro_values.put(context.getResources().getString(R.string.column_prod_nombre), pro.getNombre());
            pro_values.put(context.getResources().getString(R.string.column_prod_descripcion), pro.getDescripcion());
            pro_values.put(context.getResources().getString(R.string.column_prod_precio), pro.getPrecio());
            pro_values.put(context.getResources().getString(R.string.column_prod_f_creacion), f_creacion_str);
            pro_values.put(context.getResources().getString(R.string.column_prod_f_caducidad), f_caducidad_str);
            pro_values.put(context.getResources().getString(R.string.column_prod_departamento), pro.getDepartamento().toString());
            pro_values.put(context.getResources().getString(R.string.column_prod_porcentaje_iva), pro.getPorcentajeIva());

            Toast.makeText(context, pro_values.get(context.getResources().getString(R.string.column_prod_porcentaje_iva)).toString(), Toast.LENGTH_LONG).show();

            db.insert(context.getResources().getString(R.string.table_productos), null, pro_values);
        }
    }

    public void putFarmacias(ArrayList<Farmacia> farmacias){

        for(int i = 0; i < farmacias.size(); i++) {

            Farmacia far = farmacias.get(i);

            ContentValues pro_values = new ContentValues();


            pro_values.put(context.getResources().getString(R.string.column_farmacias_cif), far.getCIF());
            pro_values.put(context.getResources().getString(R.string.column_farmacias_localizacion_lat), far.getLocation().getLatitude());
            pro_values.put(context.getResources().getString(R.string.column_farmacias_localizacion_long), far.getLocation().getLongitude());
            pro_values.put(context.getResources().getString(R.string.column_farmacias_nombre), far.getNombre());


            db.insert(context.getResources().getString(R.string.table_farmacias), null, pro_values);
        }
    }


    public void putToContactos(Farmacia far){

        ContentValues pro_values = new ContentValues();

        pro_values.put(context.getResources().getString(R.string.column_farmacias_cif),far.getCIF());

        db.insert(context.getResources().getString(R.string.table_farmacias), null, pro_values);
    }

}
