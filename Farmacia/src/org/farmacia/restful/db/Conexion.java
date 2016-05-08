package org.farmacia.restful.db;

import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Conexion {
	static String url = "/home/jgallardo/workspace/farmacia/Farmacia/MySQLiteDB";
	public Connection con;
	public static Conexion instancia;
	private Statement statement;
	
	private Conexion(){
		try{
            Class.forName("org.sqlite.JDBC");
            this.con = DriverManager.getConnection("jdbc:sqlite:" + url);
        }
        catch(ClassNotFoundException | SQLException e){
       	 	e.printStackTrace();
        }
	}
	
	public static synchronized Conexion getConexion() {
        if ( instancia == null ) {
            instancia = new Conexion();
        }
        return instancia;
    }
	
	 public ResultSet query(String query) throws SQLException{
	        statement = instancia.con.createStatement();
	        ResultSet res = statement.executeQuery(query);
	        return res;
	    }
	 public int insert(String insertQuery) throws SQLException {
	     statement = instancia.con.createStatement();
	     int result = statement.executeUpdate(insertQuery);
	     return result;
	   }

}

