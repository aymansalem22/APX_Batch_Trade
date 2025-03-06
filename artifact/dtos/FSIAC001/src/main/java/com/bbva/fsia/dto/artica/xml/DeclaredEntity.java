package com.bbva.fsia.dto.artica.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;


@XStreamAlias("dec1:Declarado")
public class DeclaredEntity {
    @XStreamAlias("dec1:IDRegistroDeclarado")
    private Integer declaredRecordId;


    @XStreamAlias("dec1:IDDeclarado")
    private DeclaredPerson person;

    @XStreamAlias("dec1:Domicilio")
    private Address address;

    @XStreamAlias("dec1:IDMonedas")
    private IDMonedas virtualCurrencies;

    public Integer getDeclaredRecordId() {
        return declaredRecordId;
    }

    public void setDeclaredRecordId(Integer declaredRecordId) {
        this.declaredRecordId = declaredRecordId;
    }

    public DeclaredPerson getPerson() {
        return person;
    }

    public void setPerson(DeclaredPerson person) {
        this.person = person;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public IDMonedas getVirtualCurrencies() {
        return virtualCurrencies;
    }

    public void setVirtualCurrencies(IDMonedas virtualCurrencies) {
        this.virtualCurrencies = virtualCurrencies;
    }

    @Override
    public String toString() {
        return "DeclaredEntity{" +
                "declaredRecordId=" + declaredRecordId +
                ", person=" + person +
                ", address=" + address +
                ", virtualCurrencies=" + virtualCurrencies +
                '}';
    }
}
