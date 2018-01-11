package com.macochave.tupperinventario.datos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.macochave.tupperinventario.datos.Contrato;
import com.macochave.tupperinventario.datos.DBManager;
import com.macochave.tupperinventario.datos.tad.TADProducto;

import java.util.ArrayList;

/**
 * Created by marco on 5/12/17.
 */

public class DAOProducto implements DAO<TADProducto> {

    DBManager manager;
    ArrayList<TADProducto> productos;

    public DAOProducto(Context context) {
        manager = new DBManager(context);
    }

    @Override
    public ContentValues obtenerValores(TADProducto item) {
        ContentValues values = new ContentValues();
        values.put(Contrato.Producto.PRODUCTO, item.getProducto());
        values.put(Contrato.Producto.PATH_IMAGEN, item.getPath_imagen());

        return values;
    }

    @Override
    public long agregar(TADProducto item) {
        return manager.insertar("producto", null, obtenerValores(item));
    }

    @Override
    public ArrayList<TADProducto> seleccionarTodo() {
        productos = new ArrayList<>();
        Cursor cursor;
        String[] columnas = new String[]{
                "_id", "producto", "path_imagen"
        };
        cursor = manager.seleccionar("producto", columnas, null, null);

        if (cursor.moveToFirst()) {
            do {
                TADProducto producto = new TADProducto();
                producto.setId(cursor.getInt(0));
                producto.setProducto(cursor.getString(1));
                producto.setPath_imagen(cursor.getString(2));

                productos.add(producto);
            } while (cursor.moveToNext());
        }

        return productos;
    }

    @Override
    public long actualizar(TADProducto item) {
        long id;

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{Float.toString(item.getId())};
        id = manager.actualizar("producto", obtenerValores(item), whereClause, whereArgs);

        return id;
    }

    @Override
    public int eliminar(TADProducto item) {
        int i;

        String whereClause = "_id = ?";
        String[] whereArgs = new String[]{Float.toString(item.getId())};
        i = manager.eliminar("producto", whereClause, whereArgs);

        return i;
    }

    @Override
    public long obtenerID(TADProducto item) {
        Cursor cursor;

        String[] columnas = new String[]{
                "_id"
        };
        String seleccion = "_id = ?";
        String[] args = new String[]{Float.toString(item.getId())};
        cursor = manager.seleccionar("producto", columnas, seleccion, args);

        long id = 0;

        if (cursor.moveToFirst())
            id = cursor.getInt(0);

        return id;
    }
}
