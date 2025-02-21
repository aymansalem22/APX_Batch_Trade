package com.bbva.fsia.dto.artica.xml;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.*;
import java.util.List;


public class DeclarationModel172 {



    private Header cabecera;



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
