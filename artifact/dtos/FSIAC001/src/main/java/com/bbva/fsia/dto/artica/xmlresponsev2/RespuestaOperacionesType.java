package com.bbva.fsia.dto.artica.xmlresponsev2;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.List;

@XStreamAlias("RespuestaLinea")
public class RespuestaOperacionesType {
    @XStreamAlias("IDRegistroDeclarado")
    private String idRegistroDeclarado;

    @XStreamAlias("EstadoRegistro")
    private String estadoRegistro;

    @XStreamAlias("Errores") // Map the <Errores> wrapper element
    private List<ErrorType> errores;





    public String getIdRegistroDeclarado() {
        return idRegistroDeclarado;
    }

    public void setIdRegistroDeclarado(String idRegistroDeclarado) {
        this.idRegistroDeclarado = idRegistroDeclarado;
    }

    public String getEstadoRegistro() {
        return estadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }


    public List<ErrorType> getErrores() {
        return errores;
    }

    public void setErrores(List<ErrorType> errores) {
        this.errores = errores;
    }

    @Override
    public String toString() {
        return "RespuestaOperacionesType{" +
                "idRegistroDeclarado='" + idRegistroDeclarado + '\'' +
                ", estadoRegistro='" + estadoRegistro + '\'' +
                ", errores=" + errores +
                '}';
    }
}
