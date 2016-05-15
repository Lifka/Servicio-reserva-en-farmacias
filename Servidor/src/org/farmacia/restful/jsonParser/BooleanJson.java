package org.farmacia.restful.jsonParser;

public class BooleanJson {
	private boolean bool;
	
	public BooleanJson(){}
	
	public BooleanJson(boolean bool){
		this.setBool(bool);
	}

	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}
}
