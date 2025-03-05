package com.bbva.fsia.dto.artica.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;


@XStreamAlias("Declarado")
public class DeclaredEntity {
    @XStreamAlias("IDRegistroDeclarado")
    private Integer declaredRecordId;

    @XStreamAlias("Clave")
    private String key;

    @XStreamAlias("NombreRazon")
    private String fullName;

    @XStreamAlias("Domicilio")
    private Address address;

    @XStreamAlias("IDMonedas")
    private List<VirtualCurrency> virtualCurrencies;

    public Integer getDeclaredRecordId() {
        return declaredRecordId;
    }

    public void setDeclaredRecordId(Integer declaredRecordId) {
        this.declaredRecordId = declaredRecordId;
    }

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

    public List<VirtualCurrency> getVirtualCurrencies() {
        return virtualCurrencies;
    }

    public void setVirtualCurrencies(List<VirtualCurrency> virtualCurrencies) {
        this.virtualCurrencies = virtualCurrencies;
    }
}
