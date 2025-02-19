package com.bbva.fsia.dto.artica.xml;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "dec:Declaracion")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeclarationModel172 {


    @XmlElement(name = "Cabecera")
    private Header cabecera;


    @XmlElement(name = "Declarado") // ✅ Each item inside will be <Declarado>
    private List<DeclaredEntity> declaredEntities;


    public Header getCabecera() {
        return cabecera;
    }

    public void setCabecera(Header cabecera) {
        this.cabecera = cabecera;
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

        return new EqualsBuilder().append(cabecera, that.cabecera).append(declaredEntities, that.declaredEntities).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(cabecera).append(declaredEntities).toHashCode();
    }

    @Override
    public String toString() {
        return "DeclarationModel172{" +
                "cabecera=" + cabecera +
                ", declaredEntities=" + declaredEntities +
                '}';
    }
}
