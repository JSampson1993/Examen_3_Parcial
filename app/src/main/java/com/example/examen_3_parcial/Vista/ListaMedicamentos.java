package com.example.examen_3_parcial.Vista;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.examen_3_parcial.Controlador.AdaptadorContacto;
import com.example.examen_3_parcial.Controlador.AuxMedica;
import com.example.examen_3_parcial.Controlador.SQLiteConexion;
import com.example.examen_3_parcial.Modelo.BaseDatos;
import com.example.examen_3_parcial.Modelo.Medicamento;
import com.example.examen_3_parcial.R;

import java.util.ArrayList;

public class ListaMedicamentos extends AppCompatActivity implements AuxMedica {
    SQLiteConexion conexion;
    ListView listaPersona;
    RecyclerView recyclerView;
    ArrayList<Medicamento> lista;
    ArrayList<String> ArregloPersona;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_medicamentos);
        recyclerView=findViewById(R.id.recycle);

        //    empleList = new ArrayList<Persona>();

        conexion= new SQLiteConexion(this, BaseDatos.NameDataBase, null, 1);
        lista =new ArrayList<>();

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        ObtenerLista();
        AdaptadorContacto adp = new AdaptadorContacto(lista);
        recyclerView.setAdapter(adp);
    }
    private void ObtenerLista() {
        SQLiteDatabase db = conexion.getReadableDatabase();

        Medicamento listPersonas = null;

        lista = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + BaseDatos.tablapersonas, null);

        while (cursor.moveToNext()) {
            listPersonas = new Medicamento();
            listPersonas.setId(cursor.getInt(0));
            listPersonas.setDescripcion(cursor.getString(1));
            listPersonas.setCantidad(cursor.getInt(2));
            listPersonas.setTiempo(cursor.getString(3));
            listPersonas.setPeriocidad(cursor.getInt(4));
         //    listPersonas.setImagen(cursor.getBlob(5));


            lista.add(listPersonas);

        }

    }

    public void OpcionEliminar(final Medicamento persona) {
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Mensaje");
        alerta.setMessage("Esta seguro que desea Eliminar? " + persona.getDescripcion() + " " + persona.getCantidad());
        alerta.setCancelable(false);
        alerta.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                eliminarPersona(persona);
            }
        });
        alerta.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerta.show();

    }

    private void eliminarPersona(Medicamento persona) {

        SQLiteConexion sqlLite = new SQLiteConexion(this, BaseDatos.NameDataBase, null, 1);
        SQLiteDatabase sqLiteDatabase = sqlLite.getWritableDatabase();
        String codigo = String.valueOf(persona.getId());
        if(!codigo.isEmpty()){
            sqLiteDatabase.delete("persona","codigo="+codigo,null);
            Toast.makeText(this, "Se Elimino Correctamente", Toast.LENGTH_SHORT).show();
            AdaptadorContacto.eliminarPersona(persona);

            sqLiteDatabase.close();
        }else{
            Toast.makeText(this, "No se ha podido eliminar ", Toast.LENGTH_SHORT).show();
        }


    }


}