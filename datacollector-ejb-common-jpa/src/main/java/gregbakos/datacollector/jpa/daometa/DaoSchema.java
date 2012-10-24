package gregbakos.datacollector.jpa.daometa;

import java.io.Serializable;
import java.util.HashMap;

public class DaoSchema implements Serializable{
	private HashMap<String, DaoClass> classes = new HashMap<String, DaoClass>();
	public DaoSchema() {
		
	}
	public void setClasses(HashMap<String, DaoClass> classes) {
		this.classes = classes;
	}

	public HashMap<String, DaoClass> getClasses() {
		return classes;
	}
}
