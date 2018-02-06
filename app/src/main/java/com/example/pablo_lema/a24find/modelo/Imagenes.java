package com.example.pablo_lema.a24find.modelo;

/**
 * Created by pablo_lema on 29/1/18.
 */

public class Imagenes {
    private int id;
    private String urlImagen;

    public Imagenes() {
    }

    public Imagenes(int id, String urlImagen) {
        this.id = id;
        this.urlImagen = urlImagen;
    }

    public Imagenes(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    @Override
    public String toString() {
        return "Imagenes{" +
                "id=" + id +
                ", urlImagen='" + urlImagen + '\'' +
                '}';
    }
}
