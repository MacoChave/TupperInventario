package com.macochave.tupperinventario.datos.tad;

/**
 * Created by marco on 28/11/17.
 */

public class TADInventario {

    private long id_familia;
    private long id_categoria;
    private long id_color;
    private long id_producto;
    private int existencia;
    private double[] capacidad;
    private double precio;

    public TADInventario() {
    }

    public TADInventario(long id_familia, long id_categoria, long id_color, long id_producto, int existencia, double[] capacidad, double precio) {
        this.id_familia = id_familia;
        this.id_categoria = id_categoria;
        this.id_color = id_color;
        this.id_producto = id_producto;
        this.existencia = existencia;
        this.capacidad = capacidad;
        this.precio = precio;
    }

    public long getId_familia() {
        return id_familia;
    }

    public void setId_familia(long id_familia) {
        this.id_familia = id_familia;
    }

    public long getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(long id_categoria) {
        this.id_categoria = id_categoria;
    }

    public long getId_color() {
        return id_color;
    }

    public void setId_color(long id_color) {
        this.id_color = id_color;
    }

    public long getId_producto() {
        return id_producto;
    }

    public void setId_producto(long id_producto) {
        this.id_producto = id_producto;
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
