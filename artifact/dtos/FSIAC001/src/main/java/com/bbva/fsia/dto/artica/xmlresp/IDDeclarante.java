package com.bbva.fsia.dto.artica.xmlresp;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The IDDeclarante class...
 */
@XStreamAlias("IDDeclarante")
public class IDDeclarante implements Serializable  {
	private static final long serialVersionUID = 2931699728946643245L;

	@XStreamAlias("NIF")
	private String nif;

	@XStreamAlias("NombreRazon")
	private String nombreRazon;


	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getNombreRazon() {
		return nombreRazon;
	}

	public void setNombreRazon(String nombreRazon) {
		this.nombreRazon = nombreRazon;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		IDDeclarante that = (IDDeclarante) o;

		return new EqualsBuilder().append(nif, that.nif).append(nombreRazon, that.nombreRazon).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(nif).append(nombreRazon).toHashCode();
	}

	@Override
	public String toString() {
		return "IDDeclarante{" +
				"nif='" + nif + '\'' +
				", nombreRazon='" + nombreRazon + '\'' +
				'}';
	}
}
