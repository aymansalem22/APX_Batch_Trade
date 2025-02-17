package com.bbva.fsia.dto.artica.xml;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "DeclaredEntity")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeclaredEntity {

    @XmlElement(name = "IDRegistroDeclarado")
    private Double declaredRecordId;
    @XmlElement(name = "Clave")
    private String key;
    @XmlElement(name = "NombreRazon")
    private String fullName;
    @XmlElement(name = "Domicilio")
    private Address address;
    @XmlElementWrapper(name = "IDMonedas") // ✅ Wraps all currencies inside <IDMonedas>
    @XmlElement(name = "MonedaVirtual") // ✅ Ensures each currency is inside <MonedaVirtual>
    private List<VirtualCurrency> virtualCurrencies;


    public Double getDeclaredRecordId() {
        return declaredRecordId;
    }

    public void setDeclaredRecordId(Double declaredRecordId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DeclaredEntity that = (DeclaredEntity) o;

        return new EqualsBuilder().append(declaredRecordId, that.declaredRecordId).append(key, that.key).append(fullName, that.fullName).append(address, that.address).append(virtualCurrencies, that.virtualCurrencies).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(declaredRecordId).append(key).append(fullName).append(address).append(virtualCurrencies).toHashCode();
    }

    @Override
    public String toString() {
        return "DeclaredEntity{" +
                "declaredRecordId=" + declaredRecordId +
                ", key='" + key + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address=" + address +
                ", virtualCurrencies=" + virtualCurrencies +
                '}';
    }
}
