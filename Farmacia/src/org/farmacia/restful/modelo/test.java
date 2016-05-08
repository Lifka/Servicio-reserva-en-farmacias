package org.farmacia.restful.modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.farmacia.restful.db.Conexion;
import org.farmacia.restful.servicios.PedidoServicio;

public class test {

	public static void main(String[] args) {
		try {
			Conexion conexion = Conexion.getConexion();
		    ResultSet res = conexion.query("select * from farmacia");
		      
		    while(res.next()){
		    	System.out.println(res.getString(1));
		    } 
		 }catch ( Exception e ) {
		      System.err.println("*****************************" + e.getClass().getName() + ": " + e.getMessage() + e.getLocalizedMessage() );
		      //System.exit(0);
		    }
	}

}
