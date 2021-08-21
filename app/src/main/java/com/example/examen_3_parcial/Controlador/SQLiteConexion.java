package com.example.examen_3_parcial.Controlador;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.examen_3_parcial.Modelo.BaseDatos;

public class SQLiteConexion extends SQLiteOpenHelper {
    public SQLiteConexion(Context context, String dbname, SQLiteDatabase.CursorFactory factory, int version){

        super(context, dbname, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(BaseDatos.CreateTablePersonas);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(BaseDatos.DropTablePersonas);
        onCreate(db);
    }
}
