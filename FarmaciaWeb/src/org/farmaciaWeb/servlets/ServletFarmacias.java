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
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.farmaciaWeb.modelo.Departamento;
import org.farmaciaWeb.modelo.Direccion;
import org.farmaciaWeb.modelo.Farmacia;
import org.farmaciaWeb.modelo.Producto;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


@WebServlet("/ServletFarmacias")
public class ServletFarmacias extends HttpServlet {
	private static final long serialVersionUID = 2L;
	private JSONArray result_json;
	private List<Farmacia> farmacias;
       
    /**
     * @throws IOException 
     * @see HttpServlet#HttpServlet()
     */
    public ServletFarmacias(){
    	result_json = null;
    	farmacias = new ArrayList<Farmacia>();
    }
    
   public void connectURL() throws IOException, JSONException{
	   HttpURLConnection connection = null;
   		BufferedReader input = null;
		try {
			StringBuilder response  = new StringBuilder();
			String request = "http://localhost:8080/Farmacia/api/farmacias";
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
   
   public JSONArray getResult_json() {
		return result_json;
	}

	public void setResult_json(JSONArray result_json) {
		this.result_json = result_json;
	}
   
    
    public boolean createFarmacias() throws ParseException{
    	boolean creado = result_json != null;
    	if (creado){
    		try {
    			for (int i=0; i<result_json.length(); i++){
    				JSONObject f_json = result_json.getJSONObject(i);
    				Farmacia f = new Farmacia(
    						f_json.getString("cif"),
    						f_json.getString("nombre"),
    						null,
    						null,
    						null,
    						f_json.getDouble("longitud"),
    						f_json.getDouble("latitud")
    				);
    				
    				// Direccion
    				JSONObject f_json_direccion = f_json.getJSONObject("direccion");
    				Direccion d = new Direccion(
    						f_json_direccion.getString("calle"),
    						f_json_direccion.getInt("numero"),
    						f_json_direccion.getString("letra"),
    						f_json_direccion.getString("ciudad"),
    						f_json_direccion.getInt("codigo_postal"),
    						f_json_direccion.getString("provincia"),
    						f_json_direccion.getString("pais")
    				);
    				f.setDireccion(d);
    				
    				// horarios
    				SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
    				f.setHorario_abrir(new Time(df.parse(f_json.getString("horario_abrir")).getTime()));
    				f.setHorario_cerrar(new Time(df.parse(f_json.getString("horario_cerrar")).getTime()));
    				
    				farmacias.add(f);
    			}
    			creado = true;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return creado;
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				String accion = request.getParameter("accion_form");
				Farmacia farmacia = new Farmacia();
				if (accion.equals("new")){		
					String cif = request.getParameter("cif_farm");
					String nom = request.getParameter("nom_farm");
					String hora_apertura = request.getParameter("hora_apertura")+":00";
					String hora_cierre = request.getParameter("hora_cierre")+":00";	
					
					farmacia.setCif(cif);
					farmacia.setNombre(nom);
					SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
					System.out.println(hora_apertura);
    				try {
						farmacia.setHorario_abrir(new Time(df.parse(hora_apertura).getTime()));
						farmacia.setHorario_cerrar(new Time(df.parse(hora_cierre).getTime()));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				response.sendRedirect("http://localhost:8080/FarmaciaWeb/administracion.jsp");
    				
    				// Google maps
    				String latitud = request.getParameter("latitud");
    				String longitud = request.getParameter("longitud");
    				
    				farmacia.setLatitud(Double.parseDouble(latitud));
    				farmacia.setLongitud(Double.parseDouble(longitud));
    				
    				// Direccion
    				Direccion dir;
    				String calle_dir = request.getParameter("calle_dir");
    				String num_dir = request.getParameter("num_dir");
    				String letra_dir = request.getParameter("letra_dir");
    				String ciudad_dir = request.getParameter("ciudad_dir");
    				String cod_dir = request.getParameter("cod_dir");
    				String provincia_dir = request.getParameter("provincia_dir");
    				String pais_dir = request.getParameter("pais_dir");
    						
    				dir = new Direccion(calle_dir, Integer.parseInt(num_dir), letra_dir, ciudad_dir,Integer.parseInt(cod_dir), provincia_dir, pais_dir);
    				
    				farmacia.setDireccion(dir);
    				this.sendFarmaciaPOST(farmacia);
    				
				}else if(accion.equals("del")){
					String cif_farmacia = request.getParameter("cif_farm");
					this.deleteFarmacia(cif_farmacia);
					response.sendRedirect("http://localhost:8080/FarmaciaWeb/farmacias.jsp");
				}
	}

	private void sendFarmaciaPOST(Farmacia farmacia) throws IOException {
		OutputStream os = null;
		HttpURLConnection conn = null;
		
		try {
			  //constants
			  URL url = new URL("http://localhost:8080/Farmacia/api/farmacias");
			  
			  JSONObject objeto_json = new JSONObject(farmacia);
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {  
			  //clean up
			  os.close();
			  conn.disconnect();
			}
		
	}

	private void deleteFarmacia(String cif_farmacia) throws IOException {
		HttpURLConnection conn = null;
		OutputStream os = null;
		try {
			  //constants
			  URL url = new URL("http://localhost:8080/Farmacia/api/farmacias/"+cif_farmacia);
			  conn = (HttpURLConnection) url.openConnection();
			  conn.setReadTimeout( 10000 /*milliseconds*/ );
			  conn.setConnectTimeout( 15000 /* milliseconds */ );
			  conn.setRequestMethod("DELETE");
			  System.out.println(conn.getResponseCode());
			  conn.setDoOutput(true);

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

	public List<Farmacia> getFarmacias() {
		return farmacias;
	}

	public void setFarmacias(List<Farmacia> farmacias) {
		this.farmacias = farmacias;
	}


}
