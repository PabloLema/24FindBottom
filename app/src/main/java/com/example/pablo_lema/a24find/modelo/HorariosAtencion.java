package com.example.pablo_lema.a24find.modelo;

/**
 * Created by pablo_lema on 28/1/18.
 */

public class HorariosAtencion {

    private int id;
    private String dias;
    private String horaApertura;
    private String horaCierre;

    public HorariosAtencion() {

    }

    public HorariosAtencion(int id, String dias, String horaApertura, String horaCierre) {
        this.id = id;
        this.dias = dias;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public HorariosAtencion(String dias, String horaApertura, String horaCierre) {
        this.dias = dias;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getHoraApertura() {
        return horaApertura;
    }

    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }

    public String getHoraCierre() {
        return horaCierre;
    }

    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }

    @Override
    public String toString() {
        return "HorariosAtencion{" +
                "id=" + id +
                ", dias='" + dias + '\'' +
                ", horaApertura='" + horaApertura + '\'' +
                ", horaCierre='" + horaCierre + '\'' +
                '}';
    }
}
