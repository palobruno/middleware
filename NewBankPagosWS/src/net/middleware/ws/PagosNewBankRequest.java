package net.middleware.ws;

import java.util.Date;
import java.io.Serializable;

public class PagosNewBankRequest implements Serializable {
	Date fecha;
	Integer moneda;
	Double importe;
	String cuentaOrigen;
	String cuentaDestino;
	String observaciones;
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
