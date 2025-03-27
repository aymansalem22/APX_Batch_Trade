package com.bbva.fsia.dto.artica.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.List;



public class DeclarationModel172 {


    private Header header;



    @XStreamAlias("dec1:Declarado")
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
    public String toString() {
        return "DeclarationModel172{" +
                "header=" + header +
                ", declaredEntities=" + declaredEntities +
                '}';
    }
}
