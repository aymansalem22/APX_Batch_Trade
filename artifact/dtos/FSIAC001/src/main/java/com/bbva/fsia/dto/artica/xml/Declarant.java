package com.bbva.fsia.dto.artica.xml;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Declarant {
    private String taxId;
    private String companyName;
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

        return new EqualsBuilder().append(taxId, declarant.taxId).append(companyName, declarant.companyName).append(contactPerson, declarant.contactPerson).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(taxId).append(companyName).append(contactPerson).toHashCode();
    }

    @Override
    public String toString() {
        return "Declarant{" +
                "taxId='" + taxId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", contactPerson=" + contactPerson +
                '}';
    }
}
