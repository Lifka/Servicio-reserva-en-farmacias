package org.farmacia.restful.db;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.farmacia.restful.modelo.Producto;

public final class BaseDeDatos {
	private final static BaseDeDatos db = new BaseDeDatos();
	private final List<Producto> productos = new ArrayList<Producto>();
	
	private BaseDeDatos(){
		Producto p1 = new Producto(1,"Producto 1", "Descripcion p 1",1.0f, new GregorianCalendar());
		Producto p2 = new Producto(2,"Producto 2", "Descripcion p 2",2.0f, new GregorianCalendar());
		Producto p3 = new Producto(3,"Producto 3", "Descripcion p 3",3.0f, new GregorianCalendar());
		Producto p4 = new Producto(4,"Producto 4", "Descripcion p 4",4.0f, new GregorianCalendar());
		
		productos.add(p1);
		productos.add(p2);
		productos.add(p3);
		productos.add(p4);
	}
	
	public static BaseDeDatos getInstance(){
		return db;
	}
	
	public List<Producto> getProductos(){
		return productos;
	}
}
