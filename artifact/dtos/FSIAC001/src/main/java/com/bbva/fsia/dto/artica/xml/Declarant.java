package com.bbva.fsia.dto.artica.xml;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "IDDeclarante")
@XmlAccessorType(XmlAccessType.FIELD)
public class Declarant {
    @XmlElement(name = "NIF") // ✅ Matches <NIF> in XML
    private String taxId;

    @XmlElement(name = "NombreRazon") // ✅ Matches <NombreRazon> in XML
    private String companyName;

    @XmlElement(name = "NIFRepresentante") // ✅ Matches <NIFRepresentante> in XML
    private String representativeTaxId; // New field for representative NIF

    @XmlElement(name = "PersonaContacto")
    private ContactPerson contactPerson;

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRepresentativeTaxId() {
        return representativeTaxId;
    }

    public void setRepresentativeTaxId(String representativeTaxId) {
        this.representativeTaxId = representativeTaxId;
    }

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Declarant declarant = (Declarant) o;

        return new EqualsBuilder().append(taxId, declarant.taxId).append(companyName, declarant.companyName).append(representativeTaxId, declarant.representativeTaxId).append(contactPerson, declarant.contactPerson).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(taxId).append(companyName).append(representativeTaxId).append(contactPerson).toHashCode();
    }

    @Override
    public String toString() {
        return "Declarant{" +
                "taxId='" + taxId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", representativeTaxId='" + representativeTaxId + '\'' +
                ", contactPerson=" + contactPerson +
                '}';
    }
}
