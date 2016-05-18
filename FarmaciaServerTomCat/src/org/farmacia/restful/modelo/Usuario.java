package org.farmacia.restful.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Usuario {
	private String email;
	private String pass; // solo en pruebas
	private String nombre_completo;
	private Direccion direccion;
	private List<Pedido> historialPedidos;
	private String dni;
	private FORMA_PAGO pago;
	
	public Usuario(){}
	
	public Usuario(String email, String pass, String nombreCompleto, Direccion direccion, String dni, FORMA_PAGO pago) {
		this.email = email;
		this.pass = pass;
		this.nombre_completo = nombreCompleto;
		this.direccion = direccion;
		this.historialPedidos = new ArrayList<Pedido>();
		this.dni = dni;
		this.pago = pago;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getNombre_completo() {
		return nombre_completo;
	}

	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public FORMA_PAGO getPago() {
		return pago;
	}

	public void setPago(FORMA_PAGO pago) {
		this.pago = pago;
	}

	public Pedido addPedido(Pedido p){
		p.setEmail_usuario(email);
		if (historialPedidos == null)
			historialPedidos = new ArrayList<Pedido>();
		historialPedidos.add(p);
		return p;
	}

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", pass=" + pass + ", nombre_completo=" + nombre_completo + ", direccion="
				+ direccion + ", historialPedidos=" + historialPedidos + ", dni=" + dni + ", pago=" + pago + "]";
	}
	
}
