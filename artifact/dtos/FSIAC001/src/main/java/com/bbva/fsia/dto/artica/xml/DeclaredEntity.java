package com.bbva.fsia.dto.artica.xml;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "DeclaredEntity")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeclaredEntity {

    private Double declaredRecordId;
    private String key;
    private String fullName;
    private Address address;
    private VirtualCurrency virtualCurrency;

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

    public VirtualCurrency getVirtualCurrency() {
        return virtualCurrency;
    }

    public void setVirtualCurrency(VirtualCurrency virtualCurrency) {
        this.virtualCurrency = virtualCurrency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DeclaredEntity that = (DeclaredEntity) o;

        return new EqualsBuilder().append(declaredRecordId, that.declaredRecordId).append(key, that.key).append(fullName, that.fullName).append(address, that.address).append(virtualCurrency, that.virtualCurrency).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(declaredRecordId).append(key).append(fullName).append(address).append(virtualCurrency).toHashCode();
    }

    @Override
    public String toString() {
        return "DeclaredEntity{" +
                "declaredRecordId=" + declaredRecordId +
                ", key='" + key + '\'' +
                ", fullName='" + fullName + '\'' +
                ", address=" + address +
                ", virtualCurrency=" + virtualCurrency +
                '}';
    }
}
