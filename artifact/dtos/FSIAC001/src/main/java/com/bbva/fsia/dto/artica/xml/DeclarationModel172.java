package com.bbva.fsia.dto.artica.xml;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "Declaracion")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeclarationModel172 {
    private Header header;
    private List<DeclaredEntity> declaredEntities;

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public List<DeclaredEntity> getDeclaredEntities() {
        return declaredEntities;
    }

    public void setDeclaredEntities(List<DeclaredEntity> declaredEntities) {
        this.declaredEntities = declaredEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        DeclarationModel172 that = (DeclarationModel172) o;

        return new EqualsBuilder().append(header, that.header).append(declaredEntities, that.declaredEntities).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(header).append(declaredEntities).toHashCode();
    }

    @Override
    public String toString() {
        return "DeclarationModel172{" +
                "header=" + header +
                ", declaredEntities=" + declaredEntities +
                '}';
    }
}
