package com.example.pablo_lema.a24find.modelo;

import java.util.List;

/**
 * Created by pablo_lema on 26/1/18.
 */

public class Local {

    private int id;
    private String nombre;
    private String direccion;
    private String descripcion;
    private String lat;
    private String lng;
    private double promedioCalificaciones;
    private List<HorariosAtencion> horariosAtencion;
    private List<Imagenes> imagenesList;
    private List<Telefono> telefonoList;
    private List<Critica> criticaList;

    public Local() {
    }

    public Local(int id, String nombre, String direccion, String descripcion, String lat, String lng, List<HorariosAtencion> horariosAtencion, List<Imagenes> imagenesList, List<Telefono> telefonoList, List<Critica> criticaList) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.lat = lat;
        this.lng = lng;
        this.horariosAtencion = horariosAtencion;
        this.imagenesList = imagenesList;
        this.telefonoList = telefonoList;
        this.criticaList = criticaList;
    }

    public Local(String nombre, String direccion, String descripcion, String lat, String lng, List<HorariosAtencion> horariosAtencion, List<Imagenes> imagenesList, List<Telefono> telefonoList, List<Critica> criticaList) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.lat = lat;
        this.lng = lng;
        this.horariosAtencion = horariosAtencion;
        this.imagenesList = imagenesList;
        this.telefonoList = telefonoList;
        this.criticaList = criticaList;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public List<HorariosAtencion> getHorariosAtencion() {
        return horariosAtencion;
    }

    public void setHorariosAtencion(List<HorariosAtencion> horariosAtencion) {
        this.horariosAtencion = horariosAtencion;
    }

    public List<Imagenes> getImagenesList() {
        return imagenesList;
    }

    public void setImagenesList(List<Imagenes> imagenesList) {
        this.imagenesList = imagenesList;
    }

    public List<Telefono> getTelefonoList() {
        return telefonoList;
    }

    public void setTelefonoList(List<Telefono> telefonoList) {
        this.telefonoList = telefonoList;
    }

    public List<Critica> getCriticaList() {
        return criticaList;
    }

    public void setCriticaList(List<Critica> criticaList) {
        this.criticaList = criticaList;
    }

    public double getPromedioCalificaciones() {
        return promedioCalificaciones;
    }

    public void setPromedioCalificaciones(double promedioCalificaciones) {
        this.promedioCalificaciones = promedioCalificaciones;
    }

    @Override
    public String toString() {
        return "Local{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", promedioCalificaciones=" + promedioCalificaciones +
                ", horariosAtencion=" + horariosAtencion +
                ", imagenesList=" + imagenesList +
                ", telefonoList=" + telefonoList +
                ", criticaList=" + criticaList +
                '}';
    }
}
