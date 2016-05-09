package org.farmacia.restful.jsonParser;

import org.farmacia.restful.modelo.Usuario;

public class RegisterJson {
	private boolean registro;
	private Usuario usuario;
	
	public RegisterJson(){}
	
	public RegisterJson(boolean registro, Usuario us){
		this.registro = registro;
		this.usuario = us;
	}
	
	public boolean isRegistro() {
		return registro;
	}
	public void setRegistro(boolean registro) {
		this.registro = registro;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
