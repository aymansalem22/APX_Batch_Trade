package com.bbva.fsia.dto.artica.xmlresp;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The RespuestaOperacionesType class...
 */
@XStreamAlias("RespuestaLinea")
public class RespuestaOperacionesType implements Serializable  {
	private static final long serialVersionUID = 2931699728946643245L;

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
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		RespuestaOperacionesType that = (RespuestaOperacionesType) o;

		return new EqualsBuilder().append(idRegistroDeclarado, that.idRegistroDeclarado).append(estadoRegistro, that.estadoRegistro).append(errores, that.errores).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(idRegistroDeclarado).append(estadoRegistro).append(errores).toHashCode();
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
