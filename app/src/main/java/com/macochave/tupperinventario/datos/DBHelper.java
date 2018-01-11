package com.macochave.tupperinventario.datos;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by marco on 28/11/17.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "inventario.db";
    private static final int DB_VERSION = 1;
    private final Context context;

    public DBHelper(Context _context) {
        super(_context, DB_NAME, null, DB_VERSION);
        context = _context;
    }

    @Override
    public void onOpen(SQLiteDatabase database) {
        super.onOpen(database);

        if (!database.isReadOnly())
            database.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        try {
            database.execSQL(String.format(
                    "CREATE TABLE %s (" +
                            "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "%s TEXT NOT NULL)",
                    Tablas.FAMILIA,
                    Contrato.Familia._ID,
                    Contrato.Familia.FAMILIA
            ));
            database.execSQL(String.format(
                    "CREATE TABLE %s (" +
                            "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "%s TEXT NOT NULL)",
                    Tablas.CATEGORIA,
                    Contrato.Categoria._ID,
                    Contrato.Categoria.CATEGORIA
            ));
            database.execSQL(String.format(
                    "CREATE TABLE %s (" +
                            "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "%s TEXT NOT NULL)",
                    Tablas.COLOR,
                    Contrato.Color._ID,
                    Contrato.Color.COLOR
            ));
            database.execSQL(String.format(
                    "CREATE TABLE %s (" +
                            "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "%S TEXT NOT NULL, " +
                            "%s TEXT)",
                    Tablas.PRODUCTO,
                    Contrato.Producto._ID,
                    Contrato.Producto.PRODUCTO,
                    Contrato.Producto.PATH_IMAGEN
            ));
            database.execSQL(String.format(
                    "CREATE TABLE %s (" +
                            "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "%s INTEGER, " +
                            "%s INTEGER, " +
                            "%s INTEGER, " +
                            "%s INTEGER, " +
                            "%s INTEGER, " +
                            "%s TEXT, " +
                            "%s DECIMAL(10, 2))",
                    Tablas.INVENTARIO,
                    Contrato.Inventario._ID,
                    Contrato.Inventario.ID_FAMILIA,
                    Contrato.Inventario.ID_CATEGORIA,
                    Contrato.Inventario.ID_COLOR,
                    Contrato.Inventario.ID_PRODUCTO,
                    Contrato.Inventario.EXISTENCIA,
                    Contrato.Inventario.CAPACIDAD,
                    Contrato.Inventario.PRECIO
            ));

            String col_view = "inventario._id, inventario.existencia, inventario.capacidad, inventario.precio, " +
                    "familia.familia, categoria.categoria, color.color, producto.producto, producto.path_imagen";
            String join_view = "INNER JOIN familia ON familia._id = inventario.id_familia " +
                    "INNER JOIN categoria ON categoria._id = inventario.id_categoria " +
                    "INNER JOIN color ON color._id = inventario.id_color " +
                    "INNER JOIN producto ON producto._id = inventario.id_producto";

            database.execSQL(String.format(
                    "CREATE VIEW %s AS SELECT %s FROM %s %s",
                    Tablas.VISTA_REPORTE,
                    col_view,
                    Tablas.INVENTARIO,
                    join_view
            ));
        } catch (SQLException ex) {
            Log.e("CREATE DB", "onCreate: " + ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int i, int i1) {
        try {
            database.execSQL("DROP TABLE IF EXIST " + Tablas.FAMILIA);
            database.execSQL("DROP TABLE IF EXIST " + Tablas.CATEGORIA);
            database.execSQL("DROP TABLE IF EXIST " + Tablas.COLOR);
            database.execSQL("DROP TABLE IF EXIST " + Tablas.PRODUCTO);
            database.execSQL("DROP TABLE IF EXIST " + Tablas.INVENTARIO);

            onCreate(database);
        } catch (SQLException ex) {
            Log.e("UPDATE DB", "onUpgrade: " + ex.getMessage());
        }
    }

    interface Tablas {
        String FAMILIA = "familia";
        String CATEGORIA = "categoria";
        String COLOR = "color";
        String PRODUCTO = "producto";
        String INVENTARIO = "inventario";
        String VISTA_REPORTE = "view_reporte";
    }

    interface Referencias {
        String ID_FAMILIA = String.format(
                "REFERENCES %s(%s) ON DELETE CASCADE",
                Tablas.FAMILIA, Contrato.Familia._ID
        );
        String ID_CATEGORIA = String.format(
                "REFERENCES %s(%s) ON DELETE CASCADE",
                Tablas.CATEGORIA, Contrato.Categoria._ID
        );
        String ID_COLOR = String.format(
                "REFERENCES %s(%s) ON DELETE CASCADE",
                Tablas.COLOR, Contrato.Color._ID
        );
        String ID_PRODUCTO = String.format(
                "REFERENCES %s(%s) ON DELETE CASCADE",
                Tablas.PRODUCTO, Contrato.Producto._ID
        );
    }
}
