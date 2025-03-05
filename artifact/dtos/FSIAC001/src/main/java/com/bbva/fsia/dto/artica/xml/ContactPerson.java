package com.bbva.fsia.dto.artica.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


@XStreamAlias("dec1:PersonaContacto")
public class ContactPerson {

    @XStreamAlias("dec1:Telefono")
    private String phone;

    @XStreamAlias("dec1:NombreRazon")
    private String fullName;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ContactPerson that = (ContactPerson) o;

        return new EqualsBuilder().append(phone, that.phone).append(fullName, that.fullName).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(phone).append(fullName).toHashCode();
    }

    @Override
    public String toString() {
        return "ContactPerson{" +
                "phone='" + phone + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
