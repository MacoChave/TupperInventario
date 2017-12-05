package com.macochave.tupperinventario.datos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.macochave.tupperinventario.datos.Contrato;
import com.macochave.tupperinventario.datos.DBManager;
import com.macochave.tupperinventario.datos.tad.TADCategoria;

import java.util.ArrayList;

/**
 * Created by marco on 5/12/17.
 */

public class DAOCategoria implements DAO<TADCategoria> {

    DBManager manager;
    ArrayList<TADCategoria> categorias;

    public DAOCategoria(Context context) {
        manager = new DBManager(context);
    }

    @Override
    public ContentValues obtenerValores(TADCategoria item) {
        ContentValues values = new ContentValues();
        values.put(Contrato.Categoria.CATEGORIA, item.getCategoria());

        return values;
    }

    @Override
    public long agregar(TADCategoria item) {
        return manager.insertar("categoria", null, obtenerValores(item));
    }

    @Override
    public ArrayList<TADCategoria> seleccionarTodo() {
        categorias = new ArrayList<>();
        Cursor cursor;
        String[] columnas = new String[]{
                "_id", "categoria"
        };
        cursor = manager.seleccionar("categoria", columnas, null, null);

        if (cursor.moveToFirst())
        {
            do {
                TADCategoria categoria = new TADCategoria();
                categoria.setId(cursor.getInt(0));
                categoria.setCategoria(cursor.getString(1));

                categorias.add(categoria);
            } while (cursor.moveToNext());
        }

        return categorias;
    }

    @Override
    public long actualizar(TADCategoria item) {
        long id;

        String whereClause = "_id = ?";
        String[] whereArgs = new String[] {Float.toString(item.getId())};
        id = manager.actualizar("categoria", obtenerValores(item), whereClause, whereArgs);

        return id;
    }

    @Override
    public int eliminar(TADCategoria item) {
        int i;

        String whereClause = "_id = ?";
        String[] whereArgs = new String[] {Float.toString(item.getId())};
        i = manager.eliminar("categoria", whereClause, whereArgs);

        return i;
    }

    @Override
    public long obtenerID(TADCategoria item) {
        Cursor cursor;

        String[] columnas = new String[]{
                "_id", "categoria"
        };
        String seleccion = "_id = ?";
        String[] args = new String[] {Float.toString(item.getId())};
        cursor = manager.seleccionar("categoria", columnas, seleccion, args);

        long id = 0;

        if (cursor.moveToFirst())
            id = cursor.getInt(0);

        return id;
    }
}
