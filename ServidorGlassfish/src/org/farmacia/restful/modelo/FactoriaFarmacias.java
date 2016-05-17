package org.farmacia.restful.modelo;


import org.farmacia.restful.db.DatabaseHelper;

public class FactoriaFarmacias extends FactoriaAbstracta{

	@Override
	public void createObjects() {
		DatabaseHelper.getInstance().getFarmaciasDB();
	}

}
