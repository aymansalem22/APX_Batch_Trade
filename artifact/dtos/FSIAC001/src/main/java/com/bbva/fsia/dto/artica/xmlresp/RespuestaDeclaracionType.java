package com.bbva.fsia.dto.artica.xmlresp;

import java.io.Serializable;
import java.util.List;


import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;


/**
 * The RespuestaDeclaracionType class...
 */
@XStreamAlias("RespuestaDeclaracion")
public class RespuestaDeclaracionType implements Serializable  {
	private static final long serialVersionUID = 2931699728946643245L;

	@XStreamAlias("CSV")
	private String csv;


	@XStreamAlias("DatosPresentacion")
	private DatosPresentacionType datosPresentacion;


	@XStreamAlias("Cabecera")
	private CabeceraDI cabecera;

	@XStreamAlias("EstadoEnvio")
	private String estadoEnvio;

	@XStreamImplicit(itemFieldName = "RespuestaLinea")
	private List<RespuestaOperacionesType> respuestaLinea;

	public String getCsv() {
		return csv;
	}

	public void setCsv(String csv) {
		this.csv = csv;
	}

	public DatosPresentacionType getDatosPresentacion() {
		return datosPresentacion;
	}

	public void setDatosPresentacion(DatosPresentacionType datosPresentacion) {
		this.datosPresentacion = datosPresentacion;
	}

	public CabeceraDI getCabecera() {
		return cabecera;
	}

	public void setCabecera(CabeceraDI cabecera) {
		this.cabecera = cabecera;
	}

	public String getEstadoEnvio() {
		return estadoEnvio;
	}

	public void setEstadoEnvio(String estadoEnvio) {
		this.estadoEnvio = estadoEnvio;
	}

	public List<RespuestaOperacionesType> getRespuestaLinea() {
		return respuestaLinea;
	}

	public void setRespuestaLinea(List<RespuestaOperacionesType> respuestaLinea) {
		this.respuestaLinea = respuestaLinea;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		RespuestaDeclaracionType that = (RespuestaDeclaracionType) o;

		return new EqualsBuilder().append(csv, that.csv).append(datosPresentacion, that.datosPresentacion).append(cabecera, that.cabecera).append(estadoEnvio, that.estadoEnvio).append(respuestaLinea, that.respuestaLinea).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(csv).append(datosPresentacion).append(cabecera).append(estadoEnvio).append(respuestaLinea).toHashCode();
	}

	@Override
	public String toString() {
		return "RespuestaDeclaracionType{" +
				"csv='" + csv + '\'' +
				", datosPresentacion=" + datosPresentacion +
				", cabecera=" + cabecera +
				", estadoEnvio='" + estadoEnvio + '\'' +
				", respuestaLinea=" + respuestaLinea +
				'}';
	}
}
