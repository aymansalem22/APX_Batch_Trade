package com.bbva.fsia.dto.artica.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("IDDeclarado")
public class DeclaredPerson {

    @XStreamAlias("Clave")
    private String key;

    @XStreamAlias("NombreRazon")
    private String fullName;

    @XStreamAlias("Domicilio")
    private Address address;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
