package com.example.examen_3_parcial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.examen_3_parcial.Controlador.SQLiteConexion;
import com.example.examen_3_parcial.Modelo.BaseDatos;
import com.example.examen_3_parcial.Vista.ListaMedicamentos;

public class MainActivity extends AppCompatActivity {
    EditText descripcion, cantidad, tiempo, periocidad;
    ImageView imagen;
    Bitmap imageBitmap;
    Button bntFotos, btnAgregar, btnListaM;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    static final int PETICION_ACCESO_CAM = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        descripcion=(EditText)findViewById(R.id.editdes);
        cantidad=(EditText)findViewById(R.id.edicant);
        tiempo=(EditText)findViewById(R.id.editime);
        periocidad=(EditText)findViewById(R.id.ediperi);
        imagen=(ImageView)findViewById(R.id.imageView);
        bntFotos=(Button) findViewById(R.id.btnFotos);
        bntFotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                permisos();
            }
        });
        btnAgregar=(Button)findViewById(R.id.btSalvar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarMedicamentos();
            }
        });

        btnListaM=(Button) findViewById(R.id.btnSalvados);
        btnListaM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(), ListaMedicamentos.class);
                startActivity(intent);
            }
        });

    }

    private void AgregarMedicamentos() {
        SQLiteConexion conexion= new SQLiteConexion(this, BaseDatos.NameDataBase, null, 1);
        SQLiteDatabase bd= conexion.getWritableDatabase();

        ContentValues valores= new ContentValues();
        valores.put(BaseDatos.descripcion, descripcion.getText().toString());
        valores.put(BaseDatos.cantidad, cantidad.getText().toString());
        valores.put(BaseDatos.tiempo, tiempo.getText().toString());
        valores.put(BaseDatos.periocidad, periocidad.getText().toString());
        valores.put(BaseDatos.imagen, String.valueOf(imageBitmap));

        Long resultado= bd.insert(BaseDatos.tablapersonas, BaseDatos.id, valores);
        Toast.makeText(getApplicationContext(), "Ingresado" + resultado.toString(), Toast.LENGTH_LONG).show();
        bd.close();

        limpiar();
    }

    private void limpiar() {
        descripcion.setText("");
        cantidad.setText("");
        tiempo.setText("");
        periocidad.setText("");
    }

    private void permisos()
    {
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PETICION_ACCESO_CAM);
        }
        else
        {
            tomarfoto();
        }
    }

    private void tomarfoto() {
        Intent intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PETICION_ACCESO_CAM)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                tomarfoto();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Se necesitan permisos de acceso", Toast.LENGTH_LONG).show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            imagen.setImageBitmap(imageBitmap);
        }
    }
}