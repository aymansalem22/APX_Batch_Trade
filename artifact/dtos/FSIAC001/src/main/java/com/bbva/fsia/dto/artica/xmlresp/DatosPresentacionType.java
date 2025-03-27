package com.bbva.fsia.dto.artica.xmlresp;

import java.io.Serializable;
import java.util.Date;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The DatosPresentacionType class...
 */
@XStreamAlias("DatosPresentacion")
public class DatosPresentacionType implements Serializable  {
	private static final long serialVersionUID = 2931699728946643245L;

	@XStreamAlias("NIFPresentador")
	private String nifPresentador;

	@XStreamAlias("TimestampPresentacion")
	private Date timestampPresentacion;

	public String getNifPresentador() {
		return nifPresentador;
	}

	public void setNifPresentador(String nifPresentador) {
		this.nifPresentador = nifPresentador;
	}

	public Date getTimestampPresentacion() {
		return timestampPresentacion;
	}

	public void setTimestampPresentacion(Date timestampPresentacion) {
		this.timestampPresentacion = timestampPresentacion;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		DatosPresentacionType that = (DatosPresentacionType) o;

		return new EqualsBuilder().append(nifPresentador, that.nifPresentador).append(timestampPresentacion, that.timestampPresentacion).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(nifPresentador).append(timestampPresentacion).toHashCode();
	}

	@Override
	public String toString() {
		return "DatosPresentacionType{" +
				"nifPresentador='" + nifPresentador + '\'' +
				", timestampPresentacion=" + timestampPresentacion +
				'}';
	}
}
