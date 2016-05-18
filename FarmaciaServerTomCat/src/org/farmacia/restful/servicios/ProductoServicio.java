package org.farmacia.restful.servicios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.farmacia.restful.db.DatabaseHelper;
import org.farmacia.restful.modelo.Departamento;
import org.farmacia.restful.modelo.FactoriaAbstracta;
import org.farmacia.restful.modelo.FactoriaProductos;
import org.farmacia.restful.modelo.Producto;

public class ProductoServicio {
	private Collection<Producto> listadoProductos;
	
	public ProductoServicio(){
		listadoProductos = DatabaseHelper.getInstance().getProductosArrayList();
		if (listadoProductos == null || listadoProductos.isEmpty()){
			FactoriaAbstracta factoria = new FactoriaProductos();
			factoria.createObjects();
			listadoProductos = DatabaseHelper.getInstance().getProductosArrayList();
		}
	}

	public Collection<Producto> getProductos(){
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
		DatabaseHelper.getInstance().addProductoDB(p);
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
}
