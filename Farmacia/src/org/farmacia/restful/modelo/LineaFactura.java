package org.farmacia.restful.modelo;

public class LineaFactura {
	private Producto producto;
	private int cantidad;
	private int numLinea;
	
	public LineaFactura(){}
	
	public LineaFactura(int num_linea, Producto p, int cantidad){
		this.numLinea = num_linea;
		this.producto = p;
		this.cantidad = cantidad;
	}

	public float getTotalSinIva() {
		return producto.getPrecio_sin_iva()*cantidad;
	}

	public float getTotalConIVA() {
		return producto.getPrecion_con_iva()*cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto p) {
		this.producto = p;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getNumLinea() {
		return numLinea;
	}

	public void setNumLinea(int numLinea) {
		this.numLinea = numLinea;
	}

}
