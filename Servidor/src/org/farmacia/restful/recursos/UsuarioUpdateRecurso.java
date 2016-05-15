package org.farmacia.restful.recursos;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.farmacia.restful.jsonParser.BooleanJson;
import org.farmacia.restful.modelo.Usuario;
import org.farmacia.restful.servicios.UsuarioService;

@Path("/updateUser")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioUpdateRecurso {
	UsuarioService user_service = new UsuarioService();
	
	@POST
	public BooleanJson updateUser(Usuario usuario){
		return new BooleanJson(user_service.update(usuario));
	}
	
}
