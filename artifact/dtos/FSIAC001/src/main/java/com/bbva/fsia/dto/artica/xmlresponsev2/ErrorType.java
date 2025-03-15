package com.bbva.fsia.dto.artica.xmlresponsev2;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("Error")
public class ErrorType {
    @XStreamAlias("CodigoError")
    private String codigoError;
    @XStreamAlias("DescripcionError")
    private String descripcionError;
    @XStreamAlias("ElementoError")
    private String elementoError;


    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

    public String getElementoError() {
        return elementoError;
    }

    public void setElementoError(String elementoError) {
        this.elementoError = elementoError;
    }

    @Override
    public String toString() {
        return "ErrorType{" +
                "codigoError='" + codigoError + '\'' +
                ", descripcionError='" + descripcionError + '\'' +
                ", elementoError='" + elementoError + '\'' +
                '}';
    }
}