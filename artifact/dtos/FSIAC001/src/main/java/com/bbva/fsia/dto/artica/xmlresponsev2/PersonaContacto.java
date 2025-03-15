package com.bbva.fsia.dto.artica.xmlresponsev2;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("PersonaContacto")
public  class PersonaContacto {

    @XStreamAlias("Telefono")
    private String telefono;

    @XStreamAlias("ApellidosNombre")
    private String apellidosNombre;

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getApellidosNombre() {
        return apellidosNombre;
    }

    public void setApellidosNombre(String apellidosNombre) {
        this.apellidosNombre = apellidosNombre;
    }


    @Override
    public String toString() {
        return "PersonaContacto{" +
                "telefono='" + telefono + '\'' +
                ", apellidosNombre='" + apellidosNombre + '\'' +
                '}';
    }
}