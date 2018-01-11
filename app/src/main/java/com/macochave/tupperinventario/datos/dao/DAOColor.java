package com.macochave.tupperinventario.datos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.macochave.tupperinventario.datos.Contrato;
import com.macochave.tupperinventario.datos.DBManager;
import com.macochave.tupperinventario.datos.tad.TADColor;

import java.util.ArrayList;

/**
 * Created by marco on 5/12/17.
 */

public class DAOColor implements DAO<TADColor> {

    DBManager manager;
    ArrayList<TADColor> colors;

    public DAOColor(Context context) {
        manager = new DBManager(context);
    }

    @Override
    public ContentValues obtenerValores(TADColor item) {
        ContentValues values = new ContentValues();
        values.put(Contrato.Color.COLOR, item.getColor());

        return values;
    }

    @Override
    public long agregar(TADColor item) {
        return manager.insertar("color", null, obtenerValores(item));
    }

    @Override
    public ArrayList<TADColor> seleccionarTodo() {
        colors = new ArrayList<>();
        Cursor cursor;
        String[] columnas = new String[]{
                "_id", "color"
        };
        cursor = manager.seleccionar("color", columnas, null, null);

        if (cursor.moveToFirst()) {
            do {
                TADColor familia = new TADColor();
                familia.setId(cursor.getInt(0));
                familia.setColor(cursor.getString(1));

                colors.add(familia);
            } while (cursor.moveToNext());
        }

        return colors;
    }

    @Override
    public long actualizar(TADColor item) {
        long id;

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{Float.toString(item.getId())};
        id = manager.actualizar("color", obtenerValores(item), whereClause, whereArgs);

        return id;
    }

    @Override
    public int eliminar(TADColor item) {
        int i;

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{Float.toString(item.getId())};
        i = manager.eliminar("color", whereClause, whereArgs);

        return i;
    }

    @Override
    public long obtenerID(TADColor item) {
        Cursor cursor;

        String[] columnas = new String[]{
                "_id"
        };
        String seleccion = "_id = ?";
        String[] args = new String[]{Float.toString(item.getId())};
        cursor = manager.seleccionar("color", columnas, seleccion, args);

        long id = 0;

        if (cursor.moveToFirst())
            id = cursor.getInt(0);

        return id;
    }
}
