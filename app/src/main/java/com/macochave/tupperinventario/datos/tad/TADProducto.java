package com.macochave.tupperinventario.datos.tad;

/**
 * Created by marco on 28/11/17.
 */

public class TADProducto {

    private int id;
    private String producto;
    private String path_imagen;

    public TADProducto() {
    }

    public TADProducto(int id, String producto, String path_imagen) {
        this.id = id;
        this.producto = producto;
        this.path_imagen = path_imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getPath_imagen() {
        return path_imagen;
    }

    public void setPath_imagen(String path_imagen) {
        this.path_imagen = path_imagen;
    }
}
