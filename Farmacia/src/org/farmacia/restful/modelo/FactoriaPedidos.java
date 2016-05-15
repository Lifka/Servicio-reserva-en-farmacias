package org.farmacia.restful.modelo;

import org.farmacia.restful.db.DatabaseHelper;

public class FactoriaPedidos extends FactoriaAbstracta{

	@Override
	public void createObjects() {
		DatabaseHelper.getInstance().getPedidosDB();
	}
	
}
