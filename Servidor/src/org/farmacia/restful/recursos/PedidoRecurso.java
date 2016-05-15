package org.farmacia.restful.recursos;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import org.farmacia.restful.jsonParser.PedidoJson;
import org.farmacia.restful.modelo.Pedido;
import org.farmacia.restful.servicios.PedidoServicio;


@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoRecurso {
	PedidoServicio ps = new PedidoServicio();
	
	@GET
	public Collection<Pedido> getPedidos(@QueryParam("user") String email){
		if (email != null && email.length() > 0)
			return ps.getPedidosUsuario(email);
		return ps.getPedidos();
	}
	
	@GET
	@Path("/{pedidoNumber}")
	public Pedido getPedido(@PathParam("pedidoNumber") int numero){
		return ps.getPedidoPorNumero(numero);
	}
	
	@POST
	public Pedido addPedido(PedidoJson p){
		return ps.creaPedidoModelo(p);
	}
}
