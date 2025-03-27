package com.bbva.fsia.dto.artica.xmlresp;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * The CabeceraDI class...
 */
@XStreamAlias("Cabecera")
public class CabeceraDI implements Serializable  {
	private static final long serialVersionUID = 2931699728946643245L;

	@XStreamAlias("TipoComunicacion")
	private String tipoComunicacion;

	@XStreamAlias("Modelo")
	private String modelo;

	@XStreamAlias("Ejercicio")
	private String ejercicio;

	@XStreamAlias("IDVersionModelo")
	private String idVersionModelo;

	@XStreamAlias("IDDeclarante")
	private IDDeclarante idDeclarante;

	public String getTipoComunicacion() {
		return tipoComunicacion;
	}

	public void setTipoComunicacion(String tipoComunicacion) {
		this.tipoComunicacion = tipoComunicacion;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}

	public String getIdVersionModelo() {
		return idVersionModelo;
	}

	public void setIdVersionModelo(String idVersionModelo) {
		this.idVersionModelo = idVersionModelo;
	}

	public IDDeclarante getIdDeclarante() {
		return idDeclarante;
	}

	public void setIdDeclarante(IDDeclarante idDeclarante) {
		this.idDeclarante = idDeclarante;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		CabeceraDI that = (CabeceraDI) o;

		return new EqualsBuilder().append(tipoComunicacion, that.tipoComunicacion).append(modelo, that.modelo).append(ejercicio, that.ejercicio).append(idVersionModelo, that.idVersionModelo).append(idDeclarante, that.idDeclarante).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(tipoComunicacion).append(modelo).append(ejercicio).append(idVersionModelo).append(idDeclarante).toHashCode();
	}

	@Override
	public String toString() {
		return "CabeceraDI{" +
				"tipoComunicacion='" + tipoComunicacion + '\'' +
				", modelo='" + modelo + '\'' +
				", ejercicio='" + ejercicio + '\'' +
				", idVersionModelo='" + idVersionModelo + '\'' +
				", idDeclarante=" + idDeclarante +
				'}';
	}
}
