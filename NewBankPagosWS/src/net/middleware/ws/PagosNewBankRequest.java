package net.middleware.ws;

import java.util.Date;

public class PagosNewBankRequest extends Object {
	Date fecha;
	Integer moneda;
	Double importe;
	String cuentaOrigen;
	String cuentaDestino;
	String observaciones;
	String referencia;
}
