
package com.red.lifka.sisfarmaapp.DB;

import android.content.Context;

import com.red.lifka.sisfarmaapp.R;

public final class DbContract {

    public String CREATE_TABLE_PRODUCTOS;
    public String CREATE_TABLE_PRODUCTO_FARMACIA;
    public String CREATE_TABLE_FARMACIAS;
    public String CREATE_TABLE_CONTACTOS;
    public String CREATE_TABLE_HISTORIAL;

    public DbContract(Context context) {

        CREATE_TABLE_PRODUCTOS =
                "create table " + context.getResources().getString(R.string.table_productos) + "(" +
                        context.getResources().getString(R.string.column_prod_id) + " " + context.getResources().getString(R.string.type_int) + " primary key," +
                        context.getResources().getString(R.string.column_prod_nombre) + " " + context.getResources().getString(R.string.type_string) + " not null," +
                        context.getResources().getString(R.string.column_prod_descripcion) + " " + context.getResources().getString(R.string.type_string) + " not null," +
                        context.getResources().getString(R.string.column_prod_precio) + " " + context.getResources().getString(R.string.type_float) + " not null," +
                        context.getResources().getString(R.string.column_prod_f_creacion) + " " + context.getResources().getString(R.string.type_date) + " not null," +
                        context.getResources().getString(R.string.column_prod_f_caducidad) + " " + context.getResources().getString(R.string.type_date) + " not null," +
                        context.getResources().getString(R.string.column_prod_departamento) + " " + context.getResources().getString(R.string.type_string) + " not null," +
                        context.getResources().getString(R.string.column_prod_porcentaje_iva) + " " + context.getResources().getString(R.string.type_float) + " not null)";

        CREATE_TABLE_PRODUCTO_FARMACIA =
                "create table " + context.getResources().getString(R.string.table_producto_farmacia) + "(" +
                        context.getResources().getString(R.string.column_farmacias_cif) + " " + context.getResources().getString(R.string.type_string) + "," +
                        context.getResources().getString(R.string.column_prod_id) + " " + context.getResources().getString(R.string.type_int) + ", " +
                       " PRIMARY KEY (" +  context.getResources().getString(R.string.column_farmacias_cif) + ", " + context.getResources().getString(R.string.column_prod_id) + "))";


        CREATE_TABLE_FARMACIAS =
                "create table " +  context.getResources().getString(R.string.table_farmacias) + "(" +
                        context.getResources().getString(R.string.column_farmacias_cif) + " " + context.getResources().getString(R.string.type_string) + " primary key," +
                        context.getResources().getString(R.string.column_farmacias_nombre) + " " + context.getResources().getString(R.string.type_string) + " not null," +
                        context.getResources().getString(R.string.column_farmacias_localizacion_lat) + " " + context.getResources().getString(R.string.type_float) + " not null," +
                        context.getResources().getString(R.string.column_farmacias_localizacion_long) + " " + context.getResources().getString(R.string.type_float) + " not null)";

        CREATE_TABLE_CONTACTOS =
                "create table " + context.getResources().getString(R.string.table_contactos) + "(" +
                        context.getResources().getString(R.string.column_farmacias_cif) + " " + context.getResources().getString(R.string.type_string) + " primary key)";

        CREATE_TABLE_HISTORIAL =
                "create table " + context.getResources().getString(R.string.table_historial) + "(" +
                        context.getResources().getString(R.string.column_prod_id) + " " + context.getResources().getString(R.string.type_int) + " primary key)";


    }


}
