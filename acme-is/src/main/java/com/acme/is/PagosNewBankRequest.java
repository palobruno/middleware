package com.acme.is;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@XmlRootElement(name = "PagosNewBankRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class PagosNewBankRequest implements Serializable {
	@XmlElement(required = true)
	Date fecha;
	@XmlElement(required = true)
	Integer moneda;
	@XmlElement(required = true)
	Double importe;
	@XmlElement(required = true)
	String cuentaOrigen;
	@XmlElement(required = true)
	String cuentaDestino;
	@XmlElement(required = true)
	String observaciones;
	@XmlElement(required = true)
	String referencia;
	
	public Date getFecha() { return fecha; }
	public Integer getMoneda() { return moneda; }
	public Double getImporte() { return importe; }
	public String getCuentaOrigen() { return cuentaOrigen; }
	public String getCuentaDestino() { return cuentaDestino; }
	public String getObservaciones() { return observaciones; }
	public String getReferencia() { return referencia; }
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public void setMoneda(Integer moneda) {
		this.moneda = moneda;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public void setCuentaOrigen(String cuentaOrigen) {
		this.cuentaOrigen = cuentaOrigen;
	}
	public void setCuentaDestino(String cuentaDestino) {
		this.cuentaDestino = cuentaDestino;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	
	
}
