package com.bbva.fsia.dto.artica.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;




@XStreamAlias("dec1:IDDeclarante")
public class Declarant {

    @XStreamAlias("dec1:NIF")
    private String taxId;

    @XStreamAlias("dec1:NombreRazon")
    private String companyName;

    @XStreamAlias("dec1:NIFRepresentante")
    private String representativeTaxId;


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

//    public ContactPerson getContactPerson() {
//        return contactPerson;
//    }
//
//    public void setContactPerson(ContactPerson contactPerson) {
//        this.contactPerson = contactPerson;
//    }


    @Override
    public String toString() {
        return "Declarant{" +
                "taxId='" + taxId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", representativeTaxId='" + representativeTaxId + '\'' +
                '}';
    }
}
