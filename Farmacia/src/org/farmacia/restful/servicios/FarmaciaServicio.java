package org.farmacia.restful.servicios;

import java.util.List;

import org.farmacia.restful.db.DatabaseHelper;
import org.farmacia.restful.modelo.Farmacia;

public class FarmaciaServicio {
	private final List<Farmacia> listadoFarmacias = DatabaseHelper.getInstance().getFarmacias();

	public List<Farmacia> getFarmacias() {
		return listadoFarmacias;
	}

}
