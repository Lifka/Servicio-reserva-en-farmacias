package org.farmacia.restful.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Factura {
	private List<LineaFactura> lineasFactura;
	private float totalSinIVA;
	private float totalConIVA;
	private Calendar fecha_creacion;
	private Calendar fecha_pago;
	private boolean pagado;
	private TIPO_PAGO tipo_pago;
	
	public Factura(){
		lineasFactura = new ArrayList<LineaFactura>();
		setFecha_creacion(new GregorianCalendar());
		setFecha_pago(null);
		setPagado(false);
		setTipo_pago(null);
		setTotalSinIVA(0.0f);
		setTotalConIVA(0.0f);
	}
	
	public void addLineaFactura(LineaFactura lf){
		lineasFactura.add(lf);
		setTotalSinIVA(getTotalSinIVA() + lf.getTotalSinIva());
		setTotalConIVA(getTotalConIVA() + lf.getTotalConIVA());
	}

	public float getTotalSinIVA() {
		return totalSinIVA;
	}

	public void setTotalSinIVA(float totalSinIVA) {
		this.totalSinIVA = totalSinIVA;
	}

	public float getTotalConIVA() {
		return totalConIVA;
	}

	public void setTotalConIVA(float totalConIVA) {
		this.totalConIVA = totalConIVA;
	}

	public Calendar getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Calendar fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public Calendar getFecha_pago() {
		return fecha_pago;
	}

	public void setFecha_pago(Calendar fecha_pago) {
		this.fecha_pago = fecha_pago;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public TIPO_PAGO getTipo_pago() {
		return tipo_pago;
	}

	public void setTipo_pago(TIPO_PAGO tipo_pago) {
		this.tipo_pago = tipo_pago;
	}
	
	public String pagar(TIPO_PAGO tipo){
		pagado = true;
		fecha_pago = new GregorianCalendar();
		return "Pagado con " + tipo;
	}
	
}
