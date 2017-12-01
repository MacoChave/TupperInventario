package com.macochave.tupperinventario.datos;

import android.provider.BaseColumns;

/**
 * Created by marco on 28/11/17.
 */

public class Contrato {

    private Contrato() {
    }

    interface ColumnasFamilia
    {
        String FAMILIA = "familia";
    }

    interface ColumnasCategoria
    {
        String CATEGORIA = "categoria";
    }

    interface ColumnasColor
    {
        String COLOR = "color";
    }

    interface ColumnasProducto
    {
        String PRODUCTO = "producto";
        String PATH_IMAGEN = "path_imagen";
    }

    interface ColumnasInventario
    {
        String ID_FAMILIA = "id_familia";
        String ID_CATEGORIA = "id_categoria";
        String ID_COLOR = "id_color";
        String ID_PRODUCTO = "id_producto";
        String EXISTENCIA = "existencia";
        String CAPACIDAD = "capacidad";
        String PRECIO = "precio";
    }

    public static class Familia implements ColumnasFamilia, BaseColumns {}
    public static class Categoria implements ColumnasCategoria, BaseColumns {}
    public static class Color implements ColumnasColor, BaseColumns {}
    public static class Producto implements ColumnasProducto, BaseColumns {}
    public static class Inventario implements ColumnasInventario, BaseColumns {}
}
