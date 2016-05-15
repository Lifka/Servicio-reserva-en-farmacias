package org.farmacia.restful.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Pedido{
	private int num_pedido;
	private Calendar fecha_pedido;
	private List<LineaPedido> lineasPedido = new ArrayList<LineaPedido>();
	private List<Integer> productos_sin_stock = new ArrayList<Integer>();
	private Factura factura;
	private String email_usuario;
	private Farmacia farmacia;
	
	public Pedido(){}
	
	public Pedido(Farmacia f){
		this.num_pedido = -1;
		this.farmacia = f;
		this.fecha_pedido = new GregorianCalendar();
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
	
	public void addLineaPedido(LineaPedido l){
		if (l!=null)
			this.lineasPedido.add(l);
	}
	
	
	public boolean createLineaPedido(Producto p, int cantidad){
		if (this.farmacia != null){
			boolean encontrado = false;
			boolean conStock = true;
			List<StockProducto> stocks = this.farmacia.getListaStocks();
			
			for (int i=0; i< stocks.size() && !encontrado;i++){
				encontrado = (p.getId() == this.farmacia.getListaStocks().get(i).getId_producto());
				if (encontrado){
					conStock =  (cantidad <= stocks.get(i).getStock());
					if (conStock){
						LineaPedido lp = new LineaPedido(lineasPedido.size()+1,p,cantidad);
						farmacia.reducirStockProducto(p.getId(), cantidad);
						lineasPedido.add(lp);
						factura.addLineaFactura(lp.getLinea_factura());
						return true;
					}else
						productos_sin_stock.add(p.getId());
				}
			}
		}
		return false;
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


	public Farmacia getFarmacia() {
		return farmacia;
	}

	public void setFarmacia(Farmacia farmacia) {
		this.farmacia = farmacia;
	}
	
}
