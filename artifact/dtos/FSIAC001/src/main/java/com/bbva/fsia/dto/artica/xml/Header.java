package com.bbva.fsia.dto.artica.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;



@XStreamAlias("dec1:Cabecera")
public class Header {

    @XStreamAlias("dec1:TipoComunicacion")
    private String communicationType;

    @XStreamAlias("dec1:Modelo")
    private String model;

    @XStreamAlias("dec1:Periodo")
    private Period period;

    @XStreamAlias("dec1:IDVersionModelo")
    private String modelVersionId;

    @XStreamAlias("dec1:IDDeclarante")
    private Declarant declarant;


    public String getCommunicationType() {
        return communicationType;
    }

    public void setCommunicationType(String communicationType) {
        this.communicationType = communicationType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public String getModelVersionId() {
        return modelVersionId;
    }

    public void setModelVersionId(String modelVersionId) {
        this.modelVersionId = modelVersionId;
    }

    public Declarant getDeclarant() {
        return declarant;
    }

    public void setDeclarant(Declarant declarant) {
        this.declarant = declarant;
    }

    @Override
    public String toString() {
        return "Header{" +
                "communicationType='" + communicationType + '\'' +
                ", model='" + model + '\'' +
                ", period=" + period +
                ", modelVersionId='" + modelVersionId + '\'' +
                ", declarant=" + declarant +
                '}';
    }
}
