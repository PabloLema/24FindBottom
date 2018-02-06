package com.example.pablo_lema.a24find.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pablo_lema on 1/2/18.
 */

public class Parqueadero {

    private int id;
    private String nombre;
    private double tarifa;
    private String direccion;
    private int valoracion;
    private List<Imagenes> imagenesListP;
    private String lat;
    private String lng;
    private int puestosDisponibles;
    private List<Telefono> telefonoListP;

    public Parqueadero() {
    }

    public Parqueadero(int id, String nombre, double tarifa, String direccion, int valoracion, List<Imagenes> imagenesListP, String lat, String lng, int puestosDisponibles, List<Telefono> telefonoListP) {
        this.id = id;
        this.nombre = nombre;
        this.tarifa = tarifa;
        this.direccion = direccion;
        this.valoracion = valoracion;
        this.imagenesListP = imagenesListP;
        this.lat = lat;
        this.lng = lng;
        this.puestosDisponibles = puestosDisponibles;
        this.telefonoListP = telefonoListP;
    }

    public Parqueadero(String nombre, double tarifa, String direccion, int valoracion, List<Imagenes> imagenesListP, String lat, String lng, int puestosDisponibles, List<Telefono> telefonoListP) {
        this.nombre = nombre;
        this.tarifa = tarifa;
        this.direccion = direccion;
        this.valoracion = valoracion;
        this.imagenesListP = imagenesListP;
        this.lat = lat;
        this.lng = lng;
        this.puestosDisponibles = puestosDisponibles;
        this.telefonoListP = telefonoListP;
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

    public double getTarifa() {
        return tarifa;
    }

    public void setTarifa(double tarifa) {
        this.tarifa = tarifa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getValoracion() {
        return valoracion;
    }

    public void setValoracion(int valoracion) {
        this.valoracion = valoracion;
    }

    public List<Imagenes> getImagenesListP() {
        return imagenesListP;
    }

    public void setImagenesListP(List<Imagenes> imagenesListP) {
        this.imagenesListP = imagenesListP;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getPuestosDisponibles() {
        return puestosDisponibles;
    }

    public void setPuestosDisponibles(int puestosDisponibles) {
        this.puestosDisponibles = puestosDisponibles;
    }

    public List<Telefono> getTelefonoListP() {
        return telefonoListP;
    }

    public void setTelefonoListP(List<Telefono> telefonoListP) {
        this.telefonoListP = telefonoListP;
    }

    @Override
    public String toString() {
        return "Parqueadero{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", tarifa=" + tarifa +
                ", direccion='" + direccion + '\'' +
                ", valoracion=" + valoracion +
                ", imagenesListP=" + imagenesListP +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", puestosDisponibles=" + puestosDisponibles +
                ", telefonoListP=" + telefonoListP +
                '}';
    }
}
