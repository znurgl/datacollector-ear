package gregbakos.datacollector.jpa.daometa;

import java.io.Serializable;
import java.util.HashMap;

public class DaoClass implements Serializable{
	private HashMap<String, DaoField> fields = new HashMap<String, DaoField>();
	private String name;

	public void setFields(HashMap<String, DaoField> fields) {
		this.fields = fields;
	}

	public HashMap<String, DaoField> getFields() {
		return fields;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
