package com.bbva.fsia.dto.artica.xmlresp;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The ErrorType class...
 */
@XStreamAlias("Error")
public class ErrorType implements Serializable  {
	private static final long serialVersionUID = 2931699728946643245L;

	@XStreamAlias("CodigoError")
	private String codigoError;
	@XStreamAlias("DescripcionError")
	private String descripcionError;
	@XStreamAlias("ElementoError")
	private String elementoError;

	public String getCodigoError() {
		return codigoError;
	}

	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	public String getDescripcionError() {
		return descripcionError;
	}

	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
	}

	public String getElementoError() {
		return elementoError;
	}

	public void setElementoError(String elementoError) {
		this.elementoError = elementoError;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		ErrorType errorType = (ErrorType) o;

		return new EqualsBuilder().append(codigoError, errorType.codigoError).append(descripcionError, errorType.descripcionError).append(elementoError, errorType.elementoError).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(codigoError).append(descripcionError).append(elementoError).toHashCode();
	}

	@Override
	public String toString() {
		return "ErrorType{" +
				"codigoError='" + codigoError + '\'' +
				", descripcionError='" + descripcionError + '\'' +
				", elementoError='" + elementoError + '\'' +
				'}';
	}
}
