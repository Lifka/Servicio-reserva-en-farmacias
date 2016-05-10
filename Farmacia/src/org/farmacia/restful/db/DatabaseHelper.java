package org.farmacia.restful.db;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.farmacia.restful.modelo.Departamento;
import org.farmacia.restful.modelo.Direccion;
import org.farmacia.restful.modelo.FORMA_PAGO;
import org.farmacia.restful.modelo.Farmacia;
import org.farmacia.restful.modelo.Producto;
import org.farmacia.restful.modelo.Usuario;
import org.farmacia.restful.modelo.Pedido;

public class DatabaseHelper{
	private final static DatabaseHelper db = new DatabaseHelper();
	static Connection con;
	static Statement stat;
	static ResultSet res;
	
	private List<Producto> productos = new ArrayList<Producto>();
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	private List<Farmacia> farmacias = new ArrayList<Farmacia>();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	private DatabaseHelper(){
		
		// PRODUCTOS
		/*Usuario us1 = new Usuario("jcgallardomorales@gmail.com","mipass","Juan Carlos Gallardo",
				new Direccion("calle",1,'a',"GRANADA",10000,"GRANADA","ESPAÑA"),
				"22222222A",FORMA_PAGO.CONTRARREMBOLSO);
		
		Usuario us2 = new Usuario("javierizquierdovera@gmail.com","mipass","Javier Izquierdo Vera",
				new Direccion("calle",2,'d',"GRANADA",10000,"GRANADA","ESPAÑA"),
				"55555555H",FORMA_PAGO.CONTRARREMBOLSO);
		
		usuarios.add(us1);
		usuarios.add(us2);
		
		Producto p1 = new Producto(1,"Producto 1", "Descripcion p 1",1.0f, new GregorianCalendar(), new GregorianCalendar(),Departamento.HOMEOPATIA, 1.21f);
		Producto p2 = new Producto(2,"Producto 2", "Descripcion p 2",2.0f, new GregorianCalendar(), new GregorianCalendar(),Departamento.MEDICAMENTOS, 1.16f);
		Producto p3 = new Producto(3,"Producto 3", "Descripcion p 3",3.0f, new GregorianCalendar(), new GregorianCalendar(),Departamento.MEDICAMENTOS, 1.21f);
		Producto p4 = new Producto(4,"Producto 4", "Descripcion p 4",4.0f, new GregorianCalendar(), new GregorianCalendar(),Departamento.PERFUMERIA, 1.21f);
		
		productos.add(p1);
		productos.add(p2);
		productos.add(p3);
		productos.add(p4);
		
		
		// Stocks de farmacias
		
		Farmacia f1 = new Farmacia("1111111","Farmacia1", null, null, null, -4.12f, 37.26f);
		Farmacia f2 = new Farmacia("2222222", "Farmacia2", null, null, null, -5.70f, 38.35f);
		f1.addStockProducto(p1.getId(), 20);
		f1.addStockProducto(p2.getId(), 100);
		f1.addStockProducto(p3.getId(), 200);
		f1.addStockProducto(p4.getId(), 50);
		
		f2.addStockProducto(p1.getId(), 10);
		f2.addStockProducto(p2.getId(), 30);
		f2.addStockProducto(p3.getId(), 15);
		f2.addStockProducto(p4.getId(), 40);
		
		farmacias.add(f1);
		farmacias.add(f2);
		
		// PEDIDOS
		Pedido pedido1 = new Pedido(f1);
		pedido1.createLineaPedido(p1, 3);
		pedido1.createLineaPedido(p4, 2);
				
		Pedido pedido2 = new Pedido(f1);
		pedido2.createLineaPedido(p1, 10);
		pedido2.createLineaPedido(p2, 5);
				
		Pedido pedido3 = new Pedido(f2);
		pedido3.createLineaPedido(p4, 3);
				
		pedidos.add(us1.addPedido(pedido1));
		pedidos.add(us2.addPedido(pedido2));
		pedidos.add(us1.addPedido(pedido3));
		*/
		getFarmaciasDB();
		getProductosDB();
		getUsuariosDB();
	}

	public static DatabaseHelper getInstance(){
		return db;
	}
	
	public List<Producto> getProductos(){
		return productos;
	}
	
	public List<Pedido> getPedidos(){
		return pedidos;
	}
	
	public List<Farmacia> getFarmacias(){
		return farmacias;
	}
	
	public List<Usuario> getUsuarios(){
		return usuarios;
	}
	
	private void getProductosDB(){
		try {
			Conexion conexion = Conexion.getConexion();
		    ResultSet res = conexion.query("select * from producto");
		      
		    while(res.next()){
		    	Producto p = new Producto();
		    	if (res.getString(1) != null) p.setId(res.getInt(1));
		    	if (res.getString(2) != null) p.setNombre(res.getString(2));
		    	if (res.getString(3) != null) p.setDescripcion(res.getString(3));
			    if (res.getString(4) != null) p.setPrecio_sin_iva(res.getFloat(4));
			    if (res.getString(5) != null) p.setF_creacion(new GregorianCalendar(res.getDate(5).getYear(), res.getDate(5).getMonth(), res.getDate(5).getDay()));
			    if (res.getString(6) != null) p.setF_caducidad(new GregorianCalendar(res.getDate(6).getYear(), res.getDate(6).getMonth(), res.getDate(6).getDay()));
			    if (res.getString(7) != null){
			    	String dep = res.getString(7);
			    	if (Departamento.HOMEOPATIA.toString().equals(dep))
			    		p.setDepartamento(Departamento.HOMEOPATIA);
			    	else if (Departamento.MEDICAMENTOS.toString().equals(dep))
			    		p.setDepartamento(Departamento.MEDICAMENTOS);
			    	else if (Departamento.PERFUMERIA.toString().equals(dep))
			    		p.setDepartamento(Departamento.PERFUMERIA);
			    	else
			    		p.setDepartamento(Departamento.SIN_CLASIFICAR);
			    }
			    if (res.getString(8) != null) p.setIva(res.getFloat(8));
			    
		    	productos.add(p);
		    }
		    
		 }catch ( Exception e ) {
		      System.err.println("********** PRODUCTOS ****************" + e.getClass().getName() + ": " + e.getMessage() + e.getLocalizedMessage() );
		      //System.exit(0);
		 }
	}
	
	private void getFarmaciasDB(){
		try {
			Conexion conexion = Conexion.getConexion();
		    ResultSet res = conexion.query("select * from farmacia");
		      
		    while(res.next()){
		    	Farmacia f = new Farmacia();
		    	f.setCif(res.getString(1));
		    	f.setNombre(res.getString(2));
		    	int codigo_direccion = res.getInt(3);
		    	ResultSet res2 = conexion.query("select * from direccion where codigo='"+codigo_direccion+"'");
		    	
		    	// direccion de la farmacia
		    	if(res2.next()){
		    		Direccion d = new Direccion();
		    		
		    		if (res2.getString(2) != null)
		    			d.setCalle(res2.getString(2));
		    		if (res2.getString(3) != null)
		    			d.setNumero(res2.getInt(3));
		    		if (res2.getString(4) != null)
		    			d.setLetra((res2.getString(4).charAt(0)));
		    		if (res2.getString(5) != null)
		    			d.setCiudad(res2.getString(5));
		    		if (res2.getString(6) != null)
		    			d.setCodigo_postal(Integer.parseInt(res2.getString(6)));
		    		if (res2.getString(7) != null)
		    			d.setProvincia(res2.getString(7));
		    		if (res2.getString(8) != null)
		    			d.setPais(res2.getString(8));
		    		if (res2.getString(9) != null)
		    			d.setCodigo_postal(res2.getInt(9));
		    		
		    		f.setDireccion(d);
		    	}
		    	f.setHorario_abrir(res.getTime(4));
		    	f.setHorario_cerrar(res.getTime(5));
		    	f.setLongitud(res.getFloat(6));
		    	f.setLatitud(res.getFloat(7));
		    	farmacias.add(f);
		    }
		    
		 }catch ( Exception e ) {
		      System.err.println("********** FARMACIAS ****************" + e.getClass().getName() + ": " + e.getMessage() + e.getLocalizedMessage() );
		      //System.exit(0);
		 }
	}
	
	private void getUsuariosDB() {
		try {
			Conexion conexion = Conexion.getConexion();
		    ResultSet res = conexion.query("select * from usuario");
		      
		    while(res.next()){
		    	Usuario u = new Usuario();
		    	if (res.getString(2) != null) u.setEmail(res.getString(2));
		    	if (res.getString(3) != null) u.setPass(res.getString(3));
			    if (res.getString(4) != null) u.setNombre_completo(res.getString(4));
			    if (res.getString(5) != null){ // direccion
			    	int codigo_direccion = res.getInt(5);
			    	ResultSet res2 = conexion.query("select * from direccion where codigo='"+codigo_direccion+"'");
			    	
			    	// direccion de la farmacia
			    	if(res2.next()){
			    		Direccion d = new Direccion();
			    		
			    		if (res2.getString(2) != null)
			    			d.setCalle(res2.getString(2));
			    		if (res2.getString(3) != null)
			    			d.setNumero(res2.getInt(3));
			    		if (res2.getString(4) != null)
			    			d.setLetra((res2.getString(4).charAt(0)));
			    		if (res2.getString(5) != null)
			    			d.setCiudad(res2.getString(5));
			    		if (res2.getString(6) != null)
			    			d.setCodigo_postal(Integer.parseInt(res2.getString(6)));
			    		if (res2.getString(7) != null)
			    			d.setProvincia(res2.getString(7));
			    		if (res2.getString(8) != null)
			    			d.setPais(res2.getString(8));
			    		if (res2.getString(9) != null)
			    			d.setCodigo_postal(res2.getInt(9));
			    		
			    		u.setDireccion(d);
			    	}
			    }
			    if (res.getString(6) != null) u.setDni(res.getString(6));
			    if (res.getString(7) != null){
			    	String tip = res.getString(7);
			    	if (FORMA_PAGO.CONTRARREMBOLSO.toString().equals(tip))
			    		u.setPago(FORMA_PAGO.CONTRARREMBOLSO);
			    	else if (FORMA_PAGO.PAYPAL.toString().equals(tip))
			    		u.setPago(FORMA_PAGO.PAYPAL);
			    	else if (FORMA_PAGO.TARJETA.toString().equals(tip))
			    		u.setPago(FORMA_PAGO.TARJETA);
			    	else
			    		u.setPago(FORMA_PAGO.SIN_ESTABLECER);
			    }
		    	usuarios.add(u);
		    }
		    
		 }catch ( Exception e ) {
		      System.err.println("********** USUARIOS ****************" + e.getClass().getName() + ": " + e.getMessage() + e.getLocalizedMessage() );
		      //System.exit(0);
		 }
		
	}
}
