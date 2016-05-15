package org.farmacia.restful.servicios;

import java.util.List;

import org.farmacia.restful.db.DatabaseHelper;
import org.farmacia.restful.modelo.Farmacia;

public class FarmaciaServicio {
	private final List<Farmacia> listadoFarmacias = DatabaseHelper.getInstance().getFarmaciasArrayList();

	public List<Farmacia> getFarmacias() {
		return listadoFarmacias;
	}

	public void nuevaFarmacia(Farmacia f) {
		DatabaseHelper.getInstance().addFarmaciaDB(f);
	}
	public void deleteFarmacia(String cif){
		DatabaseHelper.getInstance().deleteFarmacia(cif);
	}

}
