package org.farmacia.restful.recursos;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/mirecurso")
public class MiRecurso {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String saludar(){
		return "Hola, esto es un recurso restfulll";
	}
}
