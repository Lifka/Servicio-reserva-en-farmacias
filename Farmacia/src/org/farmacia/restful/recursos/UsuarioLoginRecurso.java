package org.farmacia.restful.recursos;


import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.farmacia.restful.jsonParser.LoginJson;
import org.farmacia.restful.jsonParser.UsuarioJson;
import org.farmacia.restful.servicios.UsuarioService;

@Path("/login")
public class UsuarioLoginRecurso {
	UsuarioService user_service = new UsuarioService();
	
	/* extraer todos los productos ofrecidos por el servicio de productos */
	@POST
	public LoginJson checkUserLogin(UsuarioJson user){
		return user_service.checkLogin(user);
	}
}
