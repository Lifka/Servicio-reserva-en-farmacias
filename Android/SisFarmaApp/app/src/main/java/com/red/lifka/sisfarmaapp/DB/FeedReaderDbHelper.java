package com.red.lifka.sisfarmaapp.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    public static final String NOMBRE = "db_farmacias";
    public static final int VERSION = 1;
    private Context context;
    private DbContract db_contact;

    public FeedReaderDbHelper(Context context){
        super(context,NOMBRE, null, VERSION);
        this.context = context;
        db_contact = new DbContract(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(db_contact.CREATE_TABLE_PRODUCTOS);
        db.execSQL(db_contact.CREATE_TABLE_FARMACIAS);
        db.execSQL(db_contact.CREATE_TABLE_CONTACTOS);
        db.execSQL(db_contact.CREATE_TABLE_HISTORIAL);
        db.execSQL(db_contact.CREATE_TABLE_PRODUCTO_FARMACIA);
       /**/ Toast.makeText(context, "DB Started!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       /**/ Toast.makeText(context, "DB Upgraded!", Toast.LENGTH_LONG).show();
    }
}
