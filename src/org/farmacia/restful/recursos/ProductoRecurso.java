package org.farmacia.restful.recursos;

import java.util.GregorianCalendar;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.farmacia.restful.modelo.Producto;
import org.farmacia.restful.servicios.ProductoServicio;

@Path("/productos")
public class ProductoRecurso {

	ProductoServicio ps = new ProductoServicio();
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Producto> getProductos(){
		return ps.getProductos();
	}
}
