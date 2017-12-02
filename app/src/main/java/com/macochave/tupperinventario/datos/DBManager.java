package com.macochave.tupperinventario.datos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by marco on 1/12/17.
 */

public class DBManager {

    private DBHelper dbHelper;
    private static SQLiteDatabase database;

    public DBManager(Context context) {
        dbHelper = new DBHelper(context);
    }

    public long insertar(String tabla, String nullCollumn, ContentValues values)
    {
        long id;
        database = dbHelper.getWritableDatabase();
        database.beginTransaction();

        try {
            id = database.insertOrThrow(tabla, nullCollumn, values);
            database.setTransactionSuccessful();
        }
        finally {
            database.endTransaction();
        }

        Log.d("DBManager", "insertar: " + tabla + "->id " + id);

        return id;
    }

    public Cursor seleccionar(String tabla, String[] columnas, String seleccion, String[] args)
    {
        Cursor cursor;
        database = dbHelper.getReadableDatabase();
        database.beginTransaction();

        try {
            cursor = database.query(tabla, columnas, seleccion, args, null, null, null);
            database.setTransactionSuccessful();
        }
        finally {
            database.endTransaction();
        }

        Log.d("DBManager", "seleccionar: " + tabla);
        DatabaseUtils.dumpCursor(cursor);

        return cursor;
    }

    public long actualizar(String tabla, ContentValues values, String whereClause, String[] whereArgs)
    {
        long id;
        database = dbHelper.getWritableDatabase();
        database.beginTransaction();

        try {
            id = database.update(tabla, values, whereClause, whereArgs);
            database.setTransactionSuccessful();
        }
        finally {
            database.endTransaction();
        }

        Log.d("DBManager", "actualizar: " + tabla + "->id " + id);

        return id;
    }

    public int eliminar(String tabla, String whereClause, String[] whereArgs)
    {
        int i;
        database = dbHelper.getWritableDatabase();
        database.beginTransaction();

        try {
            i = database.delete(tabla, whereClause, whereArgs);
            database.setTransactionSuccessful();
        }
        finally {
            database.endTransaction();
        }

        Log.d("DBManager", "eliminar: " + tabla + "->res " + i);

        return i;
    }
}
