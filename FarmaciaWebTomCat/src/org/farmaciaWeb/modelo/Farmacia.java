package org.farmaciaWeb.modelo;

import java.util.Date;

import org.farmaciaWeb.modelo.Direccion;

public class Farmacia {
	private String cif;
	private String nombre;
	private Direccion direccion;
	private Date horario_abrir;
	private Date horario_cerrar;
	private double longitud;
	private double latitud;
	
	public Farmacia(){}
	
	public Farmacia(String cif, String nombre, Direccion dir, Date horario_abrir, Date horario_cerrar, double longitud, double latitud){
		this.cif = cif;
		this.nombre = nombre;
		this.direccion = dir;
		this.setHorario_abrir(horario_abrir);
		this.setHorario_cerrar(horario_cerrar);
		this.setLongitud(longitud);
		this.setLatitud(latitud);
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Date getHorario_abrir() {
		return horario_abrir;
	}

	public void setHorario_abrir(Date horario_abrir) {
		this.horario_abrir = horario_abrir;
	}

	public Date getHorario_cerrar() {
		return horario_cerrar;
	}

	public void setHorario_cerrar(Date horario_cerrar) {
		this.horario_cerrar = horario_cerrar;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Farmacia [cif=" + cif + ", nombre=" + nombre + ", direccion=" + direccion + ", horario_abrir="
				+ horario_abrir + ", horario_cerrar=" + horario_cerrar + ", longitud=" + longitud + ", latitud="
				+ latitud + "]";
	}
	
}
