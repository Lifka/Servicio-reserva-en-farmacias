package org.farmacia.restful.servicios;

import java.util.List;

import org.farmacia.restful.db.BaseDeDatos;
import org.farmacia.restful.modelo.Producto;

public class ProductoServicio {
	private List<Producto> listadoProductos = BaseDeDatos.getInstance().getProductos();

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
		listadoProductos.add(p);
		return p;
	}
	
	public Producto updateProducto(Producto p){
		int pos = getPosition(p.getId());
		try{
			listadoProductos.set(pos, p);
		}catch(IndexOutOfBoundsException ioobe){
			return null;
		}
		return p;
	}
	
	public void deleteProducto(int id){
		int pos = getPosition(id);
		listadoProductos.remove(pos);
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
