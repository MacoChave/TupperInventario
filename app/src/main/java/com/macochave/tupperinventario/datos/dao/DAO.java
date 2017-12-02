package com.macochave.tupperinventario.datos.dao;

import java.util.ArrayList;

/**
 * Created by marco on 1/12/17.
 */

interface DAO <T> {
    /*
     * C-REATE  -> long - T
     * R-EAD    -> ArrayList<T> -
     * U-PDATE  -> long - T
     * D-ELETE  -> long - T
     */
    long agregar(T item);
    ArrayList<T> seleccionar();
    long actualizar(T item);
    long eliminar(T item);
    long obtenerID(T item);
}
