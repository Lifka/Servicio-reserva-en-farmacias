package org.farmacia.restful.recursos;


import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.farmacia.restful.modelo.Farmacia;
import org.farmacia.restful.servicios.FarmaciaServicio;

@Path("/farmacias")
@Produces(MediaType.APPLICATION_JSON)
public class FarmaciaRecurso {
	
	FarmaciaServicio fs = new FarmaciaServicio();
	
	/* extraer todos los productos ofrecidos por el servicio de productos */
	@GET
	public List<Farmacia> getFarmacias(){
		return fs.getFarmacias();
	}
	
	@POST
	public void nuevaFarmacia(Farmacia f){
		fs.nuevaFarmacia(f);
	}
	
	@DELETE
	@Path("/{farmaciaCIF}")
	public void deleteFarmacia(@PathParam("farmaciaCIF") String cif){
		fs.deleteFarmacia(cif);
	}
}
