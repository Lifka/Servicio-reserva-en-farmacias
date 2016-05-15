package org.farmacia.restful.jsonParser;

import org.farmacia.restful.modelo.Usuario;

public class LoginJson {
	private boolean logeado;
	private Usuario user;
	
	public LoginJson(){}

	public LoginJson(boolean logeado, Usuario user) {
		this.logeado = logeado;
		this.user = user;
	}

	public boolean isLogeado() {
		return logeado;
	}

	public void setLogeado(boolean logeado) {
		this.logeado = logeado;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
	
}
