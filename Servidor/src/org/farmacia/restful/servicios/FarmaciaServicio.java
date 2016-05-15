package org.farmacia.restful.servicios;

import java.util.Collection;
import java.util.List;

import org.farmacia.restful.db.DatabaseHelper;
import org.farmacia.restful.modelo.FactoriaAbstracta;
import org.farmacia.restful.modelo.FactoriaFarmacias;
import org.farmacia.restful.modelo.FactoriaProductos;
import org.farmacia.restful.modelo.Farmacia;

public class FarmaciaServicio {
	private Collection<Farmacia> listadoFarmacias;

	public FarmaciaServicio(){
		listadoFarmacias = DatabaseHelper.getInstance().getFarmaciasArrayList();
		if (listadoFarmacias == null || listadoFarmacias.isEmpty()){
			FactoriaAbstracta factoria = new FactoriaFarmacias();
			factoria.createObjects();
			listadoFarmacias = DatabaseHelper.getInstance().getFarmaciasArrayList();
		}
	}
	public Collection<Farmacia> getFarmacias() {
		return listadoFarmacias;
	}

	public void nuevaFarmacia(Farmacia f) {
		DatabaseHelper.getInstance().addFarmaciaDB(f);
	}
	public void deleteFarmacia(String cif){
		DatabaseHelper.getInstance().deleteFarmacia(cif);
	}

}
