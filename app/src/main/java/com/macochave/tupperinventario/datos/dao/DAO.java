package com.macochave.tupperinventario.datos.dao;

import android.content.ContentValues;

import java.util.ArrayList;

/**
 * Created by marco on 1/12/17.
 */

interface DAO <T>{
    /*
     * C-REATE  -> long - T
     * R-EAD    -> ArrayList<T> -
     * U-PDATE  -> long - T
     * D-ELETE  -> long - T
     */
    ContentValues obtenerValores(T item);
    long agregar(T item);
    ArrayList<T> seleccionarTodo();
    long actualizar(T item);
    int eliminar(T item);
    long obtenerID(T item);
}
