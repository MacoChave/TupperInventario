package com.macochave.tupperinventario.datos.tad;

/**
 * Created by marco on 28/11/17.
 */

public class TADInventario {

    private int id_familia;
    private int id_categoria;
    private int id_color;
    private int id_producto;
    private int existencia;
    private double[] capacidad;
    private double precio;

    public TADInventario() {
    }

    public TADInventario(int id_familia, int id_categoria, int id_color, int id_producto, int existencia, double[] capacidad, double precio) {
        this.id_familia = id_familia;
        this.id_categoria = id_categoria;
        this.id_color = id_color;
        this.id_producto = id_producto;
        this.existencia = existencia;
        this.capacidad = capacidad;
        this.precio = precio;
    }

    public int getId_familia() {
        return id_familia;
    }

    public void setId_familia(int id_familia) {
        this.id_familia = id_familia;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getId_color() {
        return id_color;
    }

    public void setId_color(int id_color) {
        this.id_color = id_color;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
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
