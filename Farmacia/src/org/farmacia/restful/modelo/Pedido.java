package org.farmacia.restful.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Pedido{
	private int num_pedido;
	private Calendar fecha_pedido;
	private List<LineaPedido> lineasPedido;
	private List<Integer> productos_sin_stock;
	private Factura factura;
	private String email_usuario;
	private String cif_farmacia;
	
	public Pedido(){
		this.cif_farmacia = null;
		this.fecha_pedido = new GregorianCalendar();
		this.lineasPedido = new ArrayList<LineaPedido>();
		this.setProductos_sin_stock(new ArrayList<Integer>());
		this.factura = new Factura();
		this.setEmail_usuario(null);
	}
	
	public int getNum_pedido() {
		return num_pedido;
	}

	public void setNum_pedido(int num_pedido) {
		this.num_pedido = num_pedido;
	}

	public Calendar getFecha_pedido() {
		return fecha_pedido;
	}

	public void setFecha_pedido(Calendar fecha_pedido) {
		this.fecha_pedido = fecha_pedido;
	}
	
	public List<LineaPedido> getLineasPedido() {
		return lineasPedido;
	}

	public void setLineasPedido(List<LineaPedido> lineasPedido) {
		this.lineasPedido = lineasPedido;
	}
	
	
	public void createLineaPedido(Producto p, int cantidad){
		if (this.cif_farmacia != null){
			LineaPedido lp = new LineaPedido(lineasPedido.size()+1,p,cantidad);
			lineasPedido.add(lp); // añadimos una nueva linea de pedido
			factura.addLineaFactura(lp.getLinea_factura()); // una línea de pedido genera una línea de factura
		}
	}
	
	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}


	public String getEmail_usuario() {
		return email_usuario;
	}

	public void setEmail_usuario(String email_usuario) {
		this.email_usuario = email_usuario;
	}

	public List<Integer> getProductos_sin_stock() {
		return productos_sin_stock;
	}

	public void setProductos_sin_stock(List<Integer> productos_sin_stock) {
		this.productos_sin_stock = productos_sin_stock;
	}

	public String getCif_farmacia() {
		return cif_farmacia;
	}

	public void setCif_farmacia(String cif_farmacia) {
		this.cif_farmacia = cif_farmacia;
	}
	
}
