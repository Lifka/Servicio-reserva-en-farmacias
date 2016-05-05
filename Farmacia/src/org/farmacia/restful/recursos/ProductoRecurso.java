package org.farmacia.restful.recursos;

import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.farmacia.restful.modelo.Producto;
import org.farmacia.restful.servicios.ProductoServicio;

@Path("/productos")
public class ProductoRecurso {

	ProductoServicio ps = new ProductoServicio();
	
	/* extraer todos los productos ofrecidos por el servicio de productos */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Producto> getProductos(){
		return ps.getProductos();
	}
	
	/* extraer un producto concreto obteniendo la id de la URI */
	@GET
	@Path("/{productoId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Producto getProducto(@PathParam("productoId") int id){
		return ps.getProducto(id);
	}
	
	/* añadir un nuevo producto */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Producto addProducto(Producto p){
		return ps.addProducto(p);
	}
}
