package com.example.pablo_lema.a24find.modelo;

/**
 * Created by pablo_lema on 31/1/18.
 */

public class Usuario {
    private int id;
    private String nombre;
    private String correo;
    private String profilePicture;

    public Usuario() {
    }

    public Usuario(int id, String nombre, String correo, String profilePicture) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.profilePicture = profilePicture;
    }

    public Usuario(String nombre, String correo, String profilePicture) {
        this.nombre = nombre;
        this.correo = correo;
        this.profilePicture = profilePicture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

}
