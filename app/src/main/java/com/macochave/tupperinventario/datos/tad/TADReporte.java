package com.macochave.tupperinventario.datos.tad;

/**
 * Created by marco on 28/11/17.
 */

public class TADReporte {

    private long id;
    private String familia;
    private String categoria;
    private String color;
    private String producto;
    private int existencia;
    private double[] capacidad;
    private double precio;

    public TADReporte() {
    }

    public TADReporte(long id, String familia, String categoria, String color, String producto, int existencia, double[] capacidad, double precio) {
        this.id = id;
        this.familia = familia;
        this.categoria = categoria;
        this.color = color;
        this.producto = producto;
        this.existencia = existencia;
        this.capacidad = capacidad;
        this.precio = precio;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public double[] getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String value) {
        String[] values = value.split("-");
        capacidad[0] = Double.parseDouble(values[0]);
        capacidad[1] = Integer.parseInt(values[1]);
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
