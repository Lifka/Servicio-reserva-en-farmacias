package org.farmacia.restful.modelo;

import java.sql.ResultSet;


import org.farmacia.restful.db.Conexion;


public class test {

	public static void main(String[] args) {
		try {
			Conexion conexion = Conexion.getConexion();
		    ResultSet res = conexion.query("select * from farmacia");
		      
		    while(res.next()){
		    	System.out.println(res.getString(1));
		    } 
		 }catch ( Exception e ) {
		      System.err.println("LOCAL ********************" + e.getClass().getName() + ": " + e.getMessage() + e.getLocalizedMessage() );
		      //System.exit(0);
		    }
		    
	}

}
