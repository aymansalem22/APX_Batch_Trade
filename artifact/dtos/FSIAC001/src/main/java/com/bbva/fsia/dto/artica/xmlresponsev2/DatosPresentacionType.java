package com.bbva.fsia.dto.artica.xmlresponsev2;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.time.LocalDateTime;
import java.util.Date;

@XStreamAlias("DatosPresentacion")
public class DatosPresentacionType {
    @XStreamAlias("NIFPresentador")
    private String nifPresentador;

    @XStreamAlias("TimestampPresentacion")
    private Date timestampPresentacion;


    public String getNifPresentador() {
        return nifPresentador;
    }

    public void setNifPresentador(String nifPresentador) {
        this.nifPresentador = nifPresentador;
    }

    public Date getTimestampPresentacion() {
        return timestampPresentacion;
    }

    public void setTimestampPresentacion(Date timestampPresentacion) {
        this.timestampPresentacion = timestampPresentacion;
    }

    @Override
    public String toString() {
        return "DatosPresentacionType{" +
                "nifPresentador='" + nifPresentador + '\'' +
                ", timestampPresentacion=" + timestampPresentacion +
                '}';
    }
}
