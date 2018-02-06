package com.example.pablo_lema.a24find.modelo;

/**
 * Created by pablo_lema on 31/1/18.
 */

public class Telefono {
    private int id;
    private String numeroTelefono;
    private String tipo;

    public Telefono() {
    }

    public Telefono(int id, String numeroTelefono, String tipo) {
        this.id = id;
        this.numeroTelefono = numeroTelefono;
        this.tipo = tipo;
    }

    public Telefono(String numeroTelefono, String tipo) {
        this.numeroTelefono = numeroTelefono;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Telefono{" +
                "id=" + id +
                ", numeroTelefono='" + numeroTelefono + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
