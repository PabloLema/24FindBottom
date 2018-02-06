package com.example.pablo_lema.a24find.modelo;

/**
 * Created by pablo_lema on 31/1/18.
 */

public class Critica {

    private int id;
    private String comentario;
    private int valoracion;
    private String fechaActual;

    public Critica() {
    }

    public Critica(int id, String comentario, int valoracion, String fechaActual) {
        this.id = id;
        this.comentario = comentario;
        this.valoracion = valoracion;
        this.fechaActual = fechaActual;
    }

    public Critica(String comentario, int valoracion, String fechaActual) {
        this.comentario = comentario;
        this.valoracion = valoracion;
        this.fechaActual = fechaActual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    @Override
    public String toString() {
        return "Critica{" +
                "id=" + id +
                ", comentario='" + comentario + '\'' +
                ", valoracion=" + valoracion +
                ", fechaActual='" + fechaActual + '\'' +
                '}';
    }
}
