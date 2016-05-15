package org.farmacia.restful.servicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.farmacia.restful.db.DatabaseHelper;
import org.farmacia.restful.jsonParser.LineaPedidoJson;
import org.farmacia.restful.jsonParser.PedidoJson;
import org.farmacia.restful.modelo.FactoriaAbstracta;
import org.farmacia.restful.modelo.FactoriaFarmacias;
import org.farmacia.restful.modelo.FactoriaPedidos;
import org.farmacia.restful.modelo.FactoriaProductos;
import org.farmacia.restful.modelo.Farmacia;
import org.farmacia.restful.modelo.Pedido;
import org.farmacia.restful.modelo.Producto;

public class PedidoServicio {
	private  Collection<Pedido> lista_pedidos;
	private  Collection<Producto> lista_productos;
	private  Collection<Farmacia> lista_farmacias;
	
	public PedidoServicio(){
		lista_pedidos = DatabaseHelper.getInstance().getPedidos();
		lista_productos = DatabaseHelper.getInstance().getProductosArrayList();
		lista_farmacias = DatabaseHelper.getInstance().getFarmaciasArrayList();
		
		if (lista_productos == null || lista_productos.isEmpty()){
			FactoriaAbstracta factoria = new FactoriaProductos();
			factoria.createObjects();
			lista_productos = DatabaseHelper.getInstance().getProductosArrayList();
		}
		if (lista_farmacias == null || lista_farmacias.isEmpty()){
			FactoriaAbstracta factoria = new FactoriaFarmacias();
			factoria.createObjects();
			lista_farmacias = DatabaseHelper.getInstance().getFarmaciasArrayList();
		}
		if (lista_pedidos == null || lista_pedidos.isEmpty()){
			FactoriaAbstracta factoria = new FactoriaPedidos();
			factoria.createObjects();
			lista_pedidos = DatabaseHelper.getInstance().getPedidos();
		}
	}
	public Collection<Pedido> getPedidos(){
		return lista_pedidos;
	}
	
	public List<Pedido> getPedidosUsuario(String email) {
		List<Pedido> pedidos_por_usuario = new ArrayList<Pedido>();
		for (Pedido p : lista_pedidos){
			if (p.getEmail_usuario().equals(email))
				pedidos_por_usuario.add(p);
		}
		return pedidos_por_usuario;
	}

	public Pedido getPedidoPorNumero(int numero) {
		for (Pedido p : lista_pedidos){
			if (p.getNum_pedido() == numero)
				return p;
		}
		return null;
	}

	public Pedido creaPedidoModelo(PedidoJson p) {
		Pedido pedido = new Pedido(getFarmaciaPorCif(p.getCif()));
		
		pedido.setEmail_usuario(p.getEmail());
		List<LineaPedidoJson> id_cantidad = p.getId_cantidad();
		for (LineaPedidoJson lpj: id_cantidad){
			Producto pr = getProductoPorId(lpj.getId());
			if (pr != null){
				pedido.createLineaPedido(pr, lpj.getCantidad());
			}
		}
		if (p.getForma_pago() != null)
			pedido.getFactura().pagar(p.getForma_pago());
		
		boolean insertado = DatabaseHelper.getInstance().addPedidoDB(pedido);
		if (insertado){
			return pedido;
		}else
			return null;
	}
	
	private Producto getProductoPorId(int id){
		for (Producto pr: lista_productos){
			if (pr.getId() == id)
				return pr;
		}
		return null;
	}
	
	private Farmacia getFarmaciaPorCif(String cif){
		for (Farmacia f: lista_farmacias)
			if (f.getCif().equals(cif))
				return f;
		return null;
	}
}
