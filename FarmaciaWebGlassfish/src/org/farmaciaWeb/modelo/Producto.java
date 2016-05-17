package org.farmaciaWeb.modelo;
import java.util.Calendar;
import org.farmaciaWeb.modelo.Departamento;

public class Producto {
		private int id;
		private String nombre;
		private String descripcion;
		private double precio_sin_iva;
		private String f_creacion;
		private String f_caducidad;
		private Departamento departamento;
		private double iva;
		
		public Producto(){}
		
		public Producto(int id, String nombre, String descripcion, double precio, String f_creacion, String f_caducidad, Departamento d, double iva){
			this.id = id;
			this.nombre = nombre;
			this.descripcion = descripcion;
			this.precio_sin_iva = precio;
			this.f_caducidad = f_caducidad; 
			this.f_creacion = f_creacion;
			if (d == null) this.departamento = Departamento.SIN_CLASIFICAR; else this.departamento = d;
			this.iva = iva;
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

		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getF_creacion() {
			return f_creacion;
		}
		public void setF_creacion(String f_creacion) {
			this.f_creacion = f_creacion;
		}
		public String getF_caducidad() {
			return f_caducidad;
		}
		public void setF_caducidad(String f_caducidad) {
			this.f_caducidad = f_caducidad;
		}

		public Departamento getDepartamento() {
			return departamento;
		}

		public void setDepartamento(Departamento departamento) {
			this.departamento = departamento;
		}

		public double getPrecio_sin_iva() {
			return precio_sin_iva;
		}

		public void setPrecio_sin_iva(double precio_sin_iva) {
			this.precio_sin_iva = precio_sin_iva;
		}

		public double getIva() {
			return iva;
		}

		public void setIva(double iva) {
			this.iva = iva;
		}

		@Override
		public String toString() {
			return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio_sin_iva="
					+ precio_sin_iva + ", f_creacion=" + f_creacion + ", f_caducidad=" + f_caducidad + ", departamento="
					+ departamento + ", iva=" + iva + "]";
		}
		
}
