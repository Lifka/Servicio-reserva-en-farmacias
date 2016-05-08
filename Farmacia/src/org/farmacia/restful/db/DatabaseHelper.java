package org.farmacia.restful.db;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.farmacia.restful.modelo.Departamento;
import org.farmacia.restful.modelo.Direccion;
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
	
	private DatabaseHelper(){
		
		// PRODUCTOS
		Usuario us1 = new Usuario("jcgallardomorales@gmail.com","Juan Carlos Gallardo",
				new Direccion("calle",1,'a',"GRANADA",10000,"GRANADA","ESPAÑA"));
		
		Usuario us2 = new Usuario("javierizquierdovera@gmail.com","Javier Izquierdo Vera",
				new Direccion("calle",2,'d',"GRANADA",10000,"GRANADA","ESPAÑA"));
		
		Producto p1 = new Producto(1,"Producto 1", "Descripcion p 1",1.0f, new GregorianCalendar(),Departamento.HOMEOPATIA,21);
		Producto p2 = new Producto(2,"Producto 2", "Descripcion p 2",2.0f, new GregorianCalendar(),Departamento.MEDICAMENTOS, 16);
		Producto p3 = new Producto(3,"Producto 3", "Descripcion p 3",3.0f, new GregorianCalendar(),Departamento.MEDICAMENTOS, 21);
		Producto p4 = new Producto(4,"Producto 4", "Descripcion p 4",4.0f, new GregorianCalendar(),Departamento.PERFUMERIA, 21);
		
		productos.add(p1);
		productos.add(p2);
		productos.add(p3);
		productos.add(p4);
		
		// PEDIDOS
		Pedido pedido = new Pedido();
		pedido.createLineaPedido(p1, 3);
		pedido.createLineaPedido(p4, 2);
		
		Pedido pedido2 = new Pedido();
		pedido2.createLineaPedido(p1, 10);
		pedido2.createLineaPedido(p2, 5);
		
		Pedido pedido3 = new Pedido();
		pedido3.createLineaPedido(p4, 3);
		
		pedidos.add(us1.addPedido(pedido));
		pedidos.add(us1.addPedido(pedido2));
		pedidos.add(us2.addPedido(pedido3));
		
		
		
		// Stocks de farmacias
		
		Farmacia f1 = new Farmacia("1111111", null, null, null, -4.12f, 37.26f);
		Farmacia f2 = new Farmacia("2222222", null, null, null, -5.70f, 38.35f);
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
		getFarmaciasDB();
		return farmacias;
	}
	
	private void getFarmaciasDB(){
		try {
			Conexion conexion = Conexion.getConexion();
		    ResultSet res = conexion.query("select * from farmacia");
		      
		    while(res.next()){
		    	System.out.println(res.getString(1));
		    }
		    
		 }catch ( Exception e ) {
		      System.err.println("**************************" + e.getClass().getName() + ": " + e.getMessage() + e.getLocalizedMessage() );
		      //System.exit(0);
		 }
		 
	}
}
