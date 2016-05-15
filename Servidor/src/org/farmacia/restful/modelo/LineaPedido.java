package org.farmacia.restful.modelo;


public class LineaPedido {
	private int id_producto;
	private int cantidad;
	private int num_linea_pedido;
	private LineaFactura linea_factura;
	
	public LineaPedido(){} //obligatorio para que funcione
	
	public LineaPedido(int num_linea, Producto p, int cantidad) {
		this.setId_producto(p.getId());
		this.setCantidad(cantidad);
		this.setNum_linea_pedido(num_linea);
		
		setLinea_factura(new LineaFactura(num_linea, p, cantidad));
	}


	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public LineaFactura getLinea_factura() {
		return linea_factura;
	}

	public void setLinea_factura(LineaFactura linea_factura) {
		this.linea_factura = linea_factura;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}

	public int getNum_linea_pedido() {
		return num_linea_pedido;
	}

	public void setNum_linea_pedido(int num_linea_pedido) {
		this.num_linea_pedido = num_linea_pedido;
	}


}
