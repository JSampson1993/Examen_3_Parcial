package com.example.examen_3_parcial.Modelo;

public class BaseDatos {
    /* tablas */
    public static final String tablapersonas = "personas";
    /* Campos */
    public static final String id = "id";
    public static final String descripcion = "descripcion";
    public static final String cantidad = "cantidad";
    public static final String tiempo = "tiempo";
    public static final String periocidad = "periocidad";
    public static final String imagen = "imagen";
    /* tablas - CREATE , DROP */
    public static final String CreateTablePersonas = "CREATE TABLE personas( id INTEGER PRIMARY KEY AUTOINCREMENT, descripcion TEXT, cantidad INTEGER, tiempo String," +
            "periocidad INTEGER, imagen BLOB)";

    public static final String DropTablePersonas = "DROP TABLE IF EXISTS personas";

    /* Creacion del nombre de la base de datos */
    public static final String NameDataBase = "DBExamen";
}
