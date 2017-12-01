package com.macochave.tupperinventario.datos.tad;

/**
 * Created by marco on 28/11/17.
 */

public class TADCategoria {

    private int id;
    private String categoria;

    public TADCategoria() {
    }

    public TADCategoria(int id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
