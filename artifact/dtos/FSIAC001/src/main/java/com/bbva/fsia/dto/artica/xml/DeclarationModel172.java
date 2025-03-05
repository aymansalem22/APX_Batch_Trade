package com.bbva.fsia.dto.artica.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;


@XStreamAlias("dec1:Declaracion")
public class DeclarationModel172 {

    @XStreamAlias("dec1:Cabecera")
    private Header cabecera;

    @XStreamImplicit(itemFieldName = "dec1:Declarado")
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
