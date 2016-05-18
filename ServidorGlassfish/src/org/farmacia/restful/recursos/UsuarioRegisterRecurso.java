package org.farmacia.restful.recursos;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.farmacia.restful.jsonParser.LoginJson;
import org.farmacia.restful.modelo.Usuario;
import org.farmacia.restful.servicios.UsuarioService;

@Path("/register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioRegisterRecurso {
	UsuarioService user_service = new UsuarioService();
	
	@GET
	public List<Usuario> getUsuarios(){
		return user_service.getUsuarios();
	}
	
	@POST
	public LoginJson newUser(Usuario user){
		user = user_service.createUser(user);
		return  new LoginJson(user != null, user);
	}
}
