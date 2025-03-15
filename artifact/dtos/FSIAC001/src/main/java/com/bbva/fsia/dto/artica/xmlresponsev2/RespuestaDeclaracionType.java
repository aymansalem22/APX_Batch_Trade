package com.bbva.fsia.dto.artica.xmlresponsev2;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("RespuestaDeclaracion")
public class RespuestaDeclaracionType {

    @XStreamAlias("CSV")
    private String csv;


    @XStreamAlias("DatosPresentacion")
    private DatosPresentacionType datosPresentacion;

    @XStreamAlias("Cabecera")
    private CabeceraDI cabecera;

    @XStreamAlias("EstadoEnvio")
    private String estadoEnvio;

    @XStreamImplicit(itemFieldName = "RespuestaLinea")
    private List<RespuestaOperacionesType> respuestaLinea;

    // Getters and Setters
    public String getCsv() { return csv; }
    public void setCsv(String csv) { this.csv = csv; }

    public DatosPresentacionType getDatosPresentacion() { return datosPresentacion; }
    public void setDatosPresentacion(DatosPresentacionType datosPresentacion) { this.datosPresentacion = datosPresentacion; }

    public CabeceraDI getCabecera() { return cabecera; }
    public void setCabecera(CabeceraDI cabecera) { this.cabecera = cabecera; }

    public String getEstadoEnvio() { return estadoEnvio; }
    public void setEstadoEnvio(String estadoEnvio) { this.estadoEnvio = estadoEnvio; }

    public List<RespuestaOperacionesType> getRespuestaLinea() {
        return respuestaLinea;
    }

    public void setRespuestaLinea(List<RespuestaOperacionesType> respuestaLinea) {
        this.respuestaLinea = respuestaLinea;
    }

    @Override
    public String toString() {
        return "RespuestaDeclaracionType{" +
                "csv='" + csv + '\'' +
                ", datosPresentacion=" + datosPresentacion +
                ", cabecera=" + cabecera +
                ", estadoEnvio='" + estadoEnvio + '\'' +
                ", respuestaLinea=" + respuestaLinea +
                '}';
    }
}



