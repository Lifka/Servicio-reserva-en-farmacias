package org.farmacia.restful.modelo;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlRootElement;

public class Producto {
	private int id;
	private String nombre;
	private String descripcion;
	private float precio_sin_iva;
	private float precion_con_iva;
	private int iva;

	private Calendar f_creacion;
	private Calendar f_caducidad;
	private Departamento departamento;
	
	
	public Producto(){}
	
	public Producto(int id, String nombre, String descripcion, float precio, Calendar f_caducidad, Departamento d, int iva){
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio_sin_iva = precio;
		this.f_caducidad = f_caducidad;
		if (departamento == d) this.departamento = Departamento.SIN_CLASIFICAR; else this.departamento = d;
		this.iva = iva;
		this.precion_con_iva = precio * ((float)this.iva/100 + 1);
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
	
	public int getIva() {
		return iva;
	}

	public void setIva(int iva) {
		this.iva = iva;
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

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public float getPrecio_sin_iva() {
		return precio_sin_iva;
	}

	public void setPrecio_sin_iva(float precio_sin_iva) {
		this.precio_sin_iva = precio_sin_iva;
	}

	public float getPrecion_con_iva() {
		return precion_con_iva;
	}

	public void setPrecion_con_iva(float precion_con_iva) {
		this.precion_con_iva = precion_con_iva;
	}
}
