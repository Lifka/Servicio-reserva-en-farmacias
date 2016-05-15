package org.farmacia.restful.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.farmacia.restful.db.DatabaseHelper;
import org.farmacia.restful.modelo.Departamento;
import org.farmacia.restful.modelo.Producto;

public class ProductoServicio {
	private final List<Producto> listadoProductos = DatabaseHelper.getInstance().getProductosArrayList();

	public List<Producto> getProductos(){
		return listadoProductos;
	}
	
	public Producto getProducto(int id){
		for (Producto p : listadoProductos){
			if (p.getId() == id)
				return p;
		}
		return null;
	}
	
	public Producto addProducto(Producto p){
		p.setId(getMaximoId());
		p.setF_creacion(new GregorianCalendar());
		Calendar f_cad = new GregorianCalendar();
		f_cad.set(2020, 1, 1, 9, 0, 0);
		p.setF_caducidad(f_cad);
		System.out.println("Añadiendo producto");
		DatabaseHelper.getInstance().addProductoDB(p);
		
		return p;
	}
	
	public Producto updateProducto(Producto p){
		p.setF_creacion(new GregorianCalendar());
		p.setF_caducidad(new GregorianCalendar());
		int pos = getPosition(p.getId());
		try{
			listadoProductos.set(pos, p);
		}catch(IndexOutOfBoundsException ioobe){
			return null;
		}
		return p;
	}
	
	public void deleteProducto(int id){
		DatabaseHelper.getInstance().deleteProductoDB(id);
	}
	
	public List<Producto> getProductosPorDepartamento(Departamento d){
		List<Producto> listadoResultado = new ArrayList<Producto>();
		for (Producto p : listadoProductos){
			if (p.getDepartamento() == d)
				listadoResultado.add(p);
		}
		return listadoResultado;
	}
	
	private int getMaximoId(){
		int size = listadoProductos.size();
		if (size > 0)
			return (listadoProductos.get(size-1).getId() + 1);
		else
			return 1;
	}
	
	private int getPosition(int id){
		for (int i=0; i<listadoProductos.size(); i++){
			if (listadoProductos.get(i).getId() == id)
				return i;
		}
		return -1;
	}
}
