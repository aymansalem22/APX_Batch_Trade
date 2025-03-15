package com.bbva.fsia.dto.artica.xmlresponsev2;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("IDDeclarante")
public  class IDDeclarante {

    @XStreamAlias("NIF")
    private String nif;

    @XStreamAlias("NombreRazon")
    private String nombreRazon;



    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNombreRazon() {
        return nombreRazon;
    }

    public void setNombreRazon(String nombreRazon) {
        this.nombreRazon = nombreRazon;
    }


    @Override
    public String toString() {
        return "IDDeclarante{" +
                "nif='" + nif + '\'' +
                ", nombreRazon='" + nombreRazon + '\'' +
                '}';
    }
}
