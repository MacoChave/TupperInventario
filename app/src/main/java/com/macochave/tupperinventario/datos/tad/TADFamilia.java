package com.macochave.tupperinventario.datos.tad;

/**
 * Created by marco on 28/11/17.
 */

public class TADFamilia {

    private int id;
    private String familia;

    public TADFamilia() {
    }

    public TADFamilia(int id, String familia) {
        this.id = id;
        this.familia = familia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }
}
