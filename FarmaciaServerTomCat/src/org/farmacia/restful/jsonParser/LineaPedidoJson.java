package org.farmacia.restful.jsonParser;

public class LineaPedidoJson {
	private int id;
	private int cantidad;
	
	public LineaPedidoJson(){}
	
	public LineaPedidoJson(int id, int cantidad) {
		this.id = id;
		this.cantidad = cantidad;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
}
