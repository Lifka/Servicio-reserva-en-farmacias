package org.farmacia.restful.modelo;

public class StockProducto {
	private int id_producto;
	private int stock;
	
	public StockProducto(){}
	
	public StockProducto(int id_producto, int stock){
		this.setId_producto(id_producto);
		this.stock = stock;
	}
	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getId_producto() {
		return id_producto;
	}

	public void setId_producto(int id_producto) {
		this.id_producto = id_producto;
	}
}
