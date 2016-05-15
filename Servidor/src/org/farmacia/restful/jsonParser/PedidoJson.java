package org.farmacia.restful.jsonParser;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.farmacia.restful.modelo.FORMA_PAGO;


public class PedidoJson {
	private FORMA_PAGO forma_pago;
	private List<LineaPedidoJson> id_cantidad;
	private String email;
	private String cif;
	
	public PedidoJson(){}
	
	public PedidoJson(String cif, String email, FORMA_PAGO forma_pago, List<LineaPedidoJson> id_cantidad) {
		this.cif = cif;
		this.email = email;
		this.forma_pago = forma_pago;
		this.id_cantidad = id_cantidad;
	}

	public FORMA_PAGO getForma_pago() {
		return forma_pago;
	}

	public void setForma_pago(FORMA_PAGO forma_pago) {
		this.forma_pago = forma_pago;
	}

	public List<LineaPedidoJson> getId_cantidad() {
		return id_cantidad;
	}

	public void setId_cantidad(List<LineaPedidoJson> id_cantidad) {
		this.id_cantidad = id_cantidad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}
}
