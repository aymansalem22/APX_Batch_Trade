package com.bbva.fsia.dto.artica.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("dec1:IDDeclarado")
public class DeclaredPerson {

    @XStreamAlias("dec1:Clave")
    private String key;

    @XStreamAlias("dec1:NombreRazon")
    private String fullName;

    @XStreamAlias("dec1:Domicilio")
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
