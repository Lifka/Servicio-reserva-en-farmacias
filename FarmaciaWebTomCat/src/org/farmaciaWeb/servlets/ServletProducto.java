package org.farmaciaWeb.servlets;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.farmaciaWeb.modelo.Departamento;
import org.farmaciaWeb.modelo.Producto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/ServletProducto")
public class ServletProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JSONArray result_json;
	private List<Producto> productos;
       
    /**
     * @throws IOException 
     * @see HttpServlet#HttpServlet()
     */
    public ServletProducto(){
    	result_json = null;
    	productos = new ArrayList<Producto>();
    }
    
    
   public void connectURL() throws IOException, JSONException{
	   HttpURLConnection connection = null;
   		BufferedReader input = null;
		try {
			StringBuilder response  = new StringBuilder();
			String request = "http://webfarmacia.azurewebsites.net/FarmaciaServer/api/productos";
	    	URL url = new URL(request);
			connection = (HttpURLConnection) url.openConnection();   
	    	connection.setDoOutput(true);
	    	connection.setDoInput(true);
	    	connection.setInstanceFollowRedirects(false); 
	    	connection.setRequestMethod("GET"); 
	    	connection.setRequestProperty("Content-Type", "application/json"); 
	    	connection.setRequestProperty("charset", "utf-8");
	    	connection.setUseCaches (false);
	    	
	    	if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
	            input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	            String strLine = null;
	            while ((strLine = input.readLine()) != null)
	            {
	                response.append(strLine);
	            }
	            input.close();
	            
	            result_json =  new JSONArray(response.toString());
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (connection != null)
				connection.disconnect();
			if (input != null)
				input.close();
       }
   }
   
    
    public boolean createProductoObjects() throws ParseException{
    	boolean creados = result_json != null;
    	if (creados){
    		try {
    			for (int i=0; i<result_json.length(); i++){
    				JSONObject pr_json = result_json.getJSONObject(i);
    				Producto p = new Producto(pr_json.getInt("id"), pr_json.getString("nombre"), pr_json.getString("descripcion"),
    						pr_json.getDouble("precio_sin_iva"), new Date(pr_json.getLong("f_creacion")), new Date(pr_json.getLong("f_caducidad")),
    						Departamento.valueOf(pr_json.getString("departamento")),pr_json.getDouble("iva"));
    				
    				productos.add(p);
    			}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return creados;
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accion = request.getParameter("accion_form");
		Producto producto = new Producto();
		if (accion.equals("new")){		
			String nombre = request.getParameter("nom_prod");
			String descripcion = request.getParameter("desc_prod");
			String precio = request.getParameter("precio_prod");
			String iva = request.getParameter("iva_prod");
			String dep = request.getParameter("dep_prod");
			
			 producto = new Producto(-1,nombre,descripcion,Double.parseDouble(precio),new Date(),new Date(), Departamento.valueOf(dep),Double.parseDouble(iva)/100+1);
			 this.sendProductoPOST(producto);
			 response.sendRedirect("http://webfarmacia.azurewebsites.net/FarmaciaWeb/administracion.jsp");
		}else if(accion.equals("del")){
			String id = request.getParameter("id_prod");
			this.deleteProducto(id);
			response.sendRedirect("http://webfarmacia.azurewebsites.net/FarmaciaWeb/productos.jsp");
		}
		
		// Conectar con http POST
		response.getWriter().append("Served at: " + producto.toString()).append(request.getContextPath());
	}

	private void deleteProducto(String id) throws IOException {
		HttpURLConnection conn = null;
		OutputStream os = null;
		try {
			  //constants
			  URL url = new URL("http://webfarmacia.azurewebsites.net/FarmaciaServer/api/productos/"+id);
			  conn = (HttpURLConnection) url.openConnection();
			  conn.setReadTimeout( 10000 /*milliseconds*/ );
			  conn.setConnectTimeout( 15000 /* milliseconds */ );
			  conn.setRequestMethod("DELETE");
			  System.out.println(conn.getResponseCode());

			  //open
			  conn.connect();
			  
			  //send
			  os = new BufferedOutputStream(conn.getOutputStream());
			  
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {  
			  conn.disconnect();
			}
		
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
	private void sendProductoPOST(Producto p) throws IOException{
		OutputStream os = null;
		InputStream is = null;
		HttpURLConnection conn = null;
		
		try {
			  //constants
			  URL url = new URL("http://webfarmacia.azurewebsites.net/FarmaciaServer/api/productos");
			  
			  JSONObject objeto_json = new JSONObject();
			  objeto_json.append("iva", p.getIva()).append("departamento", p.getDepartamento()).append("descripcion", p.getDescripcion()).append("nombre", p.getNombre()).append("precio_sin_iva", p.getPrecio_sin_iva());
			  String message = objeto_json.toString();

			  conn = (HttpURLConnection) url.openConnection();
			  conn.setReadTimeout( 10000 /*milliseconds*/ );
			  conn.setConnectTimeout( 15000 /* milliseconds */ );
			  conn.setRequestMethod("POST");
			  conn.setDoInput(true);
			  conn.setDoOutput(true);
			  conn.setFixedLengthStreamingMode(message.getBytes().length);

			  //make some HTTP header nicety
			  conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
			  conn.setRequestProperty("X-Requested-With", "application/json;charset=utf-8");

			  //open
			  conn.connect();
			  
			  //setup send
			  os = new BufferedOutputStream(conn.getOutputStream());
			  os.write(message.getBytes());
			  //clean up
			  os.flush();
			  
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {  
			  //clean up
			  os.close();
			  conn.disconnect();
			}
	}


}
