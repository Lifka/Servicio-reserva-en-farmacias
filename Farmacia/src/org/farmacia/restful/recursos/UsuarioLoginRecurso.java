package org.farmacia.restful.recursos;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.farmacia.restful.servicios.UsuarioLoginService;

@Path("/login")
public class UsuarioLoginRecurso {
	UsuarioLoginService user_service = new UsuarioLoginService();
	
	/* extraer todos los productos ofrecidos por el servicio de productos */
	@GET
	public boolean getFarmacias(@QueryParam("user") String email, @QueryParam("pass") String pass){
		if (email != null && pass != null && email.length() > 0 && pass.length() > 0)
			return user_service.checkLogin();
		else
			return false;
	}
}
