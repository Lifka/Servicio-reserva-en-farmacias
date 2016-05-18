package org.farmacia.restful.db;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Date;
import org.farmacia.restful.modelo.Departamento;
import org.farmacia.restful.modelo.Direccion;
import org.farmacia.restful.modelo.FORMA_PAGO;
import org.farmacia.restful.modelo.Farmacia;
import org.farmacia.restful.modelo.LineaPedido;
import org.farmacia.restful.modelo.Producto;
import org.farmacia.restful.modelo.Usuario;
import org.farmacia.restful.modelo.Pedido;

public class DatabaseHelper{
	private final static DatabaseHelper db = new DatabaseHelper();
	static Connection con;
	static Statement stat;
	static ResultSet res;
	
	private Map<Integer, Producto> productos = new HashMap<Integer, Producto>();
	private Map<Integer,Pedido> pedidos = new HashMap<Integer,Pedido>();
	private Map<String, Farmacia> farmacias = new HashMap<String,Farmacia>();
	private Map<String,Usuario> usuarios = new HashMap<String,Usuario>();
	
	private DatabaseHelper(){}

	public static DatabaseHelper getInstance(){
		return db;
	}
	
	public List<Producto> getProductosArrayList(){
		List<Producto> p = new ArrayList<Producto>();
		for (Producto ped : productos.values())
			p.add(ped);
		return p;
	}
	
	public Map<Integer, Producto> getProductos(){
		return productos;
	}
	
	public List<Pedido> getPedidos(){
		List<Pedido> p = new ArrayList<Pedido>();
		for (Pedido ped : pedidos.values())
			p.add(ped);
		return p;
	}
	
	public List<Farmacia> getFarmaciasArrayList(){
		List<Farmacia> f = new ArrayList<Farmacia>();
		for (Farmacia far : farmacias.values())
			f.add(far);
		return f;
	}
	
	public List<Usuario> getUsuarios(){
		List<Usuario> u = new ArrayList<Usuario>();
		for (Usuario usu : usuarios.values())
			u.add(usu);
		return u;
	}
	
	public Map<Integer,Producto> getProductosDB(){
		try {
			Conexion conexion = Conexion.getConexion();
		    ResultSet res = conexion.query("select * from producto");
		      
		    while(res.next()){
		    	Producto p = new Producto();
		    	if (res.getString(1) != null) p.setId(res.getInt(1));
		    	if (res.getString(2) != null) p.setNombre(res.getString(2));
		    	if (res.getString(3) != null) p.setDescripcion(res.getString(3));
			    if (res.getString(4) != null) p.setPrecio_sin_iva(res.getFloat(4));
			    if (res.getString(5) != null){
			    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
			    	p.setF_creacion(df.parse(res.getString(5)));
			    }
			    if (res.getString(6) != null){
			    	DateFormat df = new SimpleDateFormat("yyyy-MM-dd",Locale.ENGLISH);
			    	p.setF_caducidad(df.parse(res.getString(6)));
			    }
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
			    
		    	productos.put(p.getId(), p);
		    }
		    
		 }catch ( Exception e ) {
		      System.err.println("********** PRODUCTOS ****************" + e.getClass().getName() + ": " + e.getMessage() + e.getLocalizedMessage() );
		      //System.exit(0);
		 }
		return productos;
	}
	
	public void getFarmaciasDB(){
		try {
			Conexion conexion = Conexion.getConexion();
		    ResultSet res = conexion.query("select * from farmacia");
		      
		    farmacias.clear();
		    while(res.next()){
		    	Farmacia f = new Farmacia();
		    	f.setCif(res.getString(1));
		    	f.setNombre(res.getString(2));
		    	int codigo_direccion = res.getInt(3);
		    	ResultSet res2 = conexion.query("select * from direccion where codigo="+codigo_direccion);
		    	
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
		    	DateFormat df = new SimpleDateFormat("HH:mm",Locale.ENGLISH);
		    	f.setHorario_abrir(df.parse(res.getString(4)));
		    	f.setHorario_cerrar(df.parse(res.getString(5)));
		    	f.setLongitud(res.getFloat(6));
		    	f.setLatitud(res.getFloat(7));
		    	farmacias.put(f.getCif(),f);
		    }
		    
		 }catch ( Exception e ) {
		      System.err.println("********** FARMACIAS ****************" + e.getClass().getName() + ": " + e.getMessage() + e.getLocalizedMessage() );
		      //System.exit(0);
		 }
	}
	
	public void getUsuariosDB() {
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
			    	ResultSet res2 = conexion.query("select * from direccion where codigo="+codigo_direccion+"");
			    	
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
		    	usuarios.put(u.getEmail(),u);
		    }
		    
		 }catch ( Exception e ) {
		      System.err.println("********** USUARIOS ****************" + e.getClass().getName() + ": " + e.getMessage() + e.getLocalizedMessage() );
		      //System.exit(0);
		 }
		
	}
	
	// OBTENCIÓN DE PEDIDOS
	public void getPedidosDB() {
		try {
			Conexion conexion = Conexion.getConexion();
		    ResultSet res = conexion.query("select pedido.num_pedido,pedido.fecha_pedido,usuario.email, cif_farmacia, linea_pedido.num_linea, linea_pedido.id_producto, linea_pedido.cantidad from pedido,linea_pedido,usuario where pedido.num_pedido=linea_pedido.num_pedido and usuario.id=pedido.id_usuario order by num_pedido,num_linea");
		      
		    while(res.next()){
		    	Pedido p = null;
		    	if (res.getString(1) != null){
		    		p = pedidos.get(res.getInt(1));
		    		if (p == null){ // creamos el pedido
		    			p = new Pedido();
				    	if (res.getString(1) != null) p.setNum_pedido(res.getInt(1));
				    	if (res.getString(2) != null){ 
				    		p.setFecha_pedido(new GregorianCalendar());
				    		p.getFecha_pedido().setTime(res.getDate(2));
				    	}
				    	if (res.getString(3) != null) p.setEmail_usuario(res.getString(3));
				    	if (res.getString(4) != null) p.setFarmacia(farmacias.get(res.getString(4)));
				    	pedidos.put(p.getNum_pedido(), p);
		    		}
		    		// creamos las líneas
			    	if (res.getString(5) != null && res.getString(6) != null && res.getString(7) != null){
			    		if (productos.get(res.getInt(6)) != null){
			    			p.addLineaPedido(new LineaPedido(res.getInt(5), productos.get(res.getInt(6)), res.getInt(7)));
			    		}
			    	}
		    	}
		    }
		    
		 }catch ( Exception e ) {
		      System.err.println("********** PEDIDOS ****************" + e.getClass().getName() + ": " + e.getMessage() + e.getLocalizedMessage() );
		      //System.exit(0);
		 }
	}
	
	public void getStocksFarmaciasDB() {
		try {
			Conexion conexion = Conexion.getConexion();
			ResultSet res1 = conexion.query("select * from stock_farmacia");
		    while (res1.next()){
		    	if (res1.getString(1) != null){
		    		Farmacia f = farmacias.get(res1.getString(1));
		    		if(res1.getString(2) != null && res1.getString(3) != null)
		    			f.addStockProducto(res1.getInt(2), res1.getInt(3));
		    	}
		    }
		    
		 }catch ( Exception e ) {
		      System.err.println("********** StOCKS ****************" + e.getClass().getName() + ": " + e.getMessage() + e.getLocalizedMessage() );
		      //System.exit(0);
		 }
	}

	public boolean addPedidoDB(Pedido pedido) {
		boolean insertado = false;
		try {
			Conexion conexion = Conexion.getConexion();
			ResultSet res1 = conexion.query("select id from usuario where email='"+pedido.getEmail_usuario()+"'");
			res1.next();
			int codigo_usuario = res1.getInt(1);
			
			Date dat = new Date(pedido.getFecha_pedido().getTimeInMillis());
		    int key = conexion.insert("insert into pedido(fecha_pedido,id_usuario,cif_farmacia) values('"+dat+"',"+codigo_usuario+",'"+pedido.getFarmacia().getCif()+"')");
		    if (key != -1){
		    	insertado = true;
		    	pedido.setNum_pedido(key);
		    	for (LineaPedido lp : pedido.getLineasPedido()){
		    		conexion.insert("insert into linea_pedido(num_pedido,num_linea,id_producto,cantidad) values("+key+","+lp.getNum_linea_pedido()+","+lp.getId_producto()+","+lp.getCantidad()+")");
		    	}
		    }
		    
		 }catch ( Exception e ) {
		      System.err.println("********** PEDIDOS INSERT ****************" + e.getClass().getName() + ": " + e.getMessage() + e.getLocalizedMessage() );
		      //System.exit(0);
		 }
		return insertado;
	}
	
	public boolean addProductoDB(Producto producto) {
		boolean insertado = false;
		try {
			Conexion conexion = Conexion.getConexion();
		    int key = conexion.insertAutoincrement("insert into producto(nombre,descripcion,precio,f_creacion,f_caducidad,departamento,iva) values('"+producto.getNombre()+"','"+producto.getDescripcion()+"',"+ producto.getPrecio_sin_iva() +",'" +new java.sql.Date(producto.getF_creacion().getTime())+"','"+new java.sql.Date(producto.getF_caducidad().getTime())+"','"+producto.getDepartamento()+"',"+producto.getIva()+")");
		    if (key >= 0){
		    	insertado = true;
		    	producto.setId(key);
		    	productos.put(key, producto);
		    }
		    
		 }catch ( Exception e ) {
		      System.err.println("********** PRODUCTO INSERT ****************" + e.getClass().getName() + ": " + e.getMessage() + e.getLocalizedMessage() );
		      //System.exit(0);
		 }
		return insertado;
	}

	public int updateUsuario(Usuario u) {
		try {
			Conexion conexion = Conexion.getConexion();
			return conexion.insert("update usuario set nombre_completo='"+u.getNombre_completo()+"', forma_pago='"+u.getPago()+"',  pass='"+u.getPass()+"' where email='"+u.getEmail()+"'");
		    
		 }catch ( Exception e ) {
		      System.err.println("********** USER UPDATE ****************" + e.getClass().getName() + ": " + e.getMessage() + e.getLocalizedMessage() );
		      //System.exit(0);
		 }
		return -1;
	}

	public int createUser(Usuario user) {
		int insertado  = -1;
		try {
			Conexion conexion = Conexion.getConexion();
			insertado = conexion.insertAutoincrement("insert into usuario(email,pass,nombre_completo,id_direccion,dni,forma_pago) values('"+user.getEmail()+"','"+user.getPass()+"','"+user.getNombre_completo()+"',NULL,'"+user.getDni()+"','"+user.getPago()+"')");
			if (insertado > -1)
				usuarios.put(user.getEmail(), user);
		 }catch ( Exception e ) {
		      System.err.println("********** USER CREATE ****************" + e.getClass().getName() + ": " + e.getMessage() + e.getLocalizedMessage() );
		      //System.exit(0);
		 }
		return insertado;
	}

	public void deleteProductoDB(int id) {
		try {
			Conexion conexion = Conexion.getConexion();
			conexion.insert("delete from producto where id="+id);
			productos.remove(id);
			System.out.println("eliminado");
		    
		 }catch ( Exception e ) {
		      System.err.println("********** PRODUCTO DELETE ****************" + e.getClass().getName() + ": " + e.getMessage() + e.getLocalizedMessage() );
		      //System.exit(0);
		 }
		
	}

	public void addFarmaciaDB(Farmacia f) {
		try {
			Conexion conexion = Conexion.getConexion();
			Direccion d = f.getDireccion();
			String query_dir = "insert into direccion(calle,numero,letra,ciudad,codigo_postal,provincia,pais,piso) values "
					+ "('"+d.getCalle()+"',"+d.getNumero()+",'"+d.getLetra()+"','"+d.getCiudad()+"','"+d.getCodigo_postal()
					+ "','"+d.getProvincia()+"','"+d.getPais()+"','"+0+"')";
			int key = conexion.insertAutoincrement(query_dir);
			if (key >= 1){
				String query_f = "insert into farmacia(cif,nombre,codigo_direccion,horario_apertura,horario_cierre,longitud,latitud)"
						+ " values ('"+f.getCif()+"','"+f.getNombre()+"',"+key+",'"+f.getHorario_abrir()+"','"+f.getHorario_cerrar()+"',"+f.getLongitud()+","+f.getLatitud()+")";
				if (conexion.insert(query_f) > 0)
					farmacias.put(f.getCif(), f);
			}

		 }catch ( Exception e ) {
		      System.err.println("********** FARMACIA CREATE ****************" + e.getClass().getName() + ": " + e.getMessage() + e.getLocalizedMessage() );
		      //System.exit(0);
		 }
		
	}

	public void deleteFarmacia(String cif) {
		try {
			Conexion conexion = Conexion.getConexion();
			conexion.insert("delete from farmacia where cif='"+cif+"'");
			farmacias.remove(cif);
		 }catch ( Exception e ) {
		      System.err.println("********** FARMACIA DELETE ****************" + e.getClass().getName() + ": " + e.getMessage() + e.getLocalizedMessage() );
		      //System.exit(0);
		 }
		
	}
	
	
}
