package org.farmacia.restful.modelo;

public class Direccion {
	private String calle;
	private int numero;
	private char letra;
	private String ciudad;
	private String provincia;
	private String pais;
	private int codigo_postal;
	
	public Direccion(){}
	
	public Direccion(String calle, int numero, char letra, String ciudad,int codigo_postal, String provincia, String pais) {
		this.calle = calle;
		this.numero = numero;
		this.letra = letra;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.pais = pais;
		this.codigo_postal = codigo_postal;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public char getLetra() {
		return letra;
	}
	public void setLetra(char letra) {
		this.letra = letra;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getCodigo_postal() {
		return codigo_postal;
	}

	public void setCodigo_postal(int codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	
}
