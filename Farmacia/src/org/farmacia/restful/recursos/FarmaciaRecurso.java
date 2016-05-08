package org.farmacia.restful.recursos;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
}
