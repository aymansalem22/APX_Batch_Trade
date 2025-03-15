package com.bbva.fsia.dto.artica.xmlresponsev2;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Cabecera")
public class CabeceraDI {
    @XStreamAlias("TipoComunicacion")
    private String tipoComunicacion;

    @XStreamAlias("Modelo")
    private String modelo;

    @XStreamAlias("Ejercicio")
    private String ejercicio;

    @XStreamAlias("IDVersionModelo")
    private String idVersionModelo;

    @XStreamAlias("IDDeclarante")
    private IDDeclarante idDeclarante;

    public String getTipoComunicacion() {
        return tipoComunicacion;
    }

    public void setTipoComunicacion(String tipoComunicacion) {
        this.tipoComunicacion = tipoComunicacion;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getIdVersionModelo() {
        return idVersionModelo;
    }

    public void setIdVersionModelo(String idVersionModelo) {
        this.idVersionModelo = idVersionModelo;
    }

    public IDDeclarante getIdDeclarante() {
        return idDeclarante;
    }

    public void setIdDeclarante(IDDeclarante idDeclarante) {
        this.idDeclarante = idDeclarante;
    }

    @Override
    public String toString() {
        return "CabeceraDI{" +
                "tipoComunicacion='" + tipoComunicacion + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ejercicio='" + ejercicio + '\'' +
                ", idVersionModelo='" + idVersionModelo + '\'' +
                ", idDeclarante=" + idDeclarante +
                '}';
    }
}
