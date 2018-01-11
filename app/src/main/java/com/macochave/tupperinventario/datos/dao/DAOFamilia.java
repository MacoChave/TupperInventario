package com.macochave.tupperinventario.datos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.macochave.tupperinventario.datos.Contrato;
import com.macochave.tupperinventario.datos.DBManager;
import com.macochave.tupperinventario.datos.tad.TADFamilia;

import java.util.ArrayList;

/**
 * Created by marco on 1/12/17.
 */

public class DAOFamilia implements DAO<TADFamilia> {

    DBManager manager;
    private ArrayList<TADFamilia> familias;

    public DAOFamilia(Context context) {
        manager = new DBManager(context);
    }

    @Override
    public ContentValues obtenerValores(TADFamilia item) {
        ContentValues values = new ContentValues();
        values.put(Contrato.Familia.FAMILIA, item.getFamilia());

        return values;
    }

    @Override
    public long agregar(TADFamilia item) {
        return manager.insertar("familia", null, obtenerValores(item));
    }

    @Override
    public ArrayList<TADFamilia> seleccionarTodo() {
        familias = new ArrayList<>();
        Cursor cursor;
        String[] columnas = new String[]{
                "_id", "familia"
        };
        cursor = manager.seleccionar("familia", columnas, null, null);

        if (cursor.moveToFirst()) {
            do {
                TADFamilia familia = new TADFamilia();
                familia.setId(cursor.getInt(0));
                familia.setFamilia(cursor.getString(1));

                familias.add(familia);
            } while (cursor.moveToNext());
        }

        return familias;
    }

    @Override
    public long actualizar(TADFamilia item) {
        long id;

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{Float.toString(item.getId())};
        id = manager.actualizar("familia", obtenerValores(item), whereClause, whereArgs);

        return id;
    }

    @Override
    public int eliminar(TADFamilia item) {
        int i;

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{Float.toString(item.getId())};
        i = manager.eliminar("familia", whereClause, whereArgs);

        return i;
    }

    @Override
    public long obtenerID(TADFamilia item) {
        Cursor cursor;

        String[] columnas = new String[]{
                "_id"
        };
        String seleccion = "_id = ?";
        String[] args = new String[]{Float.toString(item.getId())};
        cursor = manager.seleccionar("familia", columnas, seleccion, args);

        long id = 0;

        if (cursor.moveToFirst())
            id = cursor.getInt(0);

        return id;
    }
}
