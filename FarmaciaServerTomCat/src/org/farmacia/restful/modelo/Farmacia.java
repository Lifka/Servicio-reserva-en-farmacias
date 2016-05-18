package org.farmacia.restful.modelo;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Farmacia {
	private String cif;
	private String nombre;
	private Direccion direccion;
	private Date horario_abrir;
	private Date horario_cerrar;
	private List<StockProducto> listaStocks = new ArrayList<StockProducto>();
	private float longitud;
	private float latitud;
	
	public Farmacia(){}
	
	public Farmacia(String cif, String nombre, Direccion dir, Date horario_abrir, Date horario_cerrar, float longitud, float latitud){
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

	public List<StockProducto> getListaStocks() {
		return listaStocks;
	}

	public void setListaStocks(List<StockProducto> listaStocks) {
		this.listaStocks = listaStocks;
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
	
	public void addStockProducto(int id_producto, int stock){
		boolean encontrado = false;
		for (StockProducto sp : listaStocks){
			if (sp.getId_producto() == id_producto){
				encontrado = true;
				sp.setStock(sp.getStock() + stock);
			}
		}
		if (!encontrado)
			listaStocks.add(new StockProducto(id_producto, stock));
	}
	public boolean reducirStockProducto(int id, int stock){
		for (StockProducto sp : listaStocks){
			if (sp.getId_producto() == id && sp.getStock() > stock){
				sp.setStock(sp.getStock() - stock);
				return true;
			}
		}
		return false;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}

	public float getLatitud() {
		return latitud;
	}

	public void setLatitud(float latitud) {
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
				+ horario_abrir + ", horario_cerrar=" + horario_cerrar + ", listaStocks=" + listaStocks + ", longitud="
				+ longitud + ", latitud=" + latitud + "]";
	}
	

	
}
