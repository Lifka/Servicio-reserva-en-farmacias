package org.farmacia.restful.recursos;

import java.util.Collection;
import java.util.Date;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.farmacia.restful.modelo.Departamento;
import org.farmacia.restful.modelo.Producto;
import org.farmacia.restful.servicios.ProductoServicio;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductoRecurso {

	ProductoServicio ps = new ProductoServicio();
	
	/* extraer todos los productos ofrecidos por el servicio de productos */
	@GET
	public Collection<Producto> getProductos(@QueryParam("dep") Departamento d){
		if (d != null && d.toString().length() > 0){
			return ps.getProductosPorDepartamento(d);
		}
		return ps.getProductos();
	}
	
	/* extraer un producto concreto obteniendo la id de la URI */
	@GET
	@Path("/{productoId}")
	public Producto getProducto(@PathParam("productoId") int id){
		return ps.getProducto(id);
	}
	
	/* añadir un nuevo producto */
	@POST
	public Producto addProducto(Producto p){
		p.setF_creacion(new Date());
		p.setF_caducidad(new Date());
		return ps.addProducto(p);
	}
	
	/* eliminar un producto */
	@DELETE
	@Path("/{productoId}")
	public void deleteProducto(@PathParam("productoId") int id){
		System.out.println("Listo para eliminar");
		ps.deleteProducto(id);
	}
	
}
