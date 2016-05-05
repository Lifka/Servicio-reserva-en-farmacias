package org.farmacia.restful.modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Producto {
	private int id;
	private String nombre;
	private String descripcion;
	private float precio;
	private Calendar f_creacion;
	private Calendar f_caducidad;
	
	public Producto(){}
	
	public Producto(int id, String nombre, String descripcion, float precio, Calendar f_caducidad){
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.f_creacion =  new GregorianCalendar();
		this.f_caducidad = f_caducidad;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Calendar getF_creacion() {
		return f_creacion;
	}
	public void setF_creacion(Calendar f_creacion) {
		this.f_creacion = f_creacion;
	}
	public Calendar getF_caducidad() {
		return f_caducidad;
	}
	public void setF_caducidad(Calendar f_caducidad) {
		this.f_caducidad = f_caducidad;
	}
}
