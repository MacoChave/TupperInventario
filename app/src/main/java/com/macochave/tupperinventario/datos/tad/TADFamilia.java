package com.macochave.tupperinventario.datos.tad;

/**
 * Created by marco on 28/11/17.
 */

public class TADFamilia {

    private long id;
    private String familia;

    public TADFamilia() {
        id = 0;
        familia = "";
    }

    public TADFamilia(long id, String familia) {
        this.id = id;
        this.familia = familia;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }
}
