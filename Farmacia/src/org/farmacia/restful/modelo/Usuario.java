package org.farmacia.restful.modelo;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
	private String email;
	private String nombreCompleto;
	private Direccion direccion;
	private List<Pedido> historialPedidos;
	
	public Usuario(){}
	
	public Usuario(String email, String nombreCompleto, Direccion direccion) {
		this.email = email;
		this.nombreCompleto = nombreCompleto;
		this.direccion = direccion;
		this.historialPedidos = new ArrayList<Pedido>();
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	
	public List<Pedido> getHistorialPedidos() {
		return historialPedidos;
	}
	public void setHistorialPedidos(List<Pedido> historialPedidos) {
		this.historialPedidos = historialPedidos;
	}
	
	public Pedido addPedido(Pedido p){
		p.setEmail_usuario(email);
		historialPedidos.add(p);
		return p;
	}
}
