package org.farmacia.restful.servicios;


import java.util.List;

import org.farmacia.restful.db.DatabaseHelper;
import org.farmacia.restful.jsonParser.LoginJson;
import org.farmacia.restful.jsonParser.UsuarioJson;
import org.farmacia.restful.modelo.Usuario;

public class UsuarioService {
	private final List<Usuario> usuarios = DatabaseHelper.getInstance().getUsuarios();
	
	public List<Usuario> getUsuarios(){
		return usuarios;
	}
	
	public LoginJson checkLogin(UsuarioJson user) {
		Usuario usuario = getUsuarioPorEmail(user.getEmail());
		boolean registrado = false;
		if (usuario != null){
			if (user.getPass().equals(usuario.getPass()))
				registrado = true;
		}
		if (!registrado)
			usuario = null;
		
		return new LoginJson(registrado,usuario);
			
	}
	
	private Usuario getUsuarioPorEmail(String email){
		for (Usuario u: usuarios){
			if (u.getEmail().equals(email))
				return u;
		}
		return null;
	}


	public Usuario createUser(Usuario user) {
		if (user.getEmail() != null && user.getEmail().length() > 0 &&
				user.getPass() != null && user.getPass().length() > 0 &&
				getUsuarioPorEmail(user.getEmail()) == null){ // no debe existir otro email igual
					usuarios.add(user);
					return user;
		}else{
			return null;
		}
	}

	public boolean update(Usuario u) {
		Usuario usuario = getUsuarioPorEmail(u.getEmail());
		boolean encontrado = false;
		
		if (u != null){
			encontrado = true;
			if (u.getPass() != null)
				usuario.setPass(u.getPass());
			if (u.getPago() != null)
				usuario.setPago(u.getPago());
			if (u.getNombre_completo() != null)
				usuario .setNombre_completo(u.getNombre_completo());
		}
		return encontrado;
	}
		
}
