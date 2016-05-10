package org.farmacia.restful.db;

import java.sql.*;
import java.sql.SQLException;

import org.farmacia.restful.modelo.Usuario;

public final class Conexion {
	static String url = "//localhost:3306/farmacia_db";
	public Connection con;
	public static Conexion instancia;
	private Statement statement;
	
	private Conexion(){
		try{
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql:" + url, "usuario","usuario");
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
	 public int insertAutoincrement(String insertQuery) throws SQLException {
		 statement = instancia.con.createStatement();
		 statement.executeUpdate(insertQuery);
		 ResultSet res = statement.getGeneratedKeys();
		 int key = -1;
		 while(res.next())
		     key = res.getInt(1);
		 return key;
	  }
	 
	 public boolean updateUser(Usuario u){
		 return false;
	 }

}

