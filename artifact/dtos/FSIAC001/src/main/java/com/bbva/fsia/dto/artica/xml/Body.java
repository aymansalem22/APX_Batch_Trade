package com.bbva.fsia.dto.artica.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("soapenv:Body")
public class Body {
    @XStreamAlias("dec:Declaracion")
    private DeclarationModel172 declaration;

    // Getters and setters...
    public DeclarationModel172 getDeclaration() {
        return declaration;
    }

    public void setDeclaration(DeclarationModel172 declaration) {
        this.declaration = declaration;
    }
}
