package org.farmacia.restful.servicios;

import java.util.ArrayList;
import java.util.List;

import org.farmacia.restful.db.DatabaseHelper;
import org.farmacia.restful.modelo.Pedido;

public class PedidoServicio {
	private final List<Pedido> lista_pedidos = DatabaseHelper.getInstance().getPedidos();
	
	public List<Pedido> getPedidos(){
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
}
