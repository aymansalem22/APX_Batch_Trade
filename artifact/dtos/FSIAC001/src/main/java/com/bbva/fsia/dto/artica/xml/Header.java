package com.bbva.fsia.dto.artica.xml;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Header {
    private String communicationType;
    private String model;
    private String fiscalYear;
    private String modelVersionId;
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

    public String getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(String fiscalYear) {
        this.fiscalYear = fiscalYear;
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
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Header header = (Header) o;

        return new EqualsBuilder().append(communicationType, header.communicationType).append(model, header.model).append(fiscalYear, header.fiscalYear).append(modelVersionId, header.modelVersionId).append(declarant, header.declarant).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(communicationType).append(model).append(fiscalYear).append(modelVersionId).append(declarant).toHashCode();
    }

    @Override
    public String toString() {
        return "Header{" +
                "communicationType='" + communicationType + '\'' +
                ", model='" + model + '\'' +
                ", fiscalYear='" + fiscalYear + '\'' +
                ", modelVersionId='" + modelVersionId + '\'' +
                ", declarant=" + declarant +
                '}';
    }
}
